package library;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	String author;
	String book;
	String genre;
	long id;

	public Book() {
	}

	public Book(String nume, String autor, String genre, long id) {
		this.author = nume;
		this.book = autor;
		this.genre = genre;
		this.id = id;
	}

	@XmlElement
	public void setBook(String book) {
		this.book = book;
	}

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	@XmlElement
	public void setId(long l) {
		this.id = l;
	}

	@XmlElement
	public String getAuthor() {
		return author;
	}

	@XmlElement
	public String getBook() {
		return book;
	}
	
	@XmlElement
	public String getGenre() {
		return genre;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public String toString() {
		return "(id: " + id + " author: " + author + ", book: " + book + " genre: " + genre + ")";
	}
}