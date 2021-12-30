package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/27 - 21:36
 */

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientBookServlet", value = "/client/BookServlet")
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);

        Page page = bookService.getPage(pageNum, pageSize);
        page.setUrl("client/BookServlet?action=getPage");

        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void getPageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        Page<Book> page = bookService.getPageByPrice(min, max, pageNum, pageSize);
        System.out.println(page);
        StringBuilder sb = new StringBuilder("client/BookServlet?action=getPageByPrice");

        if(request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(String.valueOf(sb));
        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
}
