package com.atguigu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author howardy
 * @date 2021/12/26 - 16:37
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应字符集为UTF-8
        response.setContentType("text/html; charset=UTF-8");
        // 方式三：采用抽象类，所有Serverlet程序继承此抽象类，
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            // 获取action业务鉴别字段，获取相应业务方法反射对象
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 调用目标业务方法
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 将异常抛出
            throw new RuntimeException(e);
        }
    }
}
