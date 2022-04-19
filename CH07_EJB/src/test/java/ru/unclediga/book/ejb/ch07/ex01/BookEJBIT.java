package ru.unclediga.book.ejb.ch07.ex01;

import ru.unclediga.book.ejb.ch07.Book;

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

public class BookEJBIT {

  private static EJBContainer ec;
  private static Context ctx;

  @BeforeClass
  public static void init() throws Exception {
    Map<String,Object> p = new HashMap<>();
    p.put(EJBContainer.MODULES, new File("target/classes"));
    ec = EJBContainer.createEJBContainer(p);
    ctx = ec.getContext();

    System.out.println("System.getProperties() => ");
    for ( Object property : System.getProperties().keySet() ) {
      System.out.print(property + " : " + System.getProperty((String)property));
    }
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
  public void t1() throws Exception {

    Book book = new Book();
    book.setTitle("The Hitchhiker's Guide to the Galaxy");
    book.setPrice(12.5F);
    book.setDescription("Science fiction comedy book");
    book.setIsbn("1-84023-742-2");
    book.setNbOfPage(354);
    book.setIllustrations(false);

    assertNotNull(ctx.lookup("java:global/classes/BookEJB"));

    BookEJB ejb = (BookEJB)ctx.lookup("java:global/classes/BookEJB");

    book = ejb.create(book);
    assertNotNull(book.getId());
    System.out.println("id => " + book.getId());
    book = ejb.findById(book.getId());
    System.out.println("find by id -> " + book);
    assertNotNull(book);    

    List<Book> books = ejb.findAll();
    assertNotNull(books);    
    assertTrue(books.size() > 0);
  }

  @Test
  public void t2() throws Exception {

    Book book = new Book();
    book.setTitle("The Hitchhiker's Guide to the Galaxy");
    book.setPrice(12.5F);
    book.setDescription("Science fiction comedy book");
    book.setIsbn("1-84023-742-2");
    book.setNbOfPage(354);
    book.setIllustrations(false);

    assertNotNull(ctx.lookup("java:global/classes/BookEJBx"));

    BookLocal ejb = (BookLocal)ctx.lookup("java:global/classes/BookEJBx");

    book = ejb.create(book);
    assertNotNull(book.getId());
    System.out.println("id => " + book.getId());
    book = ejb.findById(book.getId());
    System.out.println("find by id -> " + book);
    assertNotNull(book);    

    List<Book> books = ejb.findAll();
    assertNotNull(books);    
    assertTrue(books.size() > 0);
  }

}