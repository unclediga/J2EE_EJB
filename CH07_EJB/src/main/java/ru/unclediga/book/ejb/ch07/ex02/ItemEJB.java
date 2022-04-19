package ru.unclediga.book.ejb.ch07.ex02;

import ru.unclediga.book.ejb.ch07.Book;
import ru.unclediga.book.ejb.ch07.CD;
import static ru.unclediga.book.ejb.ch07.CD.FIND_ALL;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.SynchronizationType;
import javax.inject.Inject;

import java.util.List;

@Stateless
@LocalBean
public class ItemEJB implements ItemRemote, ItemLocal{

  @PersistenceContext(unitName = "chapter07PU")
  EntityManager em;

///////////////////////////////////////////////////////
  public Book create(Book book){
    em.persist(book);
    return book;
  }

  public Book update(Book book){
    return em.merge(book);
  }

  public Book findBookById(Long id){
    return em.find(Book.class, id);
  }

  public List<Book> findBookAll(){
    TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class); 
    return query.getResultList();
  }

  public void delete(Book book){
    em.remove(em.merge(book));
  }

  ///////////////////////////////////////////////////////
  public CD create(CD cd){
    em.persist(cd);
    return cd;
  }

  public CD update(CD cd){
    return em.merge(cd);
  }

  public CD findCDById(Long id){
    return em.find(CD.class, id);
  }

  public List<CD> findCDAll(){
    TypedQuery<CD> query = em.createNamedQuery(CD.FIND_ALL, CD.class); 
    return query.getResultList();
  }

  public void delete(CD cd){
    em.remove(em.merge(cd));
  }

}
