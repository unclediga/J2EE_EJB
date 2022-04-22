package ru.unclediga.book.ejb.ch08.ex03;

public class Signal implements java.io.Serializable{
  private int sec;
  private String message;

  Signal(int sec, String message){
    this.sec = sec;
    this.message = message;
  }

  public int getSec(){
    return sec;
  }

  public String getMessage(){
    return message;
  }

}