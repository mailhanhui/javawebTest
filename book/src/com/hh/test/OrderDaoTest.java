package com.hh.test;

import com.hh.dao.OrderDao;
import com.hh.dao.impl.OrderDaoImpl;
import com.hh.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 13:56
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        Order order=new Order("1234567", new Date(), new BigDecimal(100), 0, 1);
        orderDao.saveOrder(order);
    }

    @Test
    public void queryOrders() {
    }

    @Test
    public void changeOrderStatus() {
    }

    @Test
    public void queryOrderByUserId() {
    }
}