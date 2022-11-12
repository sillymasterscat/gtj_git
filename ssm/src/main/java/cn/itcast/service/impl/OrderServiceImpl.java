package cn.itcast.service.impl;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Order;
import cn.itcast.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-25 20:25
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void createOrder(Order order, List<CartItem> item) {
        orderDao.createOrder(order);
        for (CartItem i : item) {
            orderDao.createOrderItem(i, order.getOrderId());
        }
    }

    @Override
    public void updateOrder(String oID) {
        orderDao.updateOrder(oID);
    }

    @Override
    public List<Order> queryForUserOrder(Integer uid) {
        List<Order> orders = orderDao.queryForUserOrder(uid);
        return orders;
    }

    @Override
    public List<Order> queryForAllOrder() {
        List<Order> orders = orderDao.queryForAllOrder();

        return orders;

    }

}
