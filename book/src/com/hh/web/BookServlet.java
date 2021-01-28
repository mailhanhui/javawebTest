package com.hh.web;

import com.hh.pojo.Book;
import com.hh.pojo.Page;
import com.hh.service.BookService;
import com.hh.service.impl.BookServiceImpl;
import com.hh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 16 - 23:01
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //获取请求的参数
        Book book= WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //保存图书到数据库
        bookService.addBook(book);
        //跳到图书管理页面(用重定向，不能用请求转发！！)
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        //重定向地址，要加上工程名 req.getContextPath()
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //删除图书
        bookService.deleteBookById(id);
        //重定向到图书管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数，封装成book对象
        Book book=WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用updateBook()修改图书
        bookService.updateBook(book);
        //重定向回到图书列表管理页面 bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过bookService查询全部图书
        List<Book> books=bookService.queryBooks();
        //把全部图书保存到request域中
        req.setAttribute("books", books);
        //请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    //根据修改请求中的id获取图书
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取编号
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //调用查询图书
        Book book=bookService.queryBookById(id);
        //保存到request域中
        req.setAttribute("book", book);
        //请求转发到，book——edit
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");
        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
