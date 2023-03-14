package application.model;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBConn {
	private static DBConn dbConn_inst = null;
	
	private DBConn() {
		
	}
	
	public static synchronized DBConn getInstance() {
		if(dbConn_inst == null) {
			return new DBConn();
		}else {
			return dbConn_inst;
		}
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
	
	public User registerUser(String name, String email, String password) {
		Connection conn = null;
		byte[] salt = HashHandler.createSalt();
		String[] hashedPWDandSALT = new String[2];
		try {
			hashedPWDandSALT = HashHandler.generateHash(password, salt);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//user = new User(name, email, hashedPWDandSALT[0]);
		//System.out.println(user);
		
		try {
			conn = getConnection();
			
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO User " +
						   "VALUES (NULL, '" + name + "', '" + email + "', '" + hashedPWDandSALT[0]
								   + "', '" + hashedPWDandSALT[1] + "')";
			int count = stmt.executeUpdate(query);
			
			System.out.println("tän verran muutoksii: " + count);
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User loginUser(String email, String password) {
		try {
			Connection conn = null;
			conn = getConnection();
			
			Statement stmt = conn.createStatement();
			String query = "SELECT UserEmail, UserName, PasswordHash, PasswordSalt FROM User WHERE "
					+ "UserEmail = '" + email + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			String retrievedEmail = rs.getString("UserEmail");
			String retrievedName = rs.getString("UserName");
			String retrievedHash = rs.getString("PasswordHash");
			String retrievedSalt = rs.getString("PasswordSalt");
			
			rs.close();
			conn.close();
			
			if(HashHandler.authenticatePass(password, retrievedHash, retrievedSalt)) {
				return new User(retrievedName, retrievedEmail, retrievedHash);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}


