package com.hibernateSearch.models;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "tbl_book")
@Indexed
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;

	@Column(name = "title")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String title;

	@Column(name = "sub_title")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String subTitle;

	@Column(name = "publication_date")
	@Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
	private String publicationDate;

	@IndexedEmbedded
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	Collection<Author> author = new LinkedList<>();

	public Book() {

	}

	public Book(String title, String subTitle, String publicationDate) {
		super();
		this.title = title;
		this.subTitle = subTitle;
		this.publicationDate = publicationDate;
		
	}

	public Collection<Author> getAuthor() {
		return author;
	}

	public void setAuthor(Collection<Author> author) {
		this.author = author;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", subTitle=" + subTitle + ", publicationDate="
				+ publicationDate + "]";
	}

}
