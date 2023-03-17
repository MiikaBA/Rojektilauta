package application.view.start;

import application.controller.Main;
import application.view.IController;
import javafx.fxml.*;
import javafx.scene.control.*;

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
