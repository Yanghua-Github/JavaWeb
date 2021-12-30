package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/29 - 14:35
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response){

    }

    protected void sendOrder(HttpServletRequest request, HttpServletResponse response){

    }
}
