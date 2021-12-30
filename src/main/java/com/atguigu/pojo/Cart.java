package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author howardy
 * @date 2021/12/28 - 20:11
 */
public class Cart {
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public void addItem(CartItem item){
        // 遍历是否存在对应的书籍，存在则更新并直接返回
        for (Map.Entry<Integer, CartItem> entry: items.entrySet()) {
            if(entry.getKey() == item.getId()){
                CartItem cartItem = entry.getValue();
                cartItem.setCount(cartItem.getCount() + item.getCount());
                return;
            }
        }
        items.put(item.getId(), item);
    }

    public void deleteItem(int id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public int getTotalCount(){
        int totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public void updateItem(int id, int newCount){
        items.get(id).setCount(newCount);
    }
}
