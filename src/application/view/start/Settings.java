package application.view.start;

import java.io.IOException;

import application.controller.Main;
import application.view.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settings implements IController {
	
	private Main mainApp;
	
	@FXML
	private TextField newPass;
	
	@FXML
	private Button changeButton;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	public void initialize() {
		changeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            		String newP = newPass.getText();
            		
            		if(newP == "" || newP.length() < 8) {
            			changeButton.setText("Try again.");
            		}else {
            			System.out.print(mainApp.changePassword(newP));
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
		// TODO Auto-generated method stub
		
	}
	
}
