package screen;

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

public class SinglePlayer {
	private Stage menuStage;
	
	protected Stage stage;
	private Group group;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimationTimer animationTimer;
	
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;
	private boolean upKeyPressed = false;
	private boolean downKeyPressed = false;
	
	
	Ball ball;
	Player playerOne;
	Player playerTwo;
	
	private int scoreOne;
	private int scoreTwo;
	
	public SinglePlayer() {
		stage = new Stage();
		group = new Group();
		scene = new Scene(group);
		stage.setTitle("SINGLE PLAYER PONG");
		stage.setResizable(false);	
		stage.setScene(scene);
		canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		group.getChildren().add(canvas);
		
		ball = new Ball();
		playerOne = new Player(true);
		playerTwo = new Player(false);
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
                drawBall();
                drawPlayers();
                ballCollision();     
                movePlayer();
            }			
        };
        animationTimer.start();
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
        });
	}
	
	private void movePlayer() {
		if (upKeyPressed) {
			playerOne.setYPos(playerOne.getYPos() - playerOne.getSpeed());
		}
		if (downKeyPressed) {
			playerOne.setYPos(playerOne.getYPos() + playerOne.getSpeed());
		}	
	}
	
	private void ballCollision() {
		
	}
	
	private void drawGame() {
		// Background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		// Ball
		gc.setFill(Color.WHITE);
		gc.fillOval(ball.getXPos(), ball.getYPos(), ball.getRadius(), ball.getRadius());
		ball.setYPos(ball.getYPos() + (int)ball.getYSpeed());
		ball.setXPos(ball.getXPos() + (int)ball.getXSpeed());
		
		// Players
		gc.setFill(Color.WHITE);
		gc.fillRect(playerOne.getXPos(), playerOne.getYPos(), playerOne.getWidth(), playerOne.getHeight());
		gc.fillRect(playerTwo.getXPos(), playerTwo.getYPos(), playerTwo.getWidth(), playerTwo.getHeight());
	}
	
	private void drawBall() {
		
	}
	
	private void drawPlayers() {
		
	}
	
	public void createNewGame() {
		        
		stage.show();
	}
}
