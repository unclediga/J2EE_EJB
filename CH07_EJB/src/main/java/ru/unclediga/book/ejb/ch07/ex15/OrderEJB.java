package ru.unclediga.book.ejb.ch07.ex15;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.SessionContext;
import javax.ejb.AsyncResult;

import java.util.concurrent.Future;

import javax.annotation.Resource; 

@Stateless
public class OrderEJB {

  @Resource 
  SessionContext ctx;
  
  public void printSync(long stime) throws Exception{
    Thread.sleep(stime);
  }

  @Asynchronous
  public void printAsync(long stime) throws Exception{
    Thread.sleep(stime);
  }

  @Asynchronous
  public Future<Integer> printCancelled(long stime) throws Exception{
    int status = 0;
    Thread.sleep(stime);
    status = 1;
    if(ctx.wasCancelCalled()){
      return new AsyncResult(2);
    }
    Thread.sleep(stime);
    return new AsyncResult(status);
  }
}