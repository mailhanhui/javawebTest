package com.hh.test;

import java.lang.reflect.Method;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 20:23
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class UserServiceTest {


    public void login(){
        System.out.println("这是login()方法调用了");
    }
    public void regist(){
        System.out.println("这是regist()方法调用了");
    }
    public void updateUser(){
        System.out.println("这是updateUser()方法调用了");
    }
    public void updateUserPassword(){
        System.out.println("这是updateUserPassword()方法调用了");
    }

    public static void main(String[] args) {
        String action ="login";
        try {
            //通过action字符串，反射获取相应的方法
            Method method = UserServiceTest.class.getDeclaredMethod(action);
            //调用获取到的方法
            method.invoke(new UserServiceTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}