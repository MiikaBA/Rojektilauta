package application.view.start;

import application.controller.Main;
import application.view.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements IController {
	
	private Main mainApp;
	
	@FXML
	TextField mailField;
	
	@FXML
	TextField passField;
	
	@FXML
	Button loginButton;

	@FXML
	public void initialize() {
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String email = mailField.getText();
            	String pass = passField.getText();
            	//TODO Luo käyttäjän haku tyyliin:
                if (!mainApp.loginUser(email, pass)) {
                	loginButton.setText("Try Again!!");
                }else {
                	loginButton.setText("Toimii!!");
                }
            	
            }
        });
	}
	
	@Override
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void jatkaSeuraavaan() {
	}

}
