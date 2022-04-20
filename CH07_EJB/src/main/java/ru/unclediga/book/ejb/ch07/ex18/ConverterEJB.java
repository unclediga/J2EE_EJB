package ru.unclediga.book.ejb.ch07.ex18;

import javax.annotation.Resource; 
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@Local(ConverterLocal.class)
@LocalBean
public class ConverterEJB implements ConverterLocal{

  @Resource(name ="rate")
  private Float rate;

  @Resource(name ="currency")
  private String currency;
  
  public Book18 convert(Book18 book){
    book.setCurrency(currency);
    book.setPrice(book.getPrice() * rate);      
    return book;
  }
}
