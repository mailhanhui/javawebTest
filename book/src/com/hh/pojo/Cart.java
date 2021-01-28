package com.hh.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 22 - 0:07
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class Cart {
    //key是商品编号；value是商品信息
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();
    //添加商品项
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加过此商品，如果已添加，则数量和总金额更新。如果没添加，直接放到集合中
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            items.put(cartItem.getId(), cartItem);
        }else{
            item.setCount(item.getCount()+1);//数量累加
//            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
            item.setTotalPrice(item.getTotalPrice().add(item.getPrice()));
        }
    }
    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空g购物车
    public void clear(){
        items.clear();
    }

    //修改商品数量
    public void updateCount(Integer id,Integer count){
        //先查看是否有此商品。有则修改商品数量，更新总金额
        CartItem item = items.get(id);
        if(item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));//更新总金额
        }

    }
    public Integer getTotalCount() {
        Integer totalCount=0;
        for (Map.Entry<Integer,CartItem>entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry:items.entrySet()) {
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }
    public Map<Integer, CartItem> getItems() {
        return items;
    }
    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", Items=" + items +
                '}';
    }
}
