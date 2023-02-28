package application.model;

import java.sql.*;

public class DBConn {
	
	public DBConn() {
		
	}
	
	public Connection getConnection() {
		Connection conn;
		
		final String URL = "jdbc:mariadb://localhost/rojekti";
		 // MySQL: "jdbc:mysql://localhost/courseDB";
		final String USERNAME = "dbuser23";
		final String PWD = "dbpass";
		
		try {
		 conn = DriverManager.getConnection(
		 URL + "?user=" + USERNAME + "&password=" + PWD);
		 return conn;
		} catch (SQLException e) {
			 do {
			System.err.println("Viesti: " + e.getMessage());
			System.err.println("Virhekoodi: " + e.getErrorCode());
			System.err.println("SQL-tilakoodi: " + e.getSQLState());
			 } while (e.getNextException() != null);{
				 System.exit(-1); // lopetus heti virheen vuoksi
			 }
		}
		return null;
	}
}


