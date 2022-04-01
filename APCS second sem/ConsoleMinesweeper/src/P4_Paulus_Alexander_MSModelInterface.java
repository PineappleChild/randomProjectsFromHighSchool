

import java.util.ArrayList;

/*
 * I found this assignment to be very interesting and refreshing. It was less challenging than other assignments we have 
 * recently had. I found this assignment to be a bit more creative because we had the freedom to decide our own 
 * methods. Overall I enjoyed this assignment and I hope we have more like these in the future. 
 */

public interface P4_Paulus_Alexander_MSModelInterface {
	ArrayList<Boolean> getSurrounding(int r,int c);
	
	public int getValue(int row, int col);
	public int getNumMinesRemaining();
	public int getWidth();
	public int getHeight();
	public int getNumNeighboringMines(int row, int col);

	public void revealAll();
	public void hideAll();
	public void toggleFlag(int row, int col);
	public void initRandom(int numRows, int numCols, int maxMines, int startRow, int startCol);

	public boolean isGameWon();
	public boolean isGameOver();
	public boolean alreadyRevealed(int row, int col);
	public boolean isHidden(int row, int col);
	public boolean hasMine(int row, int col);
	
}

