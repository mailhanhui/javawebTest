package com.hh.dao.impl;

import com.hh.dao.OrderDao;
import com.hh.pojo.Order;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 13:10
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserID());
    }

    @Override
    public List<Order> queryOrders() {
        return null;
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {

    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        return null;
    }
}
