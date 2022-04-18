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
    assertNotNull(ctx.lookup("java:global/classes/BookEJB!ru.unclediga.book.ejb.ch07.ex01.BookEJB"));
    assertNotNull(ctx.lookup("java:global/classes/BookEJB"));
  }
}