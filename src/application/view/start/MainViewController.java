package application.view.start;

import java.io.IOException;

import application.controller.Main;
import application.view.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	
	@FXML
	public void initialize() {
		
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            		FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("/application/view/start/settings.fxml"));
                    System.out.println(loader.getLocation());
                    VBox settingsRootLayout = (VBox) loader.load();
                    
                    Settings s = loader.getController();
                    s.setMainApp(mainApp);
                    
                    Scene scene = new Scene(settingsRootLayout);
                    Stage settingsStage = new Stage();
                    settingsStage.setTitle("Settings");
                    settingsStage.setScene(scene);
                    settingsStage.show();
                    
            		}catch(IOException e) {
                        e.printStackTrace();
                    }
            	}
            
        });
	}
	
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void jatkaSeuraavaan() {
	}
}
