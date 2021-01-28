package com.hh.service.impl;

import com.hh.dao.UserDao;
import com.hh.dao.impl.UserDaoImpl;
import com.hh.pojo.User;
import com.hh.service.UserService;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 20:15
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        } else {
            return true;
        }
    }
}
