package cn.itcast.controller;

import cn.itcast.dao.CartDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.service.BookService;
import cn.itcast.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

/**
 * @author fail
 * @create 2021-03-24 20:30
 */
@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;


    // 从数据库中根据id查找购物车
    @ModelAttribute
    public void cart(HttpServletRequest request, Map<String, Cart> map) {
        // filter过滤器保证有user登录，直接取userid
        int id = Integer.parseInt(request.getParameter("id"));
        // 查询cart
        Cart cart = cartService.queryForCartByUserId(id);

        // 保存
        map.put("cart", cart);

    }

    // 返回购物车内的商品显示给用户
    @RequestMapping("/cartPage")
    public void cartPage(@ModelAttribute("cart") Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 查询购物车内的商品
        List<CartItem> cartItems = cartService.queryForCartItemsByUserId(cart.getCartId());
        int count = 0;
        for (CartItem item : cartItems) {
            cart.setTotalPrice(cart.getTotalPrice().add(item.getTotalPrice()));
            count += item.getCount();
        }
        cart.setList(cartItems);
        cart.setTotalCount(count);
        System.out.println(cart);
        // 保存到request中
        request.getSession().setAttribute("cart", cart);
        // 跳转到页面 pages/cart/cart.jsp
        request.getRequestDispatcher("/pages/cart/cart.jsp").forward(request, response);
    }

    // 添加商品进入购物车
    @RequestMapping("/add")
    public void add(@ModelAttribute("cart") Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 得到bookname
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        // 查找book
        Book book = bookService.queryById(bookId);
        // 更新book库存
        book.setStock(book.getStock() - 1);
        bookService.update(book);
        // 不存在添加商品，存在更新信息
        CartItem cartItem = cartService.queryForCartItemByItemName(book.getName());
        if (cartItem == null) {
            cartService.addItemToCart(cart.getCartId(), book);
        } else {
            cartItem.setCount(cartItem.getCount() + 1);
            cartItem.setTotalPrice(cartItem.getTotalPrice().add(book.getPrice()));
            cartService.updateCartItem(cartItem);

        }
        // 重定向回去
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    // 更新购物车内的商品信息
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 拿到bookName和 count
        String bookName = request.getParameter("bookName");
        Integer count = Integer.parseInt(request.getParameter("count"));
        // 查找book
        Book book = bookService.queryByName(bookName);
        // 查找cartitem
        CartItem cartItem = cartService.queryForCartItemByItemName(book.getName());
        // 更新book库存
        book.setStock(book.getStock() + cartItem.getCount() - count);
        bookService.update(book);
        // 更新cartitem信息
        cartItem.setCount(count);
        cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(count)));
        cartService.updateCartItem(cartItem);

        // 重定向回去
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    // 删除购物车内的商品
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 拿到bookName
        String bookName = request.getParameter("bookName");
        // 查找item
        CartItem item = cartService.queryForCartItemByItemName(bookName);
        // 查找book
        Book book = bookService.queryByName(bookName);
        // 更新book库存
        book.setStock(book.getStock() + item.getCount());
        bookService.update(book);
        // 删除
        cartService.deleteItemToCart(item);
        // 重定向回去
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    // 清空购物车内的商品
    @RequestMapping("/clear")
    public void clear(@ModelAttribute("cart") Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 更新库存
        Cart cart1 = (Cart) request.getSession().getAttribute("cart");
        List<CartItem> list = cart1.getList();
        for (CartItem item : list) {
            // 查找book
            Book book = bookService.queryByName(item.getName());
            // 更新book库存
            book.setStock(book.getStock() + item.getCount());
            bookService.update(book);
        }
        //清空
        cartService.clearItem(cart.getCartId(), cart.getUserid());
        // 重定向回去
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}
