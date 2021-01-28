package com.hh.web;

import com.hh.pojo.Cart;
import com.hh.pojo.User;
import com.hh.service.OrderService;
import com.hh.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 15:40
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cart对象
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        // 获取userId
        User user =(User) req.getSession().getAttribute("user");
        if (user ==null ){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;//不再执行后面的代码
        }
        Integer userId=user.getId();

        //创建订单,获取订单号,保存在域中
        String orderId = orderService.createOrder(cart,userId);

//        请求转发到订单结算页  改为重定向，防止表单重复提交
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
