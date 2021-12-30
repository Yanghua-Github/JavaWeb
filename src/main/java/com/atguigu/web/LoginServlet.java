package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/24 - 12:11
 */

import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get方法");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post方法");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(userServiceImpl.login(username, password) != null){
            System.out.println("登陆成功");
            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request, response);
        }else{
            System.out.println("密码或账户错误");
            request.setAttribute("msg", "密码或账户错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
        }
    }
}
