package application;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Game {
	private static int gameWidth;
	private static int gameHeight;
	private static int ballRadius;
	private static int playerWidth;	
	private static int playerHeight;	
	private int playerOneXPos;
	private int playerOneYPos;
	private int playerTwoXPos;
	private int playerTwoYPos;
	private int playerDx;
	private int playerDy;	
	private int ballXPos;
	private int ballYPos;
	private float ballDx;
	private float ballDy;
	private int scoreOne;
	private int scoreTwo;
	private boolean gameStarted;
	private boolean upKeyPressed;
	private boolean downKeyPressed;
	private boolean wKeyPressed;
	private boolean sKeyPressed;
	
	
	Game() {
		Main main = new Main();
		gameHeight = main.HEIGHT;
		gameWidth = main.WIDTH;
		playerHeight = 100;
		playerWidth = 15;
		playerOneXPos = 0;
		playerOneYPos = gameHeight / 2;
		playerTwoXPos = gameWidth - playerWidth;
		playerTwoYPos = gameHeight / 2;
		playerDx = 5;
		playerDy = 5;
		ballXPos = gameWidth / 2;
		ballYPos = gameHeight / 2;
		ballDy = 2;
		ballDx = 2;
		ballRadius = 10;
		gameStarted = false;
		scoreOne = 0;
		scoreTwo = 0;
	}
	
	public void run(GraphicsContext gc) {		
		
		// Draw Background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gameWidth, gameHeight);
		
		movePlayers();
		
//		gc.setFill(Color.GREEN);
//		gc.fillText("dx = " + ballDx, gameWidth / 2, 50);
//		gc.fillText("dy = " + ballDy, gameWidth / 2, 75);
		
		if(gameStarted) {
			// Draw Ball
			
			
			// Draw Score
			gc.setFill(Color.WHITE);
			gc.fillText(scoreOne + "\t\t\t\t\t\t\t\t\t\t\t\t" + scoreTwo, gameWidth / 2, 50);
			
			
			ballCollision();
			
		} else {
			
			
			ballRestart();
		}
		// Update Ball Position
		ballYPos += ballDy;
		ballXPos += ballDx;
						
		// Draw players
		gc.setFill(Color.WHITE);
		gc.fillRect(playerOneXPos, playerOneYPos, playerWidth, playerHeight);
		gc.fillRect(playerTwoXPos, playerTwoYPos, playerWidth, playerHeight);
	}
	
	private void movePlayers() {
		if (wKeyPressed) {
			playerOneYPos -= playerDy;
		}
		if (upKeyPressed) {
			playerTwoYPos -= playerDy;
		}
		if (sKeyPressed) {
			playerOneYPos += playerDy;
		}
		if (downKeyPressed) {
			playerTwoYPos += playerDy;
		}		
	}
	
	private void ballCollision() {
		if (ballYPos >= gameHeight || ballYPos <= 0) ballDy *= -1;
		
		if (ballXPos >= playerTwoXPos - ballRadius && ballYPos > playerTwoYPos && 
				ballYPos < playerTwoYPos + playerHeight) {
			if (ballDx < 6) {
				ballDx += 0.5;
			}
			ballDx *= -1;
		}
		
		if (ballXPos >= gameWidth) {
			scoreOne++;
			gameStarted = false;
		}
		
		if (ballXPos <= playerOneXPos + ballRadius && ballYPos > playerOneYPos &&
				ballYPos < playerOneYPos + playerHeight) {
			if (ballDx > -6) {
				ballDx -= 0.5;
			}
			ballDx *= -1;
		}
		
		if (ballXPos <= 0) {
			scoreTwo++;
			gameStarted = false;
		}
		
		
	}
	
	private void ballRestart() {
		ballXPos = gameWidth / 2;
		ballYPos = gameHeight / 2;
		
		Random rand = new Random();
		int nX;
		int nY;
		do
			nX = -2 + rand.nextInt(4);
		while (nX == 0 || nX == -1 || nX == 1);
		do
			nY = -1 + rand.nextInt(2);
		while(nY == 0);
		ballDx = (float)nX;
		ballDy = (float)nY;		
	}
	
	public void keyHandler(Scene scene){
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
        }
        );
	}
	

	

}
