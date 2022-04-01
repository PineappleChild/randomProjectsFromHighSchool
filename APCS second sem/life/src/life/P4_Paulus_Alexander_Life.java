package life;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * I found this lab to be very interesting, so far I enjoyed the activities
 *  we did in class I feel that coding something related to life is cool.
 * I thought the part where we create the board was the most fun because I
 *  enjoy working with arrays and data. Overall I found the lab to be very
 * cool and an amazing idea. I found the most challenging part to be debugging to program
 * because there was a lot to manage and fix. Overall I found the assignment to be 
 * very unique and far more challenging than I expected. 
 * 
 * @author alexander paulus
 *
 */
public class P4_Paulus_Alexander_Life {
	private static ArrayList<int[]> startList = new ArrayList<int[]>();
	private static ArrayList<ArrayList<String>> boardList = new ArrayList<ArrayList<String>>();
	static int rowBuild = 0;
	static int colBuild = 0;
	public P4_Paulus_Alexander_Life(String filename) {
		loadFile(filename);
	}
	
	public static void main(String[] args) {
		
		P4_Paulus_Alexander_Life l = new P4_Paulus_Alexander_Life("life100.txt");
		int[] boardLayout = startList.get(0);
		startList.remove(0);
		rowBuild = boardLayout[0];
		colBuild = boardLayout[1];
		boardList = l.createBoard(rowBuild, colBuild);
		l.printBoard(boardList, rowBuild, colBuild);		
		System.out.println("Number of living cells in row 9 --> " + l.rowCount(9));
		System.out.println("Number of living cells in col 9 --> " + l.colCount(9));
		System.out.println("Total number of living cells --> " + l.totalCount());
//		l.printBoard(boardList, rowBuild, colBuild);
//		for (ArrayList<String> i : boardList) {
//			System.out.println(i);
//		}
//		System.out.println(l.checkCoord(5, 5, boardList, rowBuild, colBuild));
	}
	private void runLife(int numGenerations) {
		
	}
	private int rowCount(int row) {
		if(row < 0 || row > rowBuild) {
			return -1;
		}
		ArrayList<String> rowList = boardList.get(row);
		int rows = 0;
		for (int i = 0; i < rowList.size(); i++) {
			if(checkCoord(row, i, boardList, rowBuild, colBuild) == 1) {
//				System.out.println(checkCoord(row, i, boardList, rowBuild, colBuild));
				rows++;
			}
		}
		return rows;
	}
	private int colCount(int col) {
		if(col < 0 || col > colBuild) {
			return -1;
		}
		ArrayList<String> rowList = boardList.get(col);
		int cols = 0;
		for (int i = 0; i < rowList.size(); i++) {
			if(checkCoord(i, col, boardList, rowBuild, colBuild) == 1) {
//				System.out.println(checkCoord(i, col, boardList, rowBuild, colBuild));
				cols++;
			}
		}
		return cols;
	}
	private int totalCount() {
		int counter = 0;
		for (int i = 0; i < boardList.size(); i++) {
			for (int j = 0; j < boardList.get(i).size(); j++) {
				if(checkCoord(i, j, boardList, rowBuild, colBuild) == 1) {
					counter++;
				}
			}
		}
		return counter;	
	}
	private ArrayList<ArrayList<String>> createBoard(int rowBuild, int colBuild) {
		ArrayList<ArrayList<String>> boardList = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < rowBuild; i++) {
			ArrayList<String> addList = new ArrayList<String>();
			
			for (int j = 0; j < colBuild; j++) {
				boolean valChecker = false;
				for (int j2 = 0; j2 < startList.size(); j2++) {
					if(startList.get(j2)[0] == i && startList.get(j2)[1] == j) {
						valChecker = true;
						break;
					}
				}
				if(j < 9) {
					if(valChecker) {
//						System.out.println("(" + i + ", " + j + ")");
						addList.add("*");
					}else {
						addList.add(" ");
					}
				}else {
					if(valChecker) {
//						System.out.println("(" + i + ", " + j + ")");
						addList.add(" " + "*");
					}else {
						addList.add(" " + " ");
					}
				}
			}
			
			boardList.add(addList);
		}
		return boardList;
	}
	
	private void loadFile(String inFileName){
		ArrayList<String> tempList = new ArrayList<String>();
		try {
	      File myObj = new File(inFileName);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	    	String[] tempVal = myReader.next().split("\t");
	    	tempList.add(tempVal[0]);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }  
		for (int i = 0; i < tempList.size() - 1; i++) {
			int[] tempArr = new int[2];
			tempArr[0] = Integer.parseInt(tempList.get(i));
			tempArr[1] = Integer.parseInt(tempList.get(i+1));
			startList.add(tempArr);
			i++;
		}
	}
	void printBoard(ArrayList<ArrayList<String>> boardList, int rowBuild, int colBuild) {
		System.out.println("");
		int counter = 1;
		for (int i = 0; i <= colBuild; i++) {
			if(i == 0) {
				System.out.print("   ");
			}else {
				System.out.print(i + " ");
			}
		}
		System.out.println("");
		for (ArrayList<String> arrayList : boardList) {
			if(counter < 10) {
				System.out.print(" " + counter++ + " ");
			}else {
				System.out.print(counter++ + " ");
			}
			
			for (String element : arrayList) {
				System.out.print(element + " ");
			}
			System.out.println("");
		}
	}
	int checkCoord(int row, int col, ArrayList<ArrayList<String>> input, int rowBuild, int colBuild) {
		//return 1 for no change
		//return 2 for remove
		//return 3 for add
		//get all available squares around the coord
		rowBuild = rowBuild -1;
		colBuild = colBuild -1;
		ArrayList<String> aroundCoord = new ArrayList<String>();
		if(col == 0) {
			if(row == 0) {
				aroundCoord.add(input.get(row).get(1));
				aroundCoord.add(input.get(row + 1).get(1));
				aroundCoord.add(input.get(row + 1).get(0));
			}else if(row == rowBuild) {
				aroundCoord.add(input.get(row - 1).get(0));
				aroundCoord.add(input.get(row - 1).get(1));
				aroundCoord.add(input.get(row).get(1));
			}else {
				aroundCoord.add(input.get(row - 1).get(col));
				aroundCoord.add(input.get(row - 1).get(col + 1));
				aroundCoord.add(input.get(row).get(col + 1));
				aroundCoord.add(input.get(row + 1).get(col + 1));
				aroundCoord.add(input.get(row + 1).get(col));
			}
		}else if(col == colBuild) {
			if(row == 0) {
				aroundCoord.add(input.get(row).get(colBuild - 1));
				aroundCoord.add(input.get(row + 1).get(colBuild - 1));
				aroundCoord.add(input.get(row + 1).get(colBuild));
			}else if(row == rowBuild){
				aroundCoord.add(input.get(row - 1).get(colBuild));
				aroundCoord.add(input.get(row - 1).get(colBuild - 1));
				aroundCoord.add(input.get(row).get(colBuild - 1));
			}else {
				aroundCoord.add(input.get(row - 1).get(colBuild));
				aroundCoord.add(input.get(row - 1).get(colBuild - 1));
				aroundCoord.add(input.get(row).get(colBuild - 1));
				aroundCoord.add(input.get(row + 1).get(colBuild - 1));
				aroundCoord.add(input.get(row + 1).get(colBuild));
			}
		}else if(row == 0 && (col > 0 && col < colBuild)){
			aroundCoord.add(input.get(row).get(col - 1));
			aroundCoord.add(input.get(row + 1).get(col - 1));
			aroundCoord.add(input.get(row + 1).get(col));
			aroundCoord.add(input.get(row + 1).get(col + 1));
			aroundCoord.add(input.get(row).get(col + 1));
		}else if(row == rowBuild && (col > 0 && col < colBuild)) {
			aroundCoord.add(input.get(row).get(col - 1));
			aroundCoord.add(input.get(row - 1).get(col - 1));
			aroundCoord.add(input.get(row - 1).get(col));
			aroundCoord.add(input.get(row - 1).get(col + 1));
			aroundCoord.add(input.get(row).get(col + 1));
		}else{
			aroundCoord.add(input.get(row - 1).get(col));
			aroundCoord.add(input.get(row - 1).get(col + 1));
			aroundCoord.add(input.get(row).get(col + 1));
			aroundCoord.add(input.get(row + 1).get(col + 1));
			aroundCoord.add(input.get(row + 1).get(col));
			aroundCoord.add(input.get(row + 1).get(col - 1));
			aroundCoord.add(input.get(row).get(col - 1));
			aroundCoord.add(input.get(row + 1).get(col - 1));
		}
		int counter = 0;
		for (String string : aroundCoord) {
			if(string.trim().equals("*")) {
				counter++;
			}
		}
//		System.out.println(aroundCoord);
		if(counter == 2 || counter == 3) {
			return 1;
		}else if(counter < 2) {
			return 2;
		}else {
			return 3;
		}
	}
}
