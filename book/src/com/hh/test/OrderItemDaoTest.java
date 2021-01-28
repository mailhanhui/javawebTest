package com.hh.test;

import com.hh.dao.OrderItemDao;
import com.hh.dao.impl.OrderItemDaoImpl;
import com.hh.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 13:57
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderItemDaoTest {

    @Test
    public void saveOderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        orderItemDao.saveOderItem(new OrderItem(null, "java入门", 1, new BigDecimal(100), new BigDecimal(100), "1234567"));
        orderItemDao.saveOderItem(new OrderItem(null, "java进阶", 2, new BigDecimal(100), new BigDecimal(200), "1234567"));
        orderItemDao.saveOderItem(new OrderItem(null, "js入门", 1, new BigDecimal(100), new BigDecimal(100), "1234567"));
        orderItemDao.saveOderItem(new OrderItem(null, "java入门", 1, new BigDecimal(100), new BigDecimal(100), "1234567"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
    }
}