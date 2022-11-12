package cn.itcast.dao;

import cn.itcast.domain.CartItem;
import cn.itcast.domain.Order;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-25 19:43
 */
@Repository
public interface OrderDao {

    // 生成订单
    @Insert("insert into t_order (order_id, create_time, price, status, user_id) values (#{order.orderId},#{order.datetime}, #{order.price}, #{order.status}, #{order.userId})")
    public int createOrder(@Param("order") Order order);

    // 把购物车内的商品转成订单项
    @Insert("insert into t_order_item (name, count, price, total_price, order_id) values (#{item.name},#{item.count},#{item.price},#{item.totalPrice},#{oid})")
    public void createOrderItem(@Param("item") CartItem item, @Param("oid") String oid);

    // 修改订单
    @Update("update t_order set status = 1 where order_id = #{oID}")
    public void updateOrder(@Param("oID") String oID);

    // 查询用户订单
    @Select("select order_id, create_time, price, status, user_Id from t_order where user_Id=#{uid}")
    @Results(id = "t_order", value = {
            @Result(id = true, column = "order_id", property="orderId"),
            @Result(column = "create_time", property="datetime"),
            @Result(column = "price", property="price"),
            @Result(column = "status", property="status"),
            @Result(column = "user_Id", property="userId")
    })
    public List<Order> queryForUserOrder(@Param("uid")Integer uid);

    // 查询所有订单
    @Select("select order_id, create_time, price, status, user_Id from t_order ")
    @ResultMap(value = "t_order")
    public List<Order> queryForAllOrder();

}
