package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class BooksRepository {

	Connection connection = null;
	PreparedStatement stmt1 = null;

	// fac conectarea direct cand construiesc obiectul
	public BooksRepository() {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test", "root", "1234");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	// ------------ METODELE NECESARE --------------------

	Book addBook(Book book) throws SQLException {

		String insertTableSQL = "INSERT INTO books" + " VALUES" + "(?,?,?,?)";
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(insertTableSQL,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setLong(1, book.getId());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setString(3, book.getBook());
		preparedStatement.setString(4, book.getGenre());

		int result = preparedStatement.executeUpdate();

		if (result == 0) {
			throw new SQLException("Failed attempt! :( ");
		}

		ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		if (generatedKeys.next()) {
			book.setId(generatedKeys.getLong(1));
		} else {
			throw new SQLException("Failed attempt! :( ");
		}

		return book;
	}

	List<Book> getBooks() throws SQLException {

		List<Book> books = new ArrayList<Book>();
		Statement stmt = connection.createStatement();

		String sql = "SELECT id, author, book, genre FROM books";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Book book = new Book();

			book.id = rs.getLong("id");
			book.author = rs.getString("author");
			book.book = rs.getString("book");
			book.genre = rs.getString("genre");

			books.add(book);
		}
		return books;
	}

	Book getBook(int id) throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM books where id=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		Book book = new Book();
		if (rs.next()) {
			book.id = rs.getLong("id");
			book.author = rs.getString("author");
			book.book = rs.getString("book");
			book.genre = rs.getString("genre");
		}

		return book;
	}

	void deleteBook(int id) throws SQLException {

		String deleteSQL = "delete from books where id=?";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(deleteSQL);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	void updateBook(int id, Book newBook) throws SQLException {

		String updateSQL = "UPDATE books set author=?, book=?, genre=? where id=" + id;
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(updateSQL);
		stmt.setString(1, newBook.author);
		stmt.setString(2, newBook.book);
		stmt.setString(3, newBook.genre);

		stmt.executeUpdate();
	}

	// ****************************
	List<Book> getBooksAuthorGenre(String author, String genre) throws SQLException {

		List<Book> books = new ArrayList<Book>();
		Statement stmt = connection.createStatement();
		if (author == null && genre != null) {

			String sql = "SELECT * FROM books where genre='" + genre + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Book book = new Book();

				book.id = rs.getLong("id");
				book.author = rs.getString("author");
				book.book = rs.getString("book");
				book.genre = rs.getString("genre");

				books.add(book);
			}
			return books;
		}

		if (genre == null) {
			String sql = "select * from books where author='" + author + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Book book = new Book();

				book.id = rs.getLong("id");
				book.author = rs.getString("author");
				book.book = rs.getString("book");
				book.genre = rs.getString("genre");

				books.add(book);
			}
			return books;

		} else {
			String sql = "select * from books where author='" + author + "'&& genre='" + genre + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Book book = new Book();

				book.id = rs.getLong("id");
				book.author = rs.getString("author");
				book.book = rs.getString("book");
				book.genre = rs.getString("genre");

				books.add(book);
			}
			return books;
		}
	}

}
