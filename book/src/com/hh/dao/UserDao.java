package com.hh.dao;

import com.hh.pojo.User;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 19:02
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface UserDao {

    /*
    * 根据用户名查找用户信息
    * 如果返回null说明没有这个用户，反之亦然
    * */
    public User queryUserByUsername(String username);

    /*
    * 保存用户信息
    * */
    public int saveUser(User user);

    /*
    * 根据用户名与密码查询用户信息，实现登陆
    * 返回null说明用户名或密码错误，反之返回用户
    * */
    public User queryUserByUsernameAndPassword(String username,String password);

}
