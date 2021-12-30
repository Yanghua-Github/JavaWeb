package com.atguigu.web; /**
 * @author howardy
 * @date 2021/12/27 - 16:11
 */

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.sun.net.httpserver.HttpPrincipal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/manager/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        request.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将request域中的字段放入book中
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        // 存入数据库
        bookService.addBook(book);
        // 定义页面
        Integer pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1) + 1;
        // 重定向到list页面
        response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=getPage&pageNum=" + pageNum);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        bookService.deleteBookById(id);
        // 定义页面
        Integer pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=getPage&pageNum=" + pageNum);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        // 定义页面
        Integer pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=getPage&pageNum=" + pageNum);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?action=update&id=" + id).forward(request,response);
    }

    protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);

        Page page = bookService.getPage(pageNum, pageSize);

        page.setUrl("manager/BookServlet?action=getPage");

        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}
