package ui;

import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;

public class PongButton extends Button{
	private static final String MENU_HOVER = "-fx-background-color: transparent\n";


       
	private static final String MENU = "-fx-background-color: blue\n";

	private Font font;
	
	public PongButton(String text, int x, int y) throws FileNotFoundException{
		this.setText(text);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setPrefSize(150, 50);
        loadFont();
        this.setFont(font);
        this.setStyle(MENU);
        addListeners();
	}
	
	private void loadFont() throws FileNotFoundException {
		font = Font.loadFont(new FileInputStream(new File("src/screen/resources/pong.ttf")), 22);
	}
	
	private void addListeners() {
		this.setOnMouseEntered(e ->{
			this.setStyle(MENU_HOVER);
		}); 
		this.setOnMouseExited(e ->{
			this.setStyle(MENU);
		});
	}
}
