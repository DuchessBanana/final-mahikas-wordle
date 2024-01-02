public class GameBoard {
	private int[][] grid;// the grid that stores the pieces
	String[][] guessList = new String[6][5];
	String answer;
	Keyboard keyboard;

	int guessNum = 0;

	public GameBoard(String answer, Keyboard keyboard) {
		this.answer = answer;
		this.keyboard = keyboard;
		grid = new int[6][5];
		for (int r = 0; r < guessList.length; r++) {
			for (int c = 0; c < guessList[0].length; c++) {
				guessList[r][c] = " ";
			}
		}

	}

	/*** YOU COMPLETE THIS METHOD ***/
	public boolean move(String word) {
		System.out.println("[DEBUGGING INFO] You guessed the word " + word);
		if (isInWord(word)) {
			return true;
		}
		guessNum++;
		return true;
	}

	public boolean isGameOver() {
		if (guessNum > 5) {
			return true;
		}

		if (guessNum >= 1) {
			if (rowCheck(guessNum-1, 0)) {
				return true;
			}
		}

		return false;
	}

	public boolean isInWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			String letter = String.valueOf((word.charAt(i)));
			guessList[guessNum][i] = letter;
			if (answer.contains(letter)) {
				if (letter.equals(answer.substring(i, i+1))) {
					keyboard.turnYes(letter);
					grid[guessNum][i] = 1;
				} else {
					keyboard.turnMaybe(letter);
					grid[guessNum][i] = 2;
				}
			} else {
				keyboard.turnNo(letter);
				grid[guessNum][i] = 3;
			}
		}
		return false;
	}

	public boolean rowCheck(int row, int col) {
		if ((grid[row][col] == grid[row][col + 1]) && (grid[row][col + 1] == grid[row][col + 2]) && (grid[row][col+2] == grid[row][col+3]) && (grid[row][col+3] == grid[row][col+4])) {
			if (grid[row][col] == 1) {
				return true;
			}
		}
		return false;
	}


	public int[][] getGrid() {
		return grid;
	}


	public boolean isInGrid(int row, int col) {
		return false;
	}
}