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
	
	// Dimension of game 
	private static final int width = 800;
	private static final int height = 600;
	
	public void start(Stage primaryStage) throws FileNotFoundException {
		Menu menuScreen = new Menu();
		primaryStage = menuScreen.getMainStage();
        primaryStage.setTitle("PONG");
        primaryStage.show();
        /*
		primaryStage.setTitle("PONG");		
		Group root = new Group(); // Creating root Group, which can hold a collection of nodes
		Scene theScene = new Scene(root); // Creating a Scene
		primaryStage.setScene(theScene);  // A stage can show a scene
		primaryStage.setResizable(false);
		
		Canvas canvas = new Canvas(width, height); // Canvas is what we 'draw' on to
		root.getChildren().add(canvas);
		
		Game pongGame = new Game();
				
		GraphicsContext gc = canvas.getGraphicsContext2D(); // use this to draw on to canvas
		
		new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	            pongGame.run(gc);
	        }
	    }.start();
	    
	    pongGame.keyHandler(theScene);
	    
	    primaryStage.show();
	    */
	}
	
	/**
	 * Getters for game width and height 
	 */
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
