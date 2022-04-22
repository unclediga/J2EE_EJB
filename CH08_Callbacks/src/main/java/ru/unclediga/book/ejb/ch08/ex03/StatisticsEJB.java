package ru.unclediga.book.ejb.ch08.ex03;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.Date;
import java.util.logging.Logger;

@Singleton
@Startup
public class StatisticsEJB {

  Logger log = Logger.getLogger(StatisticsEJB.class.getName());


  @PostConstruct
  public void init(){
    log.info("=> TIMER started LOG");
  }

  @PreDestroy
  public void stop(){
    log.info("=> TIMER stopped LOG");
  }

  @Schedule(second="30/10", minute ="*", hour="*")
  public void stat10From30sec(){
    log.info("=> TIMER: 30/10 sec : " + new Date().toString());
  }

  @Schedules({
    @Schedule(minute="*/1", hour="*"),
    @Schedule(second="15,45", minute ="*", hour="*")
  })
  public void stat15secAnd45secAnd1min(){
    log.info("=> TIMER: 15 sec + 45 sec + 1 min : " + new Date().toString());
  }
}