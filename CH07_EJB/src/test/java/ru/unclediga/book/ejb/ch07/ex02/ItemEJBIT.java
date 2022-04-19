package ru.unclediga.book.ejb.ch07.ex02;

import ru.unclediga.book.ejb.ch07.Book;
import ru.unclediga.book.ejb.ch07.CD;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import java.io.File;

public class ItemEJBIT {

  private static EJBContainer ec;
  private static Context ctx;

  @BeforeClass
  public static void init() throws Exception {
    Map<String,Object> p = new HashMap<>();
    p.put(EJBContainer.MODULES, new File("target/classes"));
    ec = EJBContainer.createEJBContainer(p);
    ctx = ec.getContext();

    System.out.println("System.getProperties() => ");
    for ( Object property : System.getProperties().keySet() ) {
      System.out.print(property + " : " + System.getProperty((String)property));
    }
  }  

  @AfterClass
  public static void close() throws Exception {
    if(ctx != null) {
      ctx.close();
    };
    if(ec != null) {
      ec.close();
    };
  } 

  @Test
  public void t1() throws Exception {

    Book book = new Book();
    book.setTitle("The Hitchhiker's Guide to the Galaxy");
    book.setPrice(12.5F);
    book.setDescription("Science fiction comedy book");
    book.setIsbn("1-84023-742-2");
    book.setNbOfPage(354);
    book.setIllustrations(false);

    List<Book> books = null;

    assertNotNull(ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemRemote"));
    assertNotNull(ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemLocal"));
    assertNotNull(ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemEJB"));

    ItemRemote ejbR = (ItemRemote)ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemRemote");

    book = ejbR.create(book);
    assertNotNull(book.getId());
    System.out.println("id => " + book.getId());
    book = ejbR.findBookById(book.getId());
    System.out.println("find by id -> " + book);
    assertNotNull(book);    

    books = ejbR.findBookAll();
    assertEquals(1, books.size());

    ItemLocal ejbL = (ItemLocal)ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemLocal");

    book = ejbL.findBookById(book.getId());
    System.out.println("find by id -> " + book);
    assertNotNull(book);    

    books = ejbL.findBookAll();
    assertEquals(1, books.size());

    ItemEJB ejb = (ItemEJB)ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemEJB");

    books = ejb.findBookAll();
    assertEquals(1, books.size());
  }


  @Test
  public void t2() throws Exception {

    Map<Integer, String> tracks = new HashMap<>();
    tracks.put(1,  "Thunderstruck"); 
    tracks.put(2,  "Fire Your Guns" ); 
    tracks.put(3,  "Moneytalks" ); 
    tracks.put(4,  "The Razors Edge"); 
    tracks.put(5,  "Mistress for Christmas" ); 
    tracks.put(6,  "Rock Your Heart Out"); 
    tracks.put(7,  "Are You Ready"); 
    tracks.put(8,  "Got You by the Balls" ); 
    tracks.put(9,  "Shot of Love" ); 
    tracks.put(10, "Let's Make It"); 
    tracks.put(11, "Goodbye & Good Riddance to Bad Luck"); 
    tracks.put(12, "If You Dare");
    CD cd1 = new CD("The Razors Edge", 25.5f, "Released: 21 September 1990", tracks);


    tracks.clear(); 
    tracks.put(1, "It's a Long Way to the Top (If You Wanna Rock 'n' Roll)");
    tracks.put(2, "Rock 'n' Roll Singer" );
    tracks.put(3, "The Jack" );
    tracks.put(4, "Live Wire");
    tracks.put(5, "T.N.T." );
    tracks.put(6, "Can I Sit Next to You Girl (A. Young, M. Young)");
    tracks.put(7, "Little Lover" );
    tracks.put(8, "She's Got Balls");
    tracks.put(9, "High Voltage" );
    CD cd2 = new CD("High Voltage", 25.5f, "Released: 14 May 1976", tracks);


    ItemRemote ejbR = (ItemRemote)ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemRemote");
    
    CD cd = null;  
    List<CD> cds = null;

    cd = ejbR.create(cd1);
    assertNotNull(cd.getId());
    System.out.println("CD.id => " + cd.getId());
    cd = ejbR.create(cd2);
    assertNotNull(cd.getId());
    System.out.println("CD.id => " + cd.getId());

    cd = ejbR.findCDById(cd.getId());
    System.out.println("find CD by id -> " + cd);
    assertNotNull(cd);    

    cds = ejbR.findCDAll();
    assertEquals(2, cds.size());

    ItemLocal ejbL = (ItemLocal)ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemLocal");

    cd = ejbL.findCDById(cd.getId());
    System.out.println("find by id -> " + cd);
    assertNotNull(cd);    

    cds = ejbL.findCDAll();
    assertEquals(2, cds.size());
    
    ItemEJB ejb = (ItemEJB)ctx.lookup("java:global/classes/ItemEJB!ru.unclediga.book.ejb.ch07.ex02.ItemEJB");

    cds = ejb.findCDAll();
    assertEquals(2, cds.size());
  }  

}