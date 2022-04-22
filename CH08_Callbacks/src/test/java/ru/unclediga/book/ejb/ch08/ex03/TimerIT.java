package ru.unclediga.book.ejb.ch08.ex03;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Locale;

import java.io.File;

import java.util.logging.Logger;

import java.text.DateFormat;

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

  private String dat(){
    return DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.GERMAN).format(new Date());
  }

  @Test
  public void t1() throws Exception{
    assertNotNull(ctx.lookup("java:global/classes/ProgramTimerEJB"));
    log.info("=> START TimerIT "+ dat());
    
    ProgramTimerEJB t = (ProgramTimerEJB) ctx.lookup("java:global/classes/ProgramTimerEJB");
    log.info("=> START Signal(10,'Hello, World!') " + dat());
    t.createSignal(new Signal(10,"Hello, World!"));

    Thread.sleep(60 * 1000);
    
    log.info("=> STOP TimerIT " + dat());
  }
}