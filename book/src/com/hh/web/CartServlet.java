package com.hh.web;

import com.google.gson.Gson;
import com.hh.pojo.Book;
import com.hh.pojo.Cart;
import com.hh.pojo.CartItem;
import com.hh.service.BookService;
import com.hh.service.impl.BookServiceImpl;
import com.hh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 22 - 14:35
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class CartServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    //加入购物车
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.queryBookById，得到图书信息
        Book book = bookService.queryBookById(id);
        //3.把图书信息转化为cartItem商品项
        CartItem item=new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem（cartItem），添加商品到购物车
        //3.1.先从session中获取购物车
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        //3.2 判断是否为空，再决定新建还是使用原来购物车
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //添加商品项
        cart.addItem(item);

//        //将最后一条商品的名称放入session供首页使用
        req.getSession().setAttribute("lastName",item.getName());

        //返回购物车总的数量和最后一个添加的商品
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", item.getName());
        Gson gson=new Gson();
        String result=gson.toJson(resultMap);
        resp.getWriter().write(result);
    }

    //加入购物车-原来
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.queryBookById，得到图书信息
        Book book = bookService.queryBookById(id);
        //3.把图书信息转化为cartItem商品项
        CartItem item=new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem（cartItem），添加商品到购物车
        //3.1.先从session中获取购物车
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        //3.2 判断是否为空，再决定新建还是使用原来购物车
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(item);

        //将最后一条商品的名称放入session供首页使用
        req.getSession().setAttribute("lastName",item.getName());

        //重定向回原来商品所在的地址页面
        // req.getHeader("referer"),表示：从请求头中获取发起请求页面的地址
        resp.sendRedirect(req.getHeader("referer"));
    }
    //删除购物车项
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        int id=WebUtils.parseInt(req.getParameter("id"), 0);
        //从session获取购物车对象
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        //
        if(cart!=null){
            cart.deleteItem(id);
            //重定向回
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"), 0);
        int count=WebUtils.parseInt(req.getParameter("count"), 0);
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id, count);
            //重定向回
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
            //重定向回
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
