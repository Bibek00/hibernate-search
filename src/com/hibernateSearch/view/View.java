package com.hibernateSearch.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.hibernateSearch.DAO.BookDao;
import com.hibernateSearch.DAO.SearchDAO;
import com.hibernateSearch.DAOImpl.BookDaoImpl;
import com.hibernateSearch.DAOImpl.SearchDAOImpl;
import com.hibernateSearch.models.Author;
import com.hibernateSearch.models.Book;

public class View {
	BookDao bookDaoImpl = new BookDaoImpl();
	SearchDAO searchDaoImpl = new SearchDAOImpl();

	public View() {

		SwitchDisplay();
	}

	public void SwitchDisplay() {
		
		Scanner sc = new Scanner(System.in);
		String choiceCont;
		do {

			System.out.println("*****---     Menu      ---*****");
			System.out.println("\n1. Add a Student Details");
			System.out.println("2. Display all Student Details");
			System.out.println("3. Enter the word you want to search");
			System.out.println("4. Enter the letter you want to search by:");
			System.out.println("5. Exit");

			System.out.println("\n Enter choice you want to enter");

			switch (sc.nextInt()) {

			case 1:
				Book book = new Book();
				Author author = new Author();
//				List<Book> bookList = new LinkedList<>();
 					
				System.out.println("Enter book title");
				book.setTitle(sc.next());

				System.out.println("Enter book Sub-title");
				book.setSubTitle(sc.next());

				System.out.println("Enter publication date");
				book.setPublicationDate(sc.next());

				System.out.println("Enter Author name");
				author.setAuthorName(sc.next());

			
				book.getAuthor().add(author);
				bookDaoImpl.insert(book);

				break;
			case 3:
				System.out.println("Enter text you want to search");
				searchDaoImpl.searchBook(sc.next());
				break;

			case 5:
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}

			System.out.println("Do you want to continue(Y/y)");
			choiceCont = sc.next();
		} while (choiceCont.equalsIgnoreCase("Y"));

	}
}
