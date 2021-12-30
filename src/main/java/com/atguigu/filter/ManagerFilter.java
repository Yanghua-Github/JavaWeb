package com.atguigu.filter; /**
 * @author howardy
 * @date 2021/12/30 - 8:49
 */

import com.atguigu.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ManagerFilter",
        urlPatterns = "/pages/manager/*",
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")/*这里可以放一些初始化的参数*/
        })
public class ManagerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        if(user == null || user.getId() != 11){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }else{
            chain.doFilter(request, response);
        }

    }
}
