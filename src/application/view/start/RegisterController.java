package application.view.start;

import application.controller.Main;
import application.view.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController implements IController {
	
	private Main mainApp;
	
	@FXML
	TextField mailField;
	
	@FXML
	TextField nameField;
	
	@FXML
	TextField passField;
	
	@FXML
	Button registerButton;
	
	@FXML
	public void initialize() {
		
		registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String name = nameField.getText();
            	String mail = mailField.getText();
            	String pass = passField.getText();
            	
            	mainApp.registerUser(name, mail, pass);
            	System.out.println(name + mail + pass);
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
