package com.hh.service;

import com.hh.pojo.Book;
import com.hh.pojo.Page;

import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 16 - 22:33
 * <p>
 * Description:
 * 1.
 * 2.
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    Page<Book> page(int pageNO, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
