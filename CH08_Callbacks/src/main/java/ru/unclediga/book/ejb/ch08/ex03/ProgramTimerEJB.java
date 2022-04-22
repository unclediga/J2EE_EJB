package ru.unclediga.book.ejb.ch08.ex03;

import javax.ejb.Stateless;
import javax.ejb.ScheduleExpression;
import javax.ejb.TimerService;
import javax.ejb.Timer;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;


import javax.annotation.Resource;

import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;


import java.text.DateFormat;

@Stateless
public class ProgramTimerEJB {

  Logger log = Logger.getLogger(ProgramTimerEJB.class.getName());

  @Resource
  TimerService srv;


  private String dat(){
    return DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.GERMAN).format(new Date());
  }
  
  public void createSignal(Signal s) {
    ScheduleExpression se = new ScheduleExpression().second("*/10").minute("*").hour("*");
    srv.createCalendarTimer(se, new TimerConfig(s, true));
  } 

  @Timeout
  public void signalMessage(Timer timer) {
    Signal s = (Signal)timer.getInfo();
    log.info("GET signal [" + s.getMessage() + "] " + dat());
 }
}