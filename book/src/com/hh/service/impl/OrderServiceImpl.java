package com.hh.service.impl;

import com.hh.dao.BookDao;
import com.hh.dao.OrderDao;
import com.hh.dao.OrderItemDao;
import com.hh.dao.impl.BookDaoImpl;
import com.hh.dao.impl.OrderDaoImpl;
import com.hh.dao.impl.OrderItemDaoImpl;
import com.hh.pojo.*;
import com.hh.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 15:07
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号：唯一性(时间戳+用户id)
        String orderId=""+System.currentTimeMillis()+userId;
        //创建订单对象
        Order order=new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中每个商品项，转化成为订单项，保存到数据库
        cart.getItems();
        for (Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()) {
            //获取购物车中每个商品项
            CartItem cartItem=entry.getValue();
            //将每个商品项转换为订单项
            OrderItem orderItem=new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项
            orderItemDao.saveOderItem(orderItem);
            //修改BOOK的库存与总销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+orderItem.getCount());
            book.setStock(book.getStock()-orderItem.getCount());
            //保存修改！！！
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return null;
    }

    @Override
    public void sendOrder(String orderId) {

    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return null;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return null;
    }

    @Override
    public void receiveOrder(String orderId) {

    }
}
