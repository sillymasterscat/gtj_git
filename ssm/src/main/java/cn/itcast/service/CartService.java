package cn.itcast.service;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-24 19:31
 */
public interface CartService {

    //  添加购物车对象
    public void addCart(Integer user_id);

    // 通过商品名字查询购物车内的商品
    public CartItem queryForCartItemByItemName(String itemName);

    // 根据提供用户id查询cart
    public Cart queryForCartByUserId(Integer userId);

    // 根据提供的cartid查询cartItem
    public List<CartItem> queryForCartItemsByUserId(Integer cartId);

    // 向购物车添加商品通过购物车的id
    public void addItemToCart(Integer cartId, Book book);

    // 删除购物车里面的商品
    public void deleteItemToCart(CartItem cartItem);

    // 清空购物车
    public void clearItem(Integer cartId, Integer userId);

    //修改购物车中的商品信息
    public void updateCartItem(CartItem cartItem);

    Integer queryCartId(int id);
}
