package com.hh.dao.impl;

import com.hh.dao.UserDao;
import com.hh.pojo.User;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 19:12
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql="select id,username,password,email from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select id,username,password,email from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }
    @Override
    public int saveUser(User user) {
        String sql="INSERT INTO t_user(username,password,email) VALUES(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
