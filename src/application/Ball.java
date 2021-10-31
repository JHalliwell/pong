package application;

import java.util.Random;

public class Ball {
	private static int GAME_WIDTH = 800;
	private static int GAME_HEIGHT = 600;	
	private static int RADIUS = 15;
	private int xPos;
	private int yPos;
	private int ySpeed;
	private int xSpeed;
	
	public Ball(){
		xPos = GAME_WIDTH / 2;
		yPos = GAME_HEIGHT / 2;
		
		Random rand = new Random();
		int nX;
		int nY;
		do
			nX = -2 + rand.nextInt(5);
		while (nX == 0 || nX == -1 || nX == 1);
		do
			nY = -1 + rand.nextInt(3);
		while(nY == 0);
		
		xSpeed = nX;
		ySpeed = nY;
	}
		
	public int getRadius() {
		return RADIUS;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void setXPos(int d) {
		this.xPos = d;
	}
	
	public void setYPos(int d) {
		this.yPos = d;
	}

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int d) {
		this.xSpeed = d;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(int d) {
		this.ySpeed = d;
	}
}
