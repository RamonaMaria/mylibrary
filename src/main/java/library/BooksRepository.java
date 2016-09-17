package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import com.mysql.jdbc.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;

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

		String insertTableSQL = "INSERT INTO tabael2" + " VALUES" + "(?,?,?)";
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(insertTableSQL,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setLong(1, book.getId());
		preparedStatement.setString(2, book.getNume());
		preparedStatement.setString(3, book.getAutor());

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

		String sql = "SELECT id, nume, prenume FROM tabael2";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Book book = new Book();
			
			book.id = rs.getLong("id");
			book.autor = rs.getString("prenume");
			book.nume = rs.getString("nume");

			books.add(book);
		}
		return books;
	}

	
	Book getBook (int id) throws SQLException {
		
		/*
		 * select * from tabael2
		   where id=801
		 */
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM tabael2 where id=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		return null;
		
	}
}
