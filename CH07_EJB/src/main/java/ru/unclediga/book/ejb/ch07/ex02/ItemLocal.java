package ru.unclediga.book.ejb.ch07.ex02;

import ru.unclediga.book.ejb.ch07.Book;
import ru.unclediga.book.ejb.ch07.CD;

import javax.ejb.Local;

import java.util.List;

@Local
public interface ItemLocal{
  public Book findBookById(Long id);
  public List<Book> findBookAll();

  public CD findCDById(Long id);
  public List<CD> findCDAll();


}