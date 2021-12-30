package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/27 - 15:59
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    List<Book> queryBooksByOffset(Integer offset, Integer pageSize);

    int queryTotalCount();

    Page getPage(Integer pageNum, Integer pageSize);

    Page<Book> getPageByPrice(int min, int max, int pageNum, int pageSize);
}
