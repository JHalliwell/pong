package screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu {
	private Stage menuStage;
	private Stage prevStage;
	private Scene menuScene;
	private AnchorPane menuPane;
	private Font font;
	
	private static int GAME_WIDTH = 800;
	private static int GAME_HEIGHT = 600;
	
	public Menu() throws FileNotFoundException { 
		menuStage = new Stage();
		menuStage.setTitle("PONG");
		menuStage.setResizable(false);
		menuPane = new AnchorPane();
		menuPane.setMinSize(GAME_WIDTH, GAME_HEIGHT);
		menuPane.setStyle("-fx-background-color: BLACK;");
        menuScene = new Scene(menuPane);
        menuStage.setScene(menuScene);
        
        loadFont();
        createTitle();
        createButtons();
	}
	
	private void loadFont() throws FileNotFoundException {
		font = Font.loadFont(new FileInputStream(new File("src/screen/resources/pong.ttf")), 22);
	}
	
	private void createTitle() {
		Label titleLbl = new Label("PONG");
        titleLbl.setFont(font);
        //titleLbl.setStyle(TITLE_STYLE);
        titleLbl.setScaleX(5);
        titleLbl.setScaleY(5);
        titleLbl.setLayoutX(225);
        titleLbl.setLayoutY(375);
        menuPane.getChildren().add(titleLbl);
	}
	
	private void createButtons() {
		
	}
	
	public Stage getMainStage() {
        return menuStage;
    }
}
