package ru.unclediga.book.ejb.ch08.ex01;

import javax.ejb.Singleton;
import javax.annotation.PostConstruct;

import java.util.Map;
import java.util.HashMap;

@Singleton
public class CacheEJB {

  private Map<Integer,String> cache = new HashMap<>();

  @PostConstruct
  public void init(){
    cache.put(1, "First element");
    cache.put(2, "Second element");
  }

  public String get(Integer id){
    return cache.get(id);
  }

  public void remove(Integer id){
    cache.remove(id);
  }
}