package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/26 - 16:02
 */

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.atguigu.utils.WebUtils.copyParamToBean;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userServiceImpl.login(username, password);
        if(user != null){
            System.out.println("登陆成功");
            // 将登录用户信息设置到session域
            request.getSession().setAttribute("user", user);
            // 当登录成功后，就初始化一个购物车对象
            Cart cart = new Cart();
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request, response);
        }else{
            System.out.println("密码或账户错误");
            request.setAttribute("msg", "密码或账户错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
        }
    }

    protected void regist(@org.jetbrains.annotations.NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取表单验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 移除表单验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 方式一：获取表单参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
        String code = request.getParameter("code");
        // 方式二：通过BeanUtils获取表单参数
        User user = copyParamToBean(request.getParameterMap(), new User());


        request.setAttribute("username", user.getUsername());
        request.setAttribute("email", user.getEmail());

        // 检查验证码是否正确
        if(token == null || !token.equals(code)){
            System.out.println("验证码错误");
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
        }else{
            // 检查是否有同名用户，若有同名用户，则跳转回当前页面，（并保存原数据？）
            if(userServiceImpl.existUsername(user.getUsername())){
                System.out.println("存在同名用户");
                request.setAttribute("msg", "存在同名用户");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else{
                System.out.println("注册成功");
                userServiceImpl.register(new User(101, user.getUsername(), user.getPassword(), user.getEmail()));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());;
    }

    protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求的参数username
        String username = request.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userServiceImpl.existUsername(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }

}
