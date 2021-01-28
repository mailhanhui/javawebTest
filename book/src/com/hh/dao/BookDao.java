package com.hh.dao;

import com.hh.pojo.Book;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 16 - 20:17
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();
    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
