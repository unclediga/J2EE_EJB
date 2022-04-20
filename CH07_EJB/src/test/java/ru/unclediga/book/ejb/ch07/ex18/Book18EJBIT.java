package ru.unclediga.book.ejb.ch07.ex18;


import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import java.io.File;

public class Book18EJBIT {

  private static EJBContainer ec;
  private static Context ctx;

  @BeforeClass
  public static void init() throws Exception {
    Map<String,Object> p = new HashMap<>();
    p.put(EJBContainer.MODULES, new File("target/classes"));
    ec = EJBContainer.createEJBContainer(p);
    ctx = ec.getContext();
  }  

  @AfterClass
  public static void close() throws Exception {
    if(ctx != null) {
      ctx.close();
    };
    if(ec != null) {
      ec.close();
    };
  } 

  @Test
  public void t00() throws Exception {

    assertNotNull(ctx.lookup("java:global/classes/ConverterEJB!ru.unclediga.book.ejb.ch07.ex18.ConverterEJB"));
    assertNotNull(ctx.lookup("java:global/classes/ConverterEJB!ru.unclediga.book.ejb.ch07.ex18.ConverterLocal"));

    ConverterEJB e = (ConverterEJB)ctx.lookup("java:global/classes/ConverterEJB!ru.unclediga.book.ejb.ch07.ex18.ConverterEJB");

    Book18 b = e.convert(new Book18("test book", 1.0F, "USD"));
    assertEquals(new Float(1.5F), b.getPrice());
    assertEquals("EUR", b.getCurrency());

  } 

  @Test
  public void t01() throws Exception {

    assertNotNull(ctx.lookup("java:global/classes/Book18EJB!ru.unclediga.book.ejb.ch07.ex18.Book18EJB"));
    assertNotNull(ctx.lookup("java:global/classes/Book18EJB!ru.unclediga.book.ejb.ch07.ex18.Book18Local"));
  } 

  @Test
  public void t1() throws Exception {

    Book18 book = new Book18();
    book.setTitle("The Hitchhiker's Guide to the Galaxy");
    book.setPrice(12.5F);
    book.setCurrency("USD");

    Book18EJB ejb = (Book18EJB)ctx.lookup("java:global/classes/Book18EJB!ru.unclediga.book.ejb.ch07.ex18.Book18EJB");
    Book18Local ejbL = (Book18Local)ctx.lookup("java:global/classes/Book18EJB!ru.unclediga.book.ejb.ch07.ex18.Book18Local");

    book = ejb.create(book);
    assertNotNull(book.getId());

    book = ejb.findById(book.getId());
    assertEquals(new Float(12.5F), book.getPrice());

    book = ejbL.convertPrice(book);
    assertEquals(new Float(12.5F * 1.5), book.getPrice());
  }
}