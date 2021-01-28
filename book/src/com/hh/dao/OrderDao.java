package com.hh.dao;

import com.hh.pojo.Order;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 12:57
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface OrderDao {
    public int saveOrder(Order oder);
    public List<Order> queryOrders();
    public void changeOrderStatus(String orderId,Integer status);
    public List<Order> queryOrderByUserId(int userId);
}
