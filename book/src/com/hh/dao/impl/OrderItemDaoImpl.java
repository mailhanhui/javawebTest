package com.hh.dao.impl;

import com.hh.dao.OrderItemDao;
import com.hh.pojo.OrderItem;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 13:11
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOderItem(OrderItem oderItem) {
        String sql="insert into t_order_Item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,oderItem.getName(),oderItem.getCount(),oderItem.getPrice(),oderItem.getTotalPrice(),oderItem.getOrderID());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return null;
    }
}
