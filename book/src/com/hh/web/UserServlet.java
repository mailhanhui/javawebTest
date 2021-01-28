package com.hh.web;

import com.google.gson.Gson;
import com.hh.pojo.User;
import com.hh.service.UserService;
import com.hh.service.impl.UserServiceImpl;
import com.hh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 15 - 16:40
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数并封装为对象
        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //调用userService.login()处理登录，返回符合条件的用户
        User loginUser = userService.login(user);
        //判断是否存在该用户
        if(loginUser==null){
            //登录失败，把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误...");
            req.setAttribute("username",user.getUsername());
            // 跳转回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            //登录成功
            req.getSession().setAttribute("user", loginUser);
            // 跳转到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    //注销
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        req.getSession().invalidate();
        //重定向到首页（或登陆页面）
        resp.sendRedirect(req.getContextPath());
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //1.获取session中的验证码
        String tokenCode=(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //2.获取完立即从session删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //3.判断从session获取的验证码与表单提交的是否一致
        if(tokenCode!=null&&tokenCode.equalsIgnoreCase(req.getParameter("code"))){
            //检查用户名是否可用
            if(userService.existsUsername(user.getUsername())){
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //回显信息 跳转回注册页面
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername()查询是否存在该用户
        Boolean existsUsername=userService.existsUsername(username);

        //把返回的结果封装为map对象
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson=new Gson();
        String json = gson.toJson(resultMap);

        //返回给客户端
        resp.getWriter().write(json);
    }
}
