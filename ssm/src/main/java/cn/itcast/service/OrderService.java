package cn.itcast.service;

import cn.itcast.domain.CartItem;
import cn.itcast.domain.Order;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-25 20:23
 */
public interface OrderService {

    // 生成订单
    public void createOrder(Order order, List<CartItem> item);

    // 修改订单
    public void updateOrder(String oID);

    // 查询用户订单
    public List<Order> queryForUserOrder(Integer uid);

    // 查询所有订单
    public List<Order> queryForAllOrder();

}
