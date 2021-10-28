package screen;

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
import javafx.stage.Stage;

public class TwoPlayer extends SinglePlayer {

	private boolean upKeyPressed = false;
	private boolean downKeyPressed = false;
	
	public TwoPlayer() {
		
		stage = new Stage();
		group = new Group();
		scene = new Scene(group);
		stage.setTitle("TWO PLAYER PONG");
		stage.setResizable(false);	
		stage.setScene(scene);
		canvas = new Canvas(Main.WIDTH, Main.HEIGHT);
		gc = canvas.getGraphicsContext2D();
		group.getChildren().add(canvas);
		
		ball = new Ball();
		playerOne = new Player(true);
		playerTwo = new Player(false);
		gameStarted = false;
		createAnimationTimer();		
		addActionListeners();
		
	}

    private void createAnimationTimer() {
    	
		animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {            	
            	drawGame();
            	movePlayer();            	
            	
    			gc.setFill(Color.WHITE);
    			gc.fillText("Ball X : " + ball.getXPos(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 50);
    			gc.fillText("Ball Y : " + ball.getYPos(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 75);
    			gc.fillText("Ball dX : " + ball.getXSpeed(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 100);
    			gc.fillText("Ball dY : " + ball.getYSpeed(), (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 125);
    			gc.fillText("Started : " + gameStarted, (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 150);   			
    			
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
    
	private void drawGame() {
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);		
		
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
    	
    	gc.fillText("moving", (Main.WIDTH / 2) - 10, Main.HEIGHT / 2 - 175);
    	ball.setXPos(ball.getXPos() +  ball.getXSpeed());
    	ball.setYPos(ball.getYPos() +  ball.getYSpeed());
    	
    }
    
	
	private void movePlayer() {	  
	
		if (upKeyPressed) {
			playerTwo.setYPos(playerTwo.getYPos() - playerTwo.getSpeed());
		}
		if (downKeyPressed) {
			playerTwo.setYPos(playerTwo.getYPos() + playerTwo.getSpeed());
		}
		if (wKeyPressed) {
			playerOne.setYPos(playerOne.getYPos() - playerOne.getSpeed());
		}
		if (sKeyPressed) {
			playerOne.setYPos(playerOne.getYPos() + playerOne.getSpeed());
		}	

	}
	
    // Deals with key and mouse press and release
	private void addActionListeners() {
		scene.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.UP)
            {
                upKeyPressed = true;
            }
            if (e.getCode() == KeyCode.DOWN)
            {
                downKeyPressed = true;
            }
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
            if (e.getCode() == KeyCode.UP)
            {
                upKeyPressed = false;
            }
            if (e.getCode() == KeyCode.DOWN)
            {
                downKeyPressed = false;
            }
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
			nX = -2 + rand.nextInt(4);
		while (nX == 0 || nX == -1 || nX == 1);
		do
			nY = -1 + rand.nextInt(2);
		while(nY == 0);
		ball.setXSpeed(nX);
		ball.setYSpeed(nY);		
		
	}
	
	public void createNewGame() {
		        
		stage.show();
		
	}
}
