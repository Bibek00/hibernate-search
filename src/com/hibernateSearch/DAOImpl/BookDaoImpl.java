package com.hibernateSearch.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernateSearch.DAO.BookDao;
import com.hibernateSearch.models.Book;

public class BookDaoImpl implements BookDao {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@Override
	public void insert(Book book) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.persist(book);

		session.getTransaction().commit();
		session.close();

	}

}
