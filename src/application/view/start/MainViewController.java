package application.view.start;

import application.controller.Main;
import application.view.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewController implements IController {

	private Main mainApp;
	
	@FXML
	Button projectButton1;
	
	@FXML
	Button ProjectButton2;
	
	@FXML
	Button addProjectButton;
	
	@FXML
	Button settingsButton;
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void jatkaSeuraavaan() {
	}
}
