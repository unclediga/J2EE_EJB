package ru.unclediga.book.ejb.ch07.ex01;

import ru.unclediga.book.ejb.ch07.Book;

import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.SynchronizationType;
import javax.inject.Inject;

import java.util.List;

@Stateless
@Local(BookLocal.class)
public class BookEJBx implements BookLocal{

  @PersistenceContext(unitName = "chapter07PU")
  //@PersistenceContext(unitName = "chapter07PU", synchronization = SynchronizationType.UNSYNCHRONIZED)
  //@Inject
  EntityManager em;

  public Book create(Book book){
    em.persist(book);
    return book;
  }

  public Book findById(Long id){
    return em.find(Book.class, id);
  }

  public List<Book> findAll(){
    TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class); 
    return query.getResultList();
  }
}
