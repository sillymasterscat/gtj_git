package cn.itcast.service.impl;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.CartDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-24 20:09
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;



    @Override
    public void addCart(Integer user_id) {
        cartDao.addCart(user_id);
    }

    @Override
    public CartItem queryForCartItemByItemName(String itemName) {
        CartItem cartItem = cartDao.queryForCartItemByItemName(itemName);
        return cartItem;
    }


    @Override
    public Cart queryForCartByUserId(Integer userId) {
        Cart cart = cartDao.queryForCartByUserId(userId);
        System.out.println("queryForCartByUserId");
        return cart;
    }

    @Override
    public List<CartItem> queryForCartItemsByUserId(Integer cartId) {
        List<CartItem> cartItems = cartDao.queryForCartItemsByCartId(cartId);
        System.out.println("queryForCartItemsByUserId");
        return cartItems;
    }

    @Override
    public void addItemToCart(Integer cartId, Book book) {
        cartDao.addItemToCart(cartId, book);
        System.out.println("addItemToCart");
    }

    @Override
    public void deleteItemToCart(CartItem cartItem) {
        cartDao.deleteItemToCart(cartItem);
        System.out.println("deleteItemToCart");
    }

    @Override
    public void clearItem(Integer cartId, Integer userId) {
        cartDao.clearItem(cartId);
        cartDao.reCart(userId);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartDao.updateItemToCart(cartItem);
    }

    @Override
    public Integer queryCartId(int id) {
        return cartDao.queryCartId(id);
    }
}
