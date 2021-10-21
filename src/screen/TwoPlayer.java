package screen;

import java.util.Random;

import application.Ball;
import application.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TwoPlayer extends SinglePlayer{
	private Stage menuStage;
	
	private Stage stage;
	private Group group;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimationTimer animationTimer;
	
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;
	private boolean upKeyPressed = false;
	private boolean downKeyPressed = false;
	private boolean wKeyPressed = false;
	private boolean sKeyPressed = false;
	private boolean gameStarted;
	
	
	Ball ball;
	Player playerOne;
	Player playerTwo;
	
	private int scoreOne;
	private int scoreTwo;
	
	public TwoPlayer() {
		stage = new Stage();
		group = new Group();
		scene = new Scene(group);
		stage.setTitle("TWO PLAYER PONG");
		stage.setResizable(false);	
		stage.setScene(scene);
		canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		group.getChildren().add(canvas);
		
		ball = new Ball();
		playerOne = new Player(true);
		playerTwo = new Player(false);
		gameStarted = false;
		createAnimationTimer();		
		addActionListeners();
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

    private void createAnimationTimer() {
		animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
            	
            	drawGame();
            	movePlayer();
            	
            	
    			gc.setFill(Color.WHITE);
    			gc.fillText("Ball X : " + ball.getXPos(), (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2 - 50);
    			gc.fillText("Ball Y : " + ball.getYPos(), (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2 - 75);
    			gc.fillText("Ball dX : " + ball.getXSpeed(), (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2 - 100);
    			gc.fillText("Ball dY : " + ball.getYSpeed(), (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2 - 125);
    			gc.fillText("Started : " + gameStarted, (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2 - 150);
    			
    			
    			
    			
            	if (gameStarted) {            		
                    drawBall();                    
                    ballCollision();                      
            	} else {
            		// Draw click to start text
        			gc.setFill(Color.WHITE);
        			gc.fillText("Click to Start", (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2);
        			ballRestart();
            	}
            	
            	moveBall();
            	drawPlayers();
            	
            }			
        };
        animationTimer.start();
	}
    
    private void moveBall() {
    	gc.fillText("moving", (GAME_WIDTH / 2) - 10, GAME_HEIGHT / 2 - 175);
    	ball.setXPos(ball.getXPos() +  ball.getXSpeed());
    	ball.setYPos(ball.getYPos() +  ball.getYSpeed());
    }
	
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
		if (ball.getYPos() >= GAME_HEIGHT || ball.getYPos() <= 0) ball.setYSpeed(ball.getYSpeed() * -1);
		
		if (ball.getXPos() >= playerTwo.getXPos() - ball.getRadius() && ball.getYPos() > playerTwo.getYPos() && 
				ball.getYPos() < playerTwo.getYPos() + playerOne.getHeight()) {
			if (ball.getXSpeed() < 6) {
				ball.setXSpeed((int) (ball.getXSpeed() + 0.5));
			}
			ball.setXSpeed((int)(ball.getXSpeed() * -1));
		}
		
		if (ball.getXPos() >= GAME_WIDTH) {
			scoreOne++;
			gameStarted = false;
		}
		
		if (ball.getXPos() <= playerOne.getXPos() + ball.getRadius() && ball.getYPos() > playerOne.getYPos() &&
				ball.getYPos() < playerOne.getYPos() + playerOne.getHeight()) {
			if (ball.getXSpeed() > -6) {
				ball.setXSpeed((int) (ball.getXSpeed() - 0.5));
			}
			ball.setXSpeed((int) (ball.getXSpeed() * -1));
		}
		
		if (ball.getXPos() <= 0) {
			scoreTwo++;
			gameStarted = false;
		}
	}
	
	private void ballRestart() {
		ball.setXPos(GAME_WIDTH / 2);
		ball.setYPos(GAME_HEIGHT / 2);
		
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
	
	private void drawGame() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);		
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
	
	public void createNewGame() {
		        
		stage.show();
	}
}
