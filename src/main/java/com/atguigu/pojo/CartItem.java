package com.atguigu.pojo;

import java.math.BigDecimal;

/**
 * @author howardy
 * @date 2021/12/28 - 20:11
 */
public class CartItem {
    private String name;
    private int count;
    private BigDecimal price;
    private int id;

    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    public BigDecimal getTotalPrice(){
        return getPrice().multiply(new BigDecimal(getCount()));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CartItem(String name, int count, BigDecimal price, int id) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.id = id;
    }

    public CartItem() {
    }
}
