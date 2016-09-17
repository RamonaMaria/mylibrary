package library;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	String nume;
	String autor;
	long id;

	public Book() {
	}

	public Book(String nume, String autor, long id) {
		this.nume = nume;
		this.autor = autor;
		this.id = id;
	}

	@XmlElement
	public void setNume(String nume) {
		this.nume = nume;
	}

	@XmlElement
	public void setAutor(String autor) {
		this.autor = autor;
	}

	@XmlElement
	public void setId(long l) {
		this.id = l;
	}

	
	@XmlElement
	public String getNume() {
		return nume;
	}

	@XmlElement
	public String getAutor() {
		return autor;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public String toString() {
		return "(id: " + id + " nume: " + nume + ", autor: " + autor + ")";
	}
}