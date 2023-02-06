package application.view.start;

import application.controller.Main;
import application.view.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController implements IController {
	private Main mainApp;
	
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	
	@FXML
	public void initialize() { 
		
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showLogin();
            	System.out.println("taalla");
            }
        });
		
		registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showRegister();
            	System.out.println("taalla");
            }
        });
	}

	@Override
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void jatkaSeuraavaan() {
		// TODO Auto-generated method stub

	}

}
