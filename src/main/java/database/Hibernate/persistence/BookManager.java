package database.Hibernate.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import database.Hibernate.entity.Book;

public class BookManager {

	protected SessionFactory sessionFactory;

	protected void setUp()  {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings
																									// from
																									// hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	
	protected void exit() {
		sessionFactory.close();
	}
	
	protected void create() {
		Book book = new Book();
		book.setTitle("Como ser um Milionrio");
		book.setAuthor("Napoleon");
		book.setPrice(32.59f);
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(book);
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	protected void read() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		long bookId = 2;
		Book book = session.get(Book.class, bookId);
		
		if(book != null) {
			System.out.println("Titlte: "+  book.getTitle());
			System.out.println("Author: "+  book.getAuthor());
			System.out.println("Price: "+  book.getPrice());
		}else {
			System.out.println("Book could not be found");
		}
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	protected void update() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Como ser um Pobre");
		book.setAuthor("Napoleon");
		book.setPrice(32.59f);
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(book);
		
		session.getTransaction().commit();
		session.close();
	}
	
	protected void delete() {
		Book book = new Book();
		book.setId(1);
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(book);
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setUp();
		manager.create();
		manager.exit();
		
	}

}
