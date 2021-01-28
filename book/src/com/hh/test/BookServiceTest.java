package com.hh.test;

import com.hh.pojo.Book;
import com.hh.service.BookService;
import com.hh.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 16 - 22:42
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"他来自湖南","莫沫儿",new BigDecimal(58.88),1800,10,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"他来自湖南长沙","莫沫儿11",new BigDecimal(58.88),1800,10,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(2, 4));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(2, 4,10,50));
    }
}