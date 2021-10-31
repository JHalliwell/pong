package screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Game;
import application.Main;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.PongButton;

public class Menu {
	private Stage menuStage;
	private Stage prevStage;
	private Scene menuScene;
	private AnchorPane menuPane;
	private Font font;
	
	private static int GAME_WIDTH = 800;
	private static int GAME_HEIGHT = 600;
	
	public Menu() throws FileNotFoundException{ 
		System.out.println("Menu()");
		menuStage = new Stage();
		menuStage.setTitle("PONG");
		menuStage.setResizable(false);
		menuPane = new AnchorPane();
		menuPane.setMinSize(Main.WIDTH, Main.HEIGHT);
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
		Label titleLbl = new Label("PONG:");
        titleLbl.setFont(font);
        titleLbl.setStyle("-fx-text-fill: #f2f2f2;");
        DropShadow dropShadow = new DropShadow();      
        dropShadow.setBlurType(BlurType.ONE_PASS_BOX);          
        dropShadow.setColor(Color.GREY);       
        dropShadow.setHeight(0.5);
        dropShadow.setWidth(0.5);               
        dropShadow.setRadius(0.5);                
        dropShadow.setOffsetX(1); 
        dropShadow.setOffsetY(0.5);                
        dropShadow.setSpread(0.5);  
        titleLbl.setEffect(dropShadow);
        titleLbl.setScaleX(5);
        titleLbl.setScaleY(5);
        titleLbl.setLayoutX(375);
        titleLbl.setLayoutY(150);
        
        menuPane.getChildren().addAll(titleLbl);
	}
	
	private void createButtons() throws FileNotFoundException {
		PongButton onePlayer = new PongButton("Single Player", 100, 400);
		onePlayer.setOnAction(e -> {
              menuStage.hide();
              SinglePlayer single;
			try {
				single = new SinglePlayer();
				single.createNewGame();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
              
          });

          PongButton multiPlayer = new PongButton("Two Player", 500, 400);
          multiPlayer.setOnAction(e -> {
        	  menuStage.hide();
        	  TwoPlayer two;
			try {
				two = new TwoPlayer();
				two.createNewGame();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});

//        PongButton exitButton = new PongButton("Exit", 650, 450, true);
//        exitButton.setOnAction(e -> menuStage.hide());

        menuPane.getChildren().addAll(onePlayer, multiPlayer);
	}
	
	public Stage getMainStage() {
        return menuStage;
    }
}
