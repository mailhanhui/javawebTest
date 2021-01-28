package com.hh.dao;

import com.hh.pojo.OrderItem;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 12:57
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface OrderItemDao {
    public int saveOderItem(OrderItem oderItem);
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
