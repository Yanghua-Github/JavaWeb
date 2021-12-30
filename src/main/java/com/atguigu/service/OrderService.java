package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/29 - 10:23
 */
public interface OrderService {
    Order createOrder(Cart cart, int userId);

    List<Order> queryOrders();

    List<OrderItem> queryMyOrders(String orderId);

    void updateOrderStatus(String orderId, int status);
}
