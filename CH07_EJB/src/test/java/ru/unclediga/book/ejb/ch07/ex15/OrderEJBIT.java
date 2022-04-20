package ru.unclediga.book.ejb.ch07.ex15;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.concurrent.Future;

import java.util.Map;
import java.util.HashMap;

import java.io.File;

public class OrderEJBIT {
  private static EJBContainer ec;
  private static Context ctx;
  private static final long SLEEP_TIME = 100;

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
  public void t1() throws Exception {
    assertNotNull(ctx.lookup("java:global/classes/OrderEJB"));
    OrderEJB ejb = (OrderEJB)ctx.lookup("java:global/classes/OrderEJB");
    long ct = System.currentTimeMillis();
    ejb.printSync(SLEEP_TIME);
    ct = System.currentTimeMillis() - ct;
    assertTrue(ct >= SLEEP_TIME);
  }

  @Test(timeout = SLEEP_TIME)
  public void t2() throws Exception {
    assertNotNull(ctx.lookup("java:global/classes/OrderEJB"));
    OrderEJB ejb = (OrderEJB)ctx.lookup("java:global/classes/OrderEJB");
    ejb.printAsync(SLEEP_TIME);
  }

  @Test(timeout = SLEEP_TIME * 2)
  public void t3() throws Exception {
    assertNotNull(ctx.lookup("java:global/classes/OrderEJB"));
    OrderEJB ejb = (OrderEJB)ctx.lookup("java:global/classes/OrderEJB");
    Future<Integer> f = ejb.printCancelled(SLEEP_TIME);
    boolean res = f.cancel(true);
    System.err.println("===> res = " + res);
    assertEquals(new Integer(2), f.get());
  }
}