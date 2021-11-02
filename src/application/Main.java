package application;
	
import java.io.FileNotFoundException;
import java.time.Duration;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import screen.Menu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	
	// Dimension of game screen
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private Stage mainStage;
	
	public void start(Stage primaryStage) throws FileNotFoundException{
		mainStage = primaryStage;
		Menu menuScreen = new Menu();
		mainStage = menuScreen.getMainStage();
        mainStage.setTitle("PONG");
        mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
