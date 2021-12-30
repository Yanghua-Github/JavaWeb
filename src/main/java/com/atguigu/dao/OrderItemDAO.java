package com.atguigu.dao;

import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/29 - 9:52
 */
public interface OrderItemDAO {
    int saveOrderItem(CartItem cartItem, String orderId);

    List<OrderItem> queryOrderItems(String orderId);
}
