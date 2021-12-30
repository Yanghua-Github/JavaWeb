package com.atguigu.pojo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author howardy
 * @date 2021/12/29 - 9:29
 */
public class Order {
    private String orderId;
    private String createDate;
    private BigDecimal totalPrice;
    // 0 表示 未发货；1 表示 已发货；2 表示 已完成
    private int status;
    private int userId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Order(String orderId, String createDate, BigDecimal totalPrice, int status, int userId) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    public Order() {
    }
}
