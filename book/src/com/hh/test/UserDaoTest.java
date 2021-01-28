package com.hh.test;

import com.hh.dao.UserDao;
import com.hh.dao.impl.UserDaoImpl;
import com.hh.pojo.User;
import org.junit.Test;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 19:29
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码错误，不存在该用户");
        }else{
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
                System.out.println(userDao.saveUser(new User(null,"admin2","123456","mail@gmail.com")));
    }
}