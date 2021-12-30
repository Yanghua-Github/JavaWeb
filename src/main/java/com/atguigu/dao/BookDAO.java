package com.atguigu.dao;

import com.atguigu.dao.impl.BaseDAO;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/27 - 15:37
 */
public interface BookDAO {
    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBookById(Book book);

    public Book queryBookById(int id);

    public List<Book> queryBooks();

    List<Book> queryBooksByOffset(Integer offset, Integer pageSize);

    int queryTotalCount();

    List<Book> queryBooksByPrice(int min, int max, int pageNum, int pageSize);

    int queryTotalCountByPrice(int min, int max);
}
