package com.atguigu.service.impl;

import com.atguigu.dao.BookDAO;
import com.atguigu.dao.impl.BaseDAO;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/27 - 16:01
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDAO.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBookById(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDAO.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    public List<Book> queryBooksByOffset(Integer offset, Integer pageSize) {
        return bookDAO.queryBooksByOffset(offset, pageSize);
    }

    @Override
    public int queryTotalCount() {
        return bookDAO.queryTotalCount();
    }

    @Override
    public Page<Book> getPage(Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<Book>();

        int itemstotalCount = queryTotalCount();
        int pageTotalNum = itemstotalCount / pageSize;
        page.setPageSize(pageSize);
        page.setItemsTotalNum(itemstotalCount);
        if(itemstotalCount % pageSize != 0){
            pageTotalNum += 1;
        }
        page.setPageTotalNum(pageTotalNum);
        page.setPageNum(pageNum);
        List<Book> books = queryBooksByOffset((page.getPageNum() - 1) * page.getPageSize(), page.getPageSize());
        page.setItems(books);
        return page;
    }

    @Override
    public Page<Book> getPageByPrice(int min, int max, int pageNum, int pageSize) {
        Page<Book> page = new Page<Book>();
        int itemstotalCount = bookDAO.queryTotalCountByPrice(min, max);
        int pageTotalNum = itemstotalCount / pageSize;
        page.setPageSize(pageSize);
        page.setItemsTotalNum(itemstotalCount);
        if((pageTotalNum == 0 && itemstotalCount != 0) || itemstotalCount % pageSize != 0){
            pageTotalNum += 1;
        }
        page.setPageTotalNum(pageTotalNum);
        page.setPageNum(pageNum);

        List<Book> books = bookDAO.queryBooksByPrice(min, max, (pageNum - 1) * pageSize, pageSize);
        page.setItems(books);
        return page;

    }
}
