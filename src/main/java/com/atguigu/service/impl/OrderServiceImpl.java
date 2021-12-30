package com.atguigu.service.impl;

import com.atguigu.dao.OrderDAO;
import com.atguigu.dao.OrderItemDAO;
import com.atguigu.dao.impl.OrderDAOImpl;
import com.atguigu.dao.impl.OrderItemDAOImpl;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author howardy
 * @date 2021/12/29 - 10:44
 */
public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    public Order createOrder(Cart cart, int userId) {
        Order order = new Order();

        String OrderId = System.currentTimeMillis() + "" + userId;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        format.setTimeZone(TimeZone.getDefault());

        order.setOrderId(OrderId);
        order.setCreateDate(format.format(timestamp));
        order.setStatus(0);
        order.setTotalPrice(cart.getTotalPrice());
        order.setUserId(userId);
        orderDAO.saveOrder(order);

        // 模拟出现异常，测试事务
        int i = 12 / 0;

        // 将购物车中的每项存入t_order_item中
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            orderItemDAO.saveOrderItem(entry.getValue(), OrderId);
        }

        return order;
    }

    @Override
    public List<Order> queryOrders() {
        List<Order> orders = orderDAO.queryOrders();
        return orders;
    }

    @Override
    public List<OrderItem> queryMyOrders(String orderId) {
        List<OrderItem> orderItems = orderItemDAO.queryOrderItems(orderId);
        return orderItems;
    }

    @Override
    public void updateOrderStatus(String orderId, int status) {
        Order order = orderDAO.queryOrderById(orderId);
        order.setStatus(status);
        orderDAO.updateOrder(order);
    }
}
