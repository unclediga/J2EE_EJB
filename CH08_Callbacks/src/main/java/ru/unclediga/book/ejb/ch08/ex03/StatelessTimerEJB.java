package ru.unclediga.book.ejb.ch08.ex03;

import javax.ejb.Stateless;
import javax.ejb.Schedule;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;


@Stateless
public class StatelessTimerEJB {

  Logger log = Logger.getLogger(StatelessTimerEJB.class.getName());

  @PostConstruct
  public void init(){
    log.info("=> StatelessTimerEJB started");
  }

  @PreDestroy
  public void stop(){
    log.info("=> StatelessTimerEJB stopped");
  }

 @Schedule(minute = "*/1", hour = "*")
  public void runEveryMinute() {
    log.log(Level.INFO,"running every minute .. now it's: " + new Date().toString());
 }
}