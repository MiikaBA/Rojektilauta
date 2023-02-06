package application.controller;
	
import java.io.IOException;

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
	@Override
	public void start(Stage primaryStage) {
		try {
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
		user = new User(name, email, password);
		System.out.println(user);
		//TODO siirrytään projektien tarkastelu näyttöön:
		//showUserProjects();
	}
	
	public Boolean loginUser(String email, String password) {
		//TODO DB komponentti palauttaa haetuilla tiedoilla löytämänsä käyttäjän tai falsee jolloin pyydetään
		//yrittämään kirjautumista uudelleen.
		
		//TODO siirrytään projektien tarkastelu näyttöön:
		//showUserProjects();
		return true;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
