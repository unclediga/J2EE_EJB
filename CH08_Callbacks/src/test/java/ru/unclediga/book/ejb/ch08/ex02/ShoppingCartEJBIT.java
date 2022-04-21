package ru.unclediga.book.ejb.ch08.ex02;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;

import java.io.File;

public class ShoppingCartEJBIT {

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

  @Test(expected = javax.ejb.NoSuchEJBException.class)
  public void t1() throws Exception{
    assertNotNull(ctx.lookup("java:global/classes/ShoppingCartEJB"));
    ShoppingCartEJB cart = (ShoppingCartEJB)ctx.lookup("java:global/classes/ShoppingCartEJB");  


    cart.addItem("First element",10F);
    cart.addItem("Second element",20.5F);

    assertEquals(new Float(30.5F), cart.getTotal());

    Thread.sleep(100);
    System.out.println("=> 100 msec are out");

    assertEquals(new Float(30.5F), cart.getTotal());

    Thread.sleep(5000);
    System.out.println("=> 3000 msec are out");

    cart.checkout();

  }
}