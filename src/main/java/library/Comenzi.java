//package library;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import com.mysql.jdbc.PreparedStatement;
//
//public class Comenzi {
//
//	public static void main(String[] args) throws SQLException {
//
//		JDBCExample e = new JDBCExample();
//		Statement stmt = e.connection.createStatement();
//
//		// int result = stmt.executeUpdate("create table program5 (id int, nume
//		// varchar(255), prenume varchar(255))");
//		// int result = stmt.executeUpdate("insert into tabael2 values
//		// (101,'Alina', 'Stan')");
//		String sql = "select id from tabael2";
//		ResultSet rs = stmt.executeQuery(sql);
//		while (rs.next()) {
//			int id = rs.getInt("id");
//			System.out.println("ID: " + id);
//		}
//
//		System.out.println("------------------------");
//		PreparedStatement statement = null;
//		sql = "UPDATE tabael2 set id=? WHERE nume=?, prenume=?";
//		statement = (PreparedStatement) e.connection.prepareStatement(sql);
//		statement.setInt(1, 45);
//		
//		 sql = "select id from tabael2";
//		 rs = stmt.executeQuery(sql);
//		while (rs.next()) {
//			int id = rs.getInt("id");
//			System.out.println("ID: " + id);
//		}
//
//	}
//
//}
