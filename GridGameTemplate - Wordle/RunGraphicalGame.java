import processing.core.*;

import javax.swing.*;
import java.util.ArrayList;

public class RunGraphicalGame extends PApplet {
	GameBoard game;
	Keyboard keyboard;
	Display display;
	String guess, correctWord;
	boolean found;
	ArrayList<String> words;
	int timer;
	public void settings() {
		size(640, 550);
	}

	public void setup() {
		words = ListHelper.readFileAsList("words");
		correctWord = words.get((int)(Math.random()*words.size()));
		found = false;
		keyboard = new Keyboard(3,10);
		game = new GameBoard(correctWord, keyboard);
		timer = 0;

		//1 variables for guess grid, 2 variables for keyboard grid
		display = new Display(this, 150, 10, 350, 400,50, 420, 500,120);

		display.setColor(1, color(170,240,209)); //correct
		display.setColor(2, color(255, 237, 131)); //wrong place
		display.setColor(3,color(80)); //incorrect

		display.initializeWithGame(game, keyboard);
	}

	@Override
	public void draw() {
		timer ++;
		background(255, 194, 209);

		display.drawGrid(game.getGrid());
		display.drawKeys(keyboard.getGrid());
		display.displayKeysOnGrid(keyboard.keyboardLetters, 15, 1,0);
		display.displayTextOnGrid(game.guessList, 15, 1, 0);
		if(!game.isGameOver() && timer > 100) {
			do {
				guess = JOptionPane.showInputDialog("Guess a 5 letter word!");
				for(String s : words){
					if(s.equalsIgnoreCase(guess)){
						found = true;
					}
				}

			} while (guess.length() != 5 || !found);

			game.move(guess);
			timer = 0;
			found = false;
		}

		if (game.isGameOver()) {
			background(255, 194, 209);
			color(0, 0, 0);
			textSize(35);
			text("Game over! Amazing job! :)", 100, 260);
			text("The correct word was " + correctWord + ".", 95, 350);
		}
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "RunGraphicalGame" });
	}
}