package com.hh.service;

import com.hh.pojo.User;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 20:10
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface UserService {
    //注册用户
    public void registUser(User user);
    //登录
    public User login(User user);
    //检查用户名是否存在 true表示已存在
    public boolean existsUsername(String username);
}
