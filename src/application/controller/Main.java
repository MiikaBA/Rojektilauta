package application.controller;
	
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.sql.*;

import application.model.DBConn;
import application.model.HashHandler;
import application.model.Passwords;
import application.model.User;
import application.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private Stage primaryStage;
	private VBox rootLayout;
	private User user; //TODO luodaan User luokka, joka voidaan tallentaa db.
	private IController start;
	private IController login;
	private IController register;
	private Connection conn = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//System.out.println("XD");
			this.primaryStage = primaryStage;
			
			this.primaryStage.setTitle("Project Manager");
			if(user==null) {
				initStartScene();
			}
			System.out.println("kirjattu jo sisään");
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initStartScene() {
        try {
            // Load start scene from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/application/view/start/startScene.fxml"));
            System.out.println(loader.getLocation());
            rootLayout = (VBox) loader.load();
            
            start = loader.getController();
            
            start.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showLogin() {
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/application/view/start/loginForm.fxml"));
        System.out.println(loader.getLocation());
        rootLayout = (VBox) loader.load();
        
        login = loader.getController();
        login.setMainApp(this);
        
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        
		}catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showRegister() {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/application/view/start/registerForm.fxml"));
	        System.out.println(loader.getLocation());
	        rootLayout = (VBox) loader.load();
	        
	        register = loader.getController();
	        register.setMainApp(this);
	        
	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
			}catch(IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void registerUser(String name, String email, String password) {
		//TODO korvataan tää kirjottamalla db:seen uusi User ja hakemalla se.
		
		byte[] salt = HashHandler.createSalt();
		String[] hashedPWDandSALT = new String[2];
		try {
			hashedPWDandSALT = HashHandler.generateHash(password, salt);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		user = new User(name, email, hashedPWDandSALT[0]);
		System.out.println(user);
		
		try {
			if(conn == null) {
				DBConn connector = new DBConn();
				conn = connector.getConnection();
			}
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
		
		
		//TODO siirrytään projektien tarkastelu näyttöön:
		//showUserProjects();
	}
	
	public Boolean loginUser(String email, String password) {
		//TODO DB komponentti palauttaa haetuilla tiedoilla löytämänsä käyttäjän tai falsee jolloin pyydetään
		//yrittämään kirjautumista uudelleen.
		try {
			
			DBConn connector = new DBConn();
			conn = connector.getConnection();
			
			Statement stmt = conn.createStatement();
			String query = "SELECT UserEmail, PasswordHash, PasswordSalt FROM User WHERE "
					+ "UserEmail = '" + email + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			String retrievedEmail = rs.getString("UserEmail");
			String retrievedHash = rs.getString("PasswordHash");
			String retrievedSalt = rs.getString("PasswordSalt");
			
			rs.close();
			conn.close();
			
			if(HashHandler.authenticatePass(password, retrievedHash, retrievedSalt)) {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//TODO siirrytään projektien tarkastelu näyttöön:
		//showUserProjects();
		return false;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
