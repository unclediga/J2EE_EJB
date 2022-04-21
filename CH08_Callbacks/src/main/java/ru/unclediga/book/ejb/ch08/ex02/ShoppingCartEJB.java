package ru.unclediga.book.ejb.ch08.ex02;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.Remove;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import static java.util.concurrent.TimeUnit.*;

import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Stateful
@StatefulTimeout(value = 1L, unit = SECONDS)
public class ShoppingCartEJB {

  @Resource(lookup="java:comp/DefaultDataSource")
  private DataSource ds;
  private Connection conn = null;
  private Map<String,Float> cart = new HashMap<>();

  @PostConstruct
  @PostActivate
  public void init(){
    System.out.println("=> CONSTR");
    try{
      conn = ds.getConnection();
    }catch(SQLException e){
      e.printStackTrace();
    }
  }

  @PreDestroy
  @PrePassivate
  public void close(){
    System.out.println("=> DESTR");
    try{
      conn.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
  }

  public void addItem(String item, Float price){
    cart.put(item, price);
  }

  public Float getTotal(){
    float sum = 0;
    for(String i : cart.keySet()){
      sum += cart.get(i);
    }
    return sum;
  }

  @Remove
  public Float checkout(){
    float sum = getTotal();
    cart.clear();
    return sum;
  }
}