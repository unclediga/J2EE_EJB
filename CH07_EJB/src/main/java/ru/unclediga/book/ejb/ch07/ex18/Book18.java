package ru.unclediga.book.ejb.ch07.ex18;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Book18{

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String title;
  private Float price;
  private String currency;

  public Book18() {
  }

  public Book18(String title, Float price, String currency) {
    this.title = title;
    this.price = price;
    this.currency = currency;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Book18");
    sb.append("{id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", price=").append(price);
    sb.append(", curr='").append(currency);
    sb.append('}');
    return sb.toString();
  }
}