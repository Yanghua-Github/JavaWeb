package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDAO;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author howardy
 * @date 2021/12/29 - 9:51
 */
public class OrderDAOImpl extends BaseDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `create_time`, `total_money`, `status`, `user_id`) values(?,?,?,?,?);";
        return update(sql, order.getOrderId(), order.getCreateDate(), order.getTotalPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public int updateOrder(Order order) {
        String sql = "update t_order set `create_time`=?, `total_money`=?, `status`=?, `user_id`=? where order_id=?";
        return update(sql, order.getCreateDate(), order.getTotalPrice(), order.getStatus(), order.getUserId(), order.getOrderId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` as orderId, `create_time` as createTime, `total_money` as totalPrice, `status`, `user_id` as userId from t_order;";
        return queryForList(sql);
    }

    @Override
    public Order queryOrderById(String orderId) {
        String sql = "select `order_id` as orderId, `create_time` as createTime, `total_money` as totalPrice, `status`, `user_id` as userId from t_order where orderId=?;";
        return queryForOne(sql, orderId);
    }
}
