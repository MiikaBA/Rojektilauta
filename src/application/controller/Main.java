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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private Stage primaryStage;
	private VBox rootLayout;
	private AnchorPane mainViewLayout;
	private User user; //TODO luodaan User luokka, joka voidaan tallentaa db.
	private IController start;
	private IController login;
	private IController register;
	private IController mainView;
	private Connection conn = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			
			this.primaryStage.setTitle("Project Manager");
			if(user==null) {
				showLogin();
			}
			System.out.println("kirjattu jo sisään");
			
			
			
		} catch(Exception e) {
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
	
	public void showUserProjects() {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/application/view/start/mainView.fxml"));
	        System.out.println(loader.getLocation());
	        mainViewLayout = (AnchorPane) loader.load();
	        
	        mainView = loader.getController();
	        mainView.setMainApp(this);
	        
	        Scene scene = new Scene(mainViewLayout);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
			
		}catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void registerUser(String name, String email, String password) {
		User newU = DBConn.getInstance().registerUser(name, email, password);
		
		if(newU != null) {
			user = newU;
			showUserProjects();
		}
		
		System.out.println("ei oo fresh.");
	}
	
	public Boolean loginUser(String email, String password) {
		User newU = DBConn.getInstance().loginUser(email, password);
		if(newU != null) {
			user = newU;
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
