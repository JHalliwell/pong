package screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import application.Ball;
import application.Main;
import application.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SinglePlayer {
	
	private Stage stage;
	private Group group;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimationTimer animationTimer;

	private boolean sKeyPressed = false;
	private boolean wKeyPressed = false;
	private boolean gameStarted;	

	private Ball ball;
	private Player playerOne;
	private Player playerTwo;

	private int scoreOne = 0;
	private int scoreTwo = 0;
	private Font font;
	
	public SinglePlayer() throws FileNotFoundException {	
		System.out.println("SinglePlayer()");
		
		stage = new Stage();
		group = new Group();
		scene = new Scene(group);
		stage.setTitle("SINGLE PLAYER PONG");
		stage.setResizable(false);	
		stage.setScene(scene);
		canvas = new Canvas(Main.WIDTH, Main.HEIGHT);
		loadFont();
		gc = canvas.getGraphicsContext2D();
		group.getChildren().add(canvas);
		
		ball = new Ball();
		playerOne = new Player(true);
		playerTwo = new Player(false);
		gameStarted = false;
		createAnimationTimer();		
		addActionListeners();		
	}
	
	private void loadFont() throws FileNotFoundException {
		font = Font.loadFont(new FileInputStream(new File("src/screen/resources/pong.ttf")), 22);
	}
		
    private void createAnimationTimer() {
		animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
            	
            	drawGame();
            	drawScore();
            	movePlayerOne();           
            	movePlayerTwo();
            	
    			gc.setFill(Color.WHITE);
    			gc.fillText("Player1 Y: " + playerOne.getYPos(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 25);
    			gc.fillText("Player2 Y: " + playerTwo.getYPos(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 50);
    			gc.fillText("Ball X : " + ball.getXPos(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 75);
    			gc.fillText("Ball Y : " + ball.getYPos(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 100);
    			gc.fillText("Ball dX : " + ball.getXSpeed(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 125);
    			gc.fillText("Ball dY : " + ball.getYSpeed(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 150);
    			gc.fillText("Started : " + gameStarted, (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 175);    			
    			
            	if (gameStarted) {            		
                    drawBall();                    
                    ballCollision();                      
            	} else {
            		// Draw click to start text
        			gc.setFill(Color.WHITE);
        			gc.fillText("Click to Start", (Main.WIDTH / 2) - 10, Main.HEIGHT / 2);
        			ballRestart();
            	}
            	
            	moveBall();
            	drawPlayers();
            	
            }		
        };
        animationTimer.start();
        
	}
    
    private void drawScore() {
    	gc.setFill(Color.WHITE);
    	gc.setFont(font);
    	gc.fillText(scoreOne + "\t\t\t" + scoreTwo, 235, 60);
    }
    
	private void drawGame() {		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);		
		
		gc.setFill(Color.WHITE);
		for (int i = 0; i < Main.HEIGHT; i += 15) {
			gc.fillRect(Main.WIDTH / 2, i, 2, 10);
		}		
	}
	
	private void drawBall() {
		
		gc.setFill(Color.WHITE);
		gc.fillOval(ball.getXPos(), ball.getYPos(), ball.getRadius(), ball.getRadius());
		ball.setYPos(ball.getYPos() + ball.getYSpeed());
		ball.setXPos((int) (ball.getXPos() + ball.getXSpeed()));
		
	}
	
	private void drawPlayers() {
		
		gc.setFill(Color.WHITE);
		gc.fillRect(playerOne.getXPos(), playerOne.getYPos(), playerOne.getWidth(), playerOne.getHeight());
		gc.fillRect(playerTwo.getXPos(), playerTwo.getYPos(), playerTwo.getWidth(), playerTwo.getHeight());
		
	}
    
    private void moveBall() {
    	
    	gc.fillText("moving", (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 200);
    	ball.setXPos(ball.getXPos() +  ball.getXSpeed());
    	ball.setYPos(ball.getYPos() +  ball.getYSpeed());
    	
    }
    
	
	private void movePlayerOne() {	  
	
		if (wKeyPressed) {
			playerOne.setYPos(playerOne.getYPos() - playerOne.getSpeed());
		}
		if (sKeyPressed) {
			playerOne.setYPos(playerOne.getYPos() + playerOne.getSpeed());
		}

	}
	
	private void movePlayerTwo() {
		System.out.println("One player : movePlayerTwo");
		
		if (ball.getYPos() > playerTwo.getYPos()) {
			playerTwo.setYPos(playerTwo.getYPos() + playerTwo.getSpeed());
		}
		
		if (ball.getYPos() < playerTwo.getYPos()) {
			playerTwo.setYPos(playerTwo.getYPos() - playerTwo.getSpeed());
		}
	}	
	
    // Deals with key and mouse press and release
	private void addActionListeners() {
		
		scene.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.W)
            {
                wKeyPressed = true;
            }
            if (e.getCode() == KeyCode.S)
            {
                sKeyPressed = true;
            }
        });

        scene.setOnKeyReleased(e ->
        {
            if (e.getCode() == KeyCode.W)
            {
                wKeyPressed = false;
            }
            if (e.getCode() == KeyCode.S)
            {
                sKeyPressed = false;
            }
	   });
        
       scene.setOnMouseClicked(e ->
       {
    	    if (!gameStarted)
        		gameStarted = true;
       });
       
	}
	
	private void ballCollision() {
		
		// Floor or ceiling collision
		if (ball.getYPos() >= Main.HEIGHT || ball.getYPos() <= 0) ball.setYSpeed(ball.getYSpeed() * -1);
		
		// Player two collision
		if (ball.getXPos() >= playerTwo.getXPos() - ball.getRadius() && ball.getYPos() > playerTwo.getYPos() && 
				ball.getYPos() < playerTwo.getYPos() + playerOne.getHeight()) {
			if (ball.getXSpeed() < 6) {
				ball.setXSpeed((int) (ball.getXSpeed() + 0.5));
			}
			ball.setXSpeed((int)(ball.getXSpeed() * -1));
		}
		
		// Player one collision
		if (ball.getXPos() <= playerOne.getXPos() + ball.getRadius() && ball.getYPos() > playerOne.getYPos() &&
				ball.getYPos() < playerOne.getYPos() + playerOne.getHeight()) {
			if (ball.getXSpeed() > -6) {
				ball.setXSpeed((int) (ball.getXSpeed() - 0.5));
			}
			ball.setXSpeed((int) (ball.getXSpeed() * -1));
		}
		
		// Ball passes player two
		if (ball.getXPos() >= Main.WIDTH) {
			scoreOne++;
			gameStarted = false;
		}		
		
		// Ball passes player one
		if (ball.getXPos() <= 0) {
			scoreTwo++;
			gameStarted = false;
		}
		
	}
	
	// Restarts the ball to centre, and randomises it's direction
	private void ballRestart() {
		
		ball.setXPos(Main.WIDTH / 2);
		ball.setYPos(Main.HEIGHT / 2);
		
		Random rand = new Random();
		int nX;
		int nY;
		do
			nX = -2 + rand.nextInt(5);
		while (nX == 0 || nX == -1 || nX == 1);
		do
			nY = -1 + rand.nextInt(3);
		while(nY == 0);
		ball.setXSpeed(nX);
		ball.setYSpeed(nY);		
		
	}
	
	public void createNewGame() {
		        
		stage.show();
		
	}
}
