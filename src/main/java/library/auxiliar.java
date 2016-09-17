//package library;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.xml.transform.Result;
//
//import com.mysql.jdbc.PreparedStatement;
//
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class BooksRepository {
//
//	Connection connection = null;
//	PreparedStatement stmt1 = null;
//
//	// fac conectarea direct cand construiesc obiectul
//	public BooksRepository() {
//		System.out.println("-------- MySQL JDBC Connection Testing ------------");
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Where is your MySQL JDBC Driver?");
//			e.printStackTrace();
//			return;
//		}
//
//		System.out.println("MySQL JDBC Driver Registered!");
//		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test", "root", "1234");
//
//		} catch (SQLException e) {
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//		}
//
//		if (connection != null) {
//			System.out.println("You made it, take control your database now!");
//		} else {
//			System.out.println("Failed to make connection!");
//		}
//
//	}
//
//	// ------------ METODELE NECESARE --------------------
//
//	Book addBook(Book book) throws SQLException {
//		Statement stmt = connection.createStatement();
//		//stmt.executeUpdate("insert into tabel values (" + book.id + ", '" + book.nume + "', '" + book.autor + "')");
//		
//		int result = stmt.executeUpdate("insert into tabael2 (nume, prenume) values ('" +  book.nume + "', '" + book.autor + "')");
//		
//	    String sql = "UPDATE tabael2 set nume=? WHERE id=?";
//	    stmt1 =  connection.prepareStatement(sql);
//
//		
//		if (result == 0) {
//			throw new SQLException("Failed attempt :( [In database was no change]");
//		}
//		
//		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
//			if (generatedKeys.next()) {
//				
//				System.out.println("generatedKeys.getLong(1) = " + generatedKeys.getLong(1));
//				book.setId(generatedKeys.getLong(1));
//			}
//			else {
//				throw new SQLException("Failed attempt, no ID obtained");
//			}
//
//			
//		} catch (Exception e) {
//			System.out.println(":::::::::" + e);
//		}
//		
//		System.out.println("----------------------------------------");
//		System.out.println(book.getId() + " " + book.nume + " " + book.autor);
//		System.out.println("----------------------------------------");
//
//		
//		return book;
//
//
//	}
//}
