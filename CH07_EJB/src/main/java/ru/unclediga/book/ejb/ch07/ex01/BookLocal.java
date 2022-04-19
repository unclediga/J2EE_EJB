package ru.unclediga.book.ejb.ch07.ex01;

import ru.unclediga.book.ejb.ch07.Book;

import java.util.List;

public interface BookLocal{
  public Book create(Book book);
  public Book findById(Long id);
  public List<Book> findAll();
}