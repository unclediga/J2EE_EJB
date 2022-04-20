package ru.unclediga.book.ejb.ch07.ex18;

import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.SynchronizationType;
import javax.inject.Inject;

import java.util.List;

@Stateless
@Local(Book18Local.class)
@LocalBean
public class Book18EJB implements Book18Local{
  @PersistenceContext(unitName = "chapter07PU")
  EntityManager em;

  @EJB 
  private ConverterEJB conv;

  public Book18 create(Book18 book){
    em.persist(book);
    return book;
  }

  public Book18 findById(Long id){
    return em.find(Book18.class, id);
  }

  public Book18 convertPrice(Book18 book){
    return conv.convert(book);
  }
}