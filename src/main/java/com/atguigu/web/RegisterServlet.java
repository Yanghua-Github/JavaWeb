package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/24 - 9:21
 */

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("get方法");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post方法");
        // 获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        request.setAttribute("username", username);
        request.setAttribute("email", email);

        // 检查验证码是否正确
        if(!"abcde".equals(code)){
            System.out.println("验证码错误");
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
        }else{
            // 检查是否有同名用户，若有同名用户，则跳转回当前页面，（并保存原数据？）
            if(userServiceImpl.existUsername(username)){
                System.out.println("存在同名用户");
                request.setAttribute("msg", "存在同名用户");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else{
                System.out.println("注册成功");
                userServiceImpl.register(new User(101, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }
    }
}
