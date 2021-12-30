package com.atguigu.dao;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/29 - 9:35
 */
public interface OrderDAO {
    int saveOrder(Order order);
    int updateOrder(Order order);
    List<Order> queryOrders();
    Order queryOrderById(String orderId);
}
