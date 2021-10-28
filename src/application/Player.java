package application;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Player {
	
	private static int HEIGHT = 100;
	private static int WIDTH = 15;
	private static int SPEED = 5;
	private static int GAME_HEIGHT = 600;
	private static int GAME_WIDTH = 800;			
	private int xPos;
	private int yPos;
	private int playerTwoSpeed;

	
	public Player(boolean one) {		
		if (one) xPos = 0;
		else xPos = GAME_WIDTH - WIDTH;			
		yPos = (GAME_HEIGHT / 2) - 50;
		
		setPlayerTwoSpeed(5);	
	}
		

	
	public int getHeight() {
		return HEIGHT;
	}


	public int getWidth() {
		return WIDTH;
	}


	public int getXPos() {
		return xPos;
	}


	public void setXPos(int xPos) {
		this.xPos = xPos;
	}


	public int getYPos() {
		return yPos;
	}


	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public static int getSpeed() {
		return SPEED;
	}

	public int getPlayerTwoSpeed() {
		return playerTwoSpeed;
	}



	public void setPlayerTwoSpeed(int playerTwoSpeed) {
		this.playerTwoSpeed = playerTwoSpeed;
	}	
	
}
