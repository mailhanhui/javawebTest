package com.hh.service;

import com.hh.pojo.Cart;
import com.hh.pojo.Order;
import com.hh.pojo.OrderItem;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 14:57
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> showAllOrders();
    public void sendOrder(String orderId);
    public List<OrderItem> showOrderDetail(String orderId);
    public List<Order> showMyOrders(Integer userId);
    public void receiveOrder(String orderId);
}
