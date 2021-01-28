package com.hh.filter;

import com.hh.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 26 - 10:46
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//将异常抛出给tomcat管理
        }
    }
    @Override
    public void destroy() {

    }
}
