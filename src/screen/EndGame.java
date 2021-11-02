package screen;

import application.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndGame {
	
	Stage endStage;
	AnchorPane endPane;
	Scene endScene;	
	
	Button menuButton;
	Button exitButton;
	
	public EndGame() {
		// Initialise stage
		endStage = new Stage();
		endStage.setTitle("PONG");
		endStage.setResizable(false);
		
		// Initialise AnchorPane
		endPane = new AnchorPane();
		endPane.setMinSize(Main.WIDTH, Main.HEIGHT);
		endPane.setStyle("-fx-background-color: BLACK;");
		
		// Initialise Scene, setScene to stage 
        endScene = new Scene(endPane);
        endStage.setScene(endScene);
	}
	
}
