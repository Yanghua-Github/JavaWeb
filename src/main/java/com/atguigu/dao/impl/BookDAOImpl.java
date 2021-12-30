package com.atguigu.dao.impl;

import com.atguigu.dao.BookDAO;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/27 - 15:43
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) " +
                "values(?, ?, ?, ?, ?, ?);";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBookById(Book book) {
        String sql = "update t_book set name=?, author=?, price=?, sales=?, stock=?, img_path=? where id=?;";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select id, name, author, price, sales, stock, img_path as imgPath from t_book where id=?;";
        return queryForOne(sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id, name, author, price, sales, stock, img_path as imgPath from t_book;";
        return queryForList(sql);
    }

    @Override
    public List<Book> queryBooksByOffset(Integer offset, Integer pageSize) {
        String sql = "select id, name, author, price, sales, stock, img_path as imgPath from t_book limit ?,?;";
        return queryForList(sql, offset, pageSize);
    }

    @Override
    public int queryTotalCount() {
        String sql = "select count(*) from t_book;";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryBooksByPrice(int min, int max, int pageNum, int pageSize) {
        String sql = "select id, name, author, price, sales, stock, img_path as imgPath from t_book where price between ? and ? limit ?,?;";
        return queryForList(sql, min, max, pageNum, pageSize);
    }

    @Override
    public int queryTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?;";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }
}
