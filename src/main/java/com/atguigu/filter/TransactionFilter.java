package com.atguigu.filter; /**
 * @author howardy
 * @date 2021/12/30 - 9:49
 */

import com.atguigu.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "TransactionFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")/*这里可以放一些初始化的参数*/
        })
public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(request, response);
            JDBCUtils.closeAndCommit(); // 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.closeAndRollback(); // 回滚事务
            // 如果需要跳转到错误页面，需要配置web.xml以及相关的错误页面.jsp
            // 并且：将该异常抛出，抛到Tomcat处理该异常
            throw new RuntimeException(e);
        }
    }
}
