package ru.unclediga.book.ejb.ch07.ex02;

import ru.unclediga.book.ejb.ch07.Book;
import ru.unclediga.book.ejb.ch07.CD;

import javax.ejb.Remote;

import java.util.List;

@Remote
public interface ItemRemote{
  public Book create(Book book);
  public Book update(Book book);
  public Book findBookById(Long id);
  public List<Book> findBookAll();

  public CD create(CD cd);
  public CD update(CD cd);
  public CD findCDById(Long id);
  public List<CD> findCDAll();


}