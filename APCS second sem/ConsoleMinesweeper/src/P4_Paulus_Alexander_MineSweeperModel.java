import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/*
 * Alexander Paulus
 * Period: 5
 * 
 * Overall I found this lab to be very fun, but at the same time quite challenging. I ran into many bugs while working on my project and I found many of 
 * those bugs quite confusing, but very satisfying when solved. Overall I found this lab to be very cool, but while working on the lab I encountered many 
 * internet troubles so I did not have stable access to the powerpoint and other resources for this lab making the assignment very difficult for me. 
 * 
 */
public class P4_Paulus_Alexander_MineSweeperModel implements P4_Paulus_Alexander_MSModelInterface{
	ArrayList<ArrayList<Boolean>> bottomOne = new ArrayList<ArrayList<Boolean>>();
	ArrayList<ArrayList<Boolean>> topOne = new ArrayList<ArrayList<Boolean>>();
	
	public void printBoard() {
		for (int i = 0; i < bottomOne.size(); i++) {
			for (int j = 0; j < bottomOne.get(i).size(); j++) {
				System.out.println(bottomOne.get(i).get(j));
			}
			System.out.println("");
		}
	}
	
	public void askUser() {
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your command: ");
        String userMessage = in.nextLine(); 
        switch(userMessage.toUpperCase()) 
        { 
            case "FLAG": 
            	int[] coordsFlag = rowAndColAsk(in);
            	toggleFlag(coordsFlag[0], coordsFlag[1]);
            	System.out.println("You revealed a flag!");
                break; 
            case "REVEAL":
            	int[] coordsReveal = rowAndColAsk(in);
            	if(!(isHidden(coordsReveal[0], coordsReveal[1]))) {
            		System.out.println("The value at " + coordsReveal[0] + " " + coordsReveal[1] + " are: " + getValue(coordsReveal[0], coordsReveal[1]));
            	}
            	break;
           
        }
	}
	public int[] rowAndColAsk(Scanner in) {
		System.out.println("Which row you looking for."); 
        String row = in.nextLine();
        System.out.println("Which col you looking for."); 
        String col = in.nextLine();
        
        int[] coordinate = new int[]{Integer.parseInt(row), Integer.parseInt(col)};
        
        return coordinate;
	}
	
	@Override
	public ArrayList<Boolean> getSurrounding(int r, int c) {
		ArrayList<Boolean> storList = new ArrayList<Boolean>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				storList.add(bottomOne.get(r + i).get(c + j));
			}
		}
		return storList;
	}

	@Override
	public int getValue(int row, int col) {
		if(bottomOne.get(row).get(col) == true) {
			return 1;
		}
		return 0;
	}

	@Override
	public int getNumMinesRemaining() {
		int numMines = 0;
		for (int i = 0; i < bottomOne.size(); i++) {
			for (int j = 0; j < bottomOne.get(i).size(); j++) {
				if(bottomOne.get(i).get(j) == true) {
					numMines++;
				}
			}
		}
		return numMines;
	}

	@Override
	public int getNumNeighboringMines(int row, int col) {
		int numNeighMines = 0;
		ArrayList<Boolean> storList = getSurrounding(row, col);
		for (Boolean boolean1 : storList) {
			if(boolean1 == true) {
				numNeighMines++;
			}
		}
		return numNeighMines;
	}

	@Override
	public void revealAll() {
		for (int i = 0; i < bottomOne.size(); i++) {
			for (int j = 0; j < bottomOne.get(i).size(); j++) {
				if(bottomOne.get(i).get(j) == false) {
					bottomOne.get(i).set(j, true);
				}
			}
		}
		
	}

	@Override
	public void hideAll() {
		for (int i = 0; i < bottomOne.size(); i++) {
			for (int j = 0; j < bottomOne.get(i).size(); j++) {
				if(bottomOne.get(i).get(j) == true) {
					bottomOne.get(i).set(j, false);
				}
			}
		}
		
	}

	@Override
	public void initRandom(int numRows, int numCols, int maxMines, int startRow, int startCol) {
		Random rand = new Random();
		for (int i = 0; i < numRows; i++) {
			ArrayList<Boolean> newList = new ArrayList<Boolean>();
			for (int j = 0; j < numCols; j++) {
				if(rand.nextInt(100) < 50)
					newList.add(true);
				else
					newList.add(false);
			}
		}
		
	}

	@Override
	public boolean isGameWon() {
		if(getNumMinesRemaining() != 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alreadyRevealed(int row, int col) {
		if(topOne.get(row).get(col) == true) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isHidden(int row, int col) {
		if(topOne.get(row).get(col) == false) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasMine(int row, int col) {
		if(bottomOne.get(row).get(col) == true) {
			return true;
		}
		return false;
	}

	@Override
	public int getWidth() {
		return 1000;
	}

	@Override
	public int getHeight() {
		return 1000;
	}

	@Override
	public void toggleFlag(int row, int col) {
		if(bottomOne.get(row).get(col) == true) {
			bottomOne.get(row).set(col, false);
		}
		bottomOne.get(row).set(col, false);
		
	}

}
