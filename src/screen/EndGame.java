package screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.PongButton;

public class EndGame {
	
	Stage endStage;
	AnchorPane endPane;
	Scene endScene;	
	
	PongButton menuButton;
	PongButton exitButton;
	private Font font;
	
	Boolean winner;		// True for 1, false for 2
	
	public EndGame(Boolean oneWins) throws FileNotFoundException {
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
        
        loadFont();
        createText();
        createButtons();
	}
	
	private void createButtons() throws FileNotFoundException {
		
		menuButton = new PongButton("Menu", 100, 400);		
		
		exitButton = new PongButton("Exit", 500, 400);
		
		endPane.getChildren().addAll(menuButton, exitButton);
	}
	
	private void createText() {
		String win;
		if (winner) win = "One"; else win = "Two"; 
		
		// Initialise label
		Label titleLbl = new Label("Player" + win + "Wins!");
        titleLbl.setFont(font);
        titleLbl.setStyle("-fx-text-fill: #f2f2f2;");
        titleLbl.setScaleX(5);
        titleLbl.setScaleY(5);
        titleLbl.setLayoutX(375);
        titleLbl.setLayoutY(150);
        
        endPane.getChildren().add(titleLbl);
        
	}
	
	private void loadFont() throws FileNotFoundException {
		font = Font.loadFont(new FileInputStream(new File("src/screen/resources/pong.ttf")), 22);
	}
	
	public Stage getEndStage() {
		return endStage;
	}
	
	public void createEndGame() {
		endStage.show();
	}
	
}
