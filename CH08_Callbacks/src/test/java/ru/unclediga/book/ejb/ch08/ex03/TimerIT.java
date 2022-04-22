package ru.unclediga.book.ejb.ch08.ex03;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;

import java.io.File;

import java.util.logging.Logger;

public class TimerIT {

  private static EJBContainer ec;
  private static Context ctx;
  Logger log = Logger.getLogger(TimerIT.class.getName());
  

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
    log.info("=> START TimerIT");
    Thread.sleep(60 * 1000);
    log.info("=> STOP TimerIT");
  }
}