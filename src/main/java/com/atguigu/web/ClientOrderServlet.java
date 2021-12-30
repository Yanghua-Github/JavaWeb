package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/29 - 14:42
 */

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ClientOrderServlet", value = "/client/OrderServlet")
public class ClientOrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * @Author HowardY
     * @Description 用户创建订单
     * @Date 14:42 2021/12/29
     * @param request
     * @param response
     **/
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        if(cart == null){
            // 如果cart为空，说明用户未登录，跳转到登陆页面
            // 转到登录页面
            response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        // 创建OrderId 貌似返回值可以优化成orderId
        Order order = orderService.createOrder(cart, user.getId());
        request.setAttribute("orderId", order.getOrderId());
        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
    }
}
