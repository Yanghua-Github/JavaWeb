package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author howardy
 * @date 2021/12/26 - 16:49
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            System.out.println("注入之前：" + bean);
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Integer parseInt(String strInt, Integer defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    public static void sendRedirect(HttpServletResponse response, String redirectUrl){
        try {//这里并不是设置跳转页面，而是将重定向的地址发给前端，让前端执行重定向
            //设置跳转地址
            response.setHeader("redirectUrl", redirectUrl);
            //设置跳转使能
            response.setHeader("enableRedirect","true");
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
