package com.hh.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 15 - 18:59
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            //一行代码，把所有请求的参数都注入到user对象中
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    //将字符串转化为int类型
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
