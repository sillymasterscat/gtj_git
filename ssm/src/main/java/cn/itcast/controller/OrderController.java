package cn.itcast.controller;

import cn.itcast.domain.Cart;
import cn.itcast.domain.Order;
import cn.itcast.domain.User;
import cn.itcast.service.CartService;
import cn.itcast.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author fail
 * @create 2021-03-26 9:32
 */
@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    // 结算购物车内的商品
    @RequestMapping("/bill")
    public void bill(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 得到session域中的cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 创建order对象
        String orderID = System.currentTimeMillis() + cart.getUserid() + "";
        Order order = new Order(orderID, new Date(), cart.getTotalPrice(), 0, cart.getUserid());
        // 创建订单
        orderService.createOrder(order, cart.getList());
        // 把购物车清空
        cartService.clearItem(cart.getCartId(), cart.getUserid());
        // 重定向到我的订单
        // 重定向到之前的页面
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    // 用户查询自己的订单
    @RequestMapping("/userOrder")
    public void userOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 得到session域中的user
        User user = (User) request.getSession().getAttribute("user");
        // 查询订单
        List<Order> orderList = orderService.queryForUserOrder(user.getId());
        // 保存到session域中
        request.setAttribute("orderList", orderList);
        // 跳转到 order.jsp
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    // 管理员查询所有的订单
    @RequestMapping("/allOrder")
    public void allOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Order> orderList = orderService.queryForAllOrder();
        // 保存到session域中
        request.setAttribute("orderList", orderList);
        // 跳转到 order_manager.jsp
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 得到oid
        String oid = request.getParameter("oid");
        // 修改订单状态
        orderService.updateOrder(oid);
        // 重定向回去
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

}
