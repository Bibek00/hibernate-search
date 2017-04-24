package com.hibernateSearch.DAOImpl;

import java.awt.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.hibernateSearch.DAO.SearchDAO;
import com.hibernateSearch.models.Author;
import com.hibernateSearch.models.Book;

public class SearchDAOImpl implements SearchDAO {

	@Override
	public void searchBook(String searchText) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			Transaction tx = fullTextSession.beginTransaction();
			QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
			org.apache.lucene.search.Query query = qb.keyword().onFields("title", "subTitle", "author.authorName")
					.matching(searchText).createQuery();

			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, Book.class);
			List result = (List) hibQuery.list();
			System.out.println(result.toString());
			tx.commit();
			e.printStackTrace();
		}

		session.getTransaction().commit();
		session.close();

	}

}
