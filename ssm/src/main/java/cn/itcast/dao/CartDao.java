package cn.itcast.dao;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-24 19:22
 */

@Repository
public interface CartDao {

    // 根据提供的用户id查询cart
    @Select("select * from t_cart where user_id = #{userId}")
    @Results(id = "cart", value = {
            @Result(id = true, column = "id", property = "cartId"),
            @Result(column = "totalPrice", property = "totalPrice"),
            @Result(column = "totalCount", property = "totalCount"),
            @Result(column = "user_id", property = "userid")
    })
    public Cart queryForCartByUserId(@Param("userId") Integer userId);

    @Select("select t_cart.id from t_cart where user_id = #{userId}")
    public Integer queryCartId(@Param("userId") Integer userId);

    //购物车生成
    @Insert("insert into t_cart (totalPrice, totalCount, user_id) values (0, 0, #{user_id})")
    public void addCart(@Param("user_id") Integer user_id);

    // 添加商品通过购物车的id
    @Insert("insert into t_cartitem (name, count, price, totalPrice, cart_Id) values (#{book.name}, 1, #{book.price}, #{book.price}, #{cartId})")
    public void addItemToCart(@Param("cartId")Integer cartId, @Param("book")Book book);

    // 通过商品名字查询购物车内的商品
    @Select("select * from t_cartitem where name = #{itemName}")
    public CartItem queryForCartItemByItemName(@Param("itemName")String itemName);

    // 通过cartid查询购物车内的商品
    @Select("select * from t_cartitem where cart_id = #{cartId}")
    public List<CartItem> queryForCartItemsByCartId(@Param("cartId")Integer cartId);

    // 更新商品通过购物车的id
    @Update("update t_cartitem set count=#{item.count}, totalPrice=#{item.totalPrice} where cart_id=#{item.cart_id} and name = #{item.name}")
    public void updateItemToCart(@Param("item")CartItem item);



    // 删除购物车里面的商品
    @Delete("delete from t_cartitem where cart_id=#{item.cart_id} and name = #{item.name} ")
    public void deleteItemToCart(@Param("item")CartItem item);

    // 清空购物车商品
    @Delete("delete from t_cartitem where cart_id=#{id}")
    public void clearItem(@Param("id")Integer id);

    // 还原购物车信息
    @Delete("update  t_cart set totalPrice = 0, totalCount = 0 where user_Id=#{id}")
    public void reCart(@Param("id")Integer id);
}
