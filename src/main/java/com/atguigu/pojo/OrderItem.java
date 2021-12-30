package com.atguigu.pojo;

import java.math.BigDecimal;

/**
 * @author howardy
 * @date 2021/12/29 - 9:22
 */
public class OrderItem {
    private int id;
    private String name;
    private BigDecimal price;
    private int count;
    private int orderId;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalPrice=" + getTotalPrice() +
                ", count=" + count +
                ", orderId=" + orderId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderItem(int id, String name, BigDecimal price, int count, int orderId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.orderId = orderId;
    }

    public OrderItem() {
    }

    public BigDecimal getTotalPrice(){
        return price.multiply(BigDecimal.valueOf(count));
    }
}
