package com.hh.test;

import com.hh.pojo.Cart;
import com.hh.pojo.CartItem;
import com.hh.service.OrderService;
import com.hh.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 15:31
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1, "java入门", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java入门", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));
        OrderService orderService=new OrderServiceImpl();
        System.out.println("订单号为："+orderService.createOrder(cart, 1));
    }

    @Test
    public void showAllOrders() {
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderDetail() {
    }

    @Test
    public void showMyOrders() {
    }

    @Test
    public void receiveOrder() {
    }
}