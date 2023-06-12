package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game,
 * specifically.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class ClearCellGameModel extends GameModel {

	private Random random;
	private int score;

	/* Include whatever instance variables you think are useful. */

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board.
	 * 
	 * @param rows   number of rows in board
	 * @param cols   number of columns in board
	 * @param random random number generator to be used during game when rows are
	 *               randomly created
	 */
	public ClearCellGameModel(int rows, int cols, Random random) {
		super(rows, cols);
		this.random = random;
	}

	/**
	 * Returns true only if an entire row is empty.
	 * 
	 * @param rows - holds an index of a row
	 */
	private boolean EmptyRows(int rowIndex) {
		for (int col = 0; col < this.getCols(); col++) {
			if (board[rowIndex][col] != BoardCell.EMPTY) {
				return false;
			}

		}
		return true;
	}

	/**
	 * returns true if the return value for EmptyRows is false(indicating row is not
	 * empty)
	 */
	public boolean isGameOver() {
		return !(EmptyRows(board.length - 1));
	}

	/**
	 * Returns the player's score. The player should be awarded one point for each
	 * cell that is cleared.
	 * 
	 * @return player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * This method does nothing if the game is over.
	 * 
	 * If the game is not over the existing rows get shifted by one position, a
	 * random BoardCell of rows will be inserted and the row will be filled from
	 * left to right with cells obtained by calling
	 * BoardCell.getNonEmptyRandomBoardCell().
	 */
	public void nextAnimationStep() {
		if (!(this.isGameOver())) {
			// previous row will shift one position down
			for (int rows = getRows() - 1; rows > 0; rows--) {
				board[rows] = board[rows - 1];

			}
			// a row with random BoardCell object gets inserted.
			board[0] = new BoardCell[getCols()];
			for (int cols = 0; cols < getCols(); cols++) {
				setBoardCell(0, cols, BoardCell.getNonEmptyRandomBoardCell(random));
			}
		}
	}

	/**
	 * This method is called when the user clicks a cell on the board. If the
	 * selected cell is not empty, it will be set to BoardCell.EMPTY, along with any
	 * adjacent cells that are the same color as this one.
	 * 
	 * If any rows on the board become empty as a result of the removal of cells
	 * then those rows will collapse.
	 * 
	 * @throws IllegalArgumentException with message "Invalid row index" for invalid
	 *                                  row or "Invalid column index" for invalid
	 *                                  column. We check for row validity first.
	 */
	public void processCell(int rowIndex, int colIndex) {
		if (rowIndex < 0 || rowIndex >= getRows())
			throw new IllegalArgumentException("Invalid row Index");
		if (colIndex < 0 || colIndex >= getCols())
			throw new IllegalArgumentException("Invalid column Index");

		BoardCell squareColor = board[rowIndex][colIndex]; // the specific little cell(square)

		if (squareColor == BoardCell.EMPTY)// if empty and clicked, do nothing
			return;

		for (int j = rowIndex - 1; j <= rowIndex + 1; j++) {// goes in order of column and row
			for (int i = colIndex - 1; i <= colIndex + 1; i++) {
				// After checking the colors and the validity, the cell is set to empty
				if (Valid(j, i) && squareColor == board[j][i]) {
					this.setBoardCell(j, i, BoardCell.EMPTY);
					score++;
				}
			}
		}
		// shallow copy of the board
		BoardCell[][] temp = new BoardCell[getRows()][getCols()];

		// sets them all to EMPTY
		for (int rows = 0; rows < getRows(); rows++) {
			for (int cols = 0; cols < getCols(); cols++) {
				temp[rows][cols] = BoardCell.EMPTY;
			}
		}

		/*
		 * if it is empty at that exact spot, we increment, get the number of non empty
		 * rows and counts them, check if empty or not and then add all the non empty
		 * rows to the shallow copy of the board
		 */

		int index = 0;
		for (int i = 0; i < getRows(); i++) {
			if (!EmptyRows(i)) {
				temp[index++] = board[i];

			}
		}
		board = temp;

	}

	/*
	 * checks that rows and columns are valid inputs
	 */
	private boolean Valid(int r, int c) {
		return r >= 0 && r < getRows() && c >= 0 && c < getCols();
	}

}