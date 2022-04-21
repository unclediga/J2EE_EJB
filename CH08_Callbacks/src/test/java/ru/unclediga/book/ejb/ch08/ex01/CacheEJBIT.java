package ru.unclediga.book.ejb.ch08.ex01;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;

import java.io.File;

public class CacheEJBIT {

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
  public void t1() throws Exception{
    assertNotNull(ctx.lookup("java:global/classes/CacheEJB"));
    CacheEJB cache = (CacheEJB)ctx.lookup("java:global/classes/CacheEJB");  

    assertEquals("First element", cache.get(1));
    assertEquals("Second element", cache.get(2));

    cache.remove(1);

    assertNull(cache.get(1));
    assertEquals("Second element", cache.get(2));
  }
}