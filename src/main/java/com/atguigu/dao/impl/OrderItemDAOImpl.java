package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDAO;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/29 - 9:54
 */
public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public int saveOrderItem(CartItem cartItem, String orderId) {
        String sql = "insert into t_order_item(`id`, `name`, `price`, `total_money`, `count`, `order_id`)" +
                "values(?,?,?,?,?,?);";
        return update(sql, null, cartItem.getName(), cartItem.getPrice(), cartItem.getTotalPrice(), cartItem.getCount(), orderId);
    }

    @Override
    public List<OrderItem> queryOrderItems(String orderId) {
        String sql = "select `id`, `name`, `price`, `total_money` as totalPrice, `count`, `order_id` as orderId from t_order_item where orderId=?;";
        return queryForList(sql, orderId);
    }
}
