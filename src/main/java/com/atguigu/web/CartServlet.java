package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/28 - 21:16
 */

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.sun.net.httpserver.HttpPrincipal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if(cart == null){
            // 如果cart为空，说明用户未登录，跳转到登陆页面
            response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        Book book = bookService.queryBookById(id);
        CartItem item = new CartItem(book.getName(), 1, book.getPrice(), book.getId());

        cart.addItem(item);
        // 每次点击加入购物车后，更新书籍的销量及库存
        book.setStock(book.getStock() - 1);
        book.setSales(book.getSales() + 1);
        bookService.updateBook(book);
        // 重定向到当前页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request.getParameter(\"id\") = " + request.getParameter("id"));
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        try {
            cart.deleteItem(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        int defaultCount = WebUtils.parseInt(request.getParameter("defaultCount"), 1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateItem(id, count);
            Book book = bookService.queryBookById(id);
            book.setStock(book.getStock() - (count - defaultCount));
            book.setSales(book.getSales() + (count - defaultCount));
            bookService.updateBook(book);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

}
