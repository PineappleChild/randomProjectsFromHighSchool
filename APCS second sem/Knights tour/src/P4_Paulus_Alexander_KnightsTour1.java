import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * This lab was very interesting, I found the most fascinating part to be printing out the chess board.
 * I find it satisfying to watch the board slowly fill up with numbers and it was really relieving to finally debug the entire code.
 * I learned that I should better organize my code so when I look at it again in the future I have a better understanding of how it works, 
 * this could help me a lot on assignments like this one where I resumed the project after a 2 day gap.
 * 
 * 
 * @author Alexander Paulus
 *
 */
public class P4_Paulus_Alexander_KnightsTour1 {
	
	public static void main(String[] args) {
		P4_Paulus_Alexander_KnightsTour1 kt = new P4_Paulus_Alexander_KnightsTour1();
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		ArrayList<int[]> validMoves = new ArrayList<int[]>();
		
		ArrayList<int[]> usedMoves = new ArrayList<int[]>();
		int counter = 1;
		int row = 1;
		int col = 1;
		
		int[] firstMove = new int[2];
		firstMove[0] = 1;
		firstMove[1] = 1;
		usedMoves.add(firstMove);
		
		for(int i = 0; i < 8; i++) {
			ArrayList<Integer> addList = new ArrayList<Integer>();
			for (int j = 0; j < 8; j++) {
				addList.add(0);
			}
			newList.add(addList);
		}
		newList.get(0).set(0, counter++);
		for (int b = 0; b < 50; b++) {
		ArrayList<int[]> sortedMoves = new ArrayList<int[]>();
		System.out.println("(" + row + ", " + col + ")" + " current coord");
		for (int[] coord : kt.genMoves(row, col)) {
			if(kt.isValidMove(coord)) {
				validMoves.add(coord);
			}
		}
		for (int[] coord : validMoves) {
//			System.out.println(kt.isUsedMove(usedMoves, coord) + " " + Arrays.toString(coord));
			if(kt.isUsedMove(usedMoves, coord)) {
				sortedMoves.add(coord);
			}
		}
//		for (int[] i : sortedMoves) {
//			System.out.print(Arrays.toString(i) + " ");
//		}
			
	//		for (int[] coord : validMoves) {
	//			System.out.println(Arrays.toString(coord));
	//		}
	//		System.out.println(validMoves.size());
	//		while(kt.canMove(validMoves, newList)) {
			
				Random rand = new Random();
			    int randomNum = rand.nextInt((sortedMoves.size() - 0));
			    int[] position = sortedMoves.get(randomNum);
			    usedMoves.add(position);
			    System.out.println(Arrays.toString(position) + " selected coord");
			    row = position[0];
			    col = position[1];
			    newList.get(position[0] - 1).set(position[1] - 1, counter++);
			    for (int[] i : usedMoves) {
					System.out.print(Arrays.toString(i));
				}
			    kt.printBoard(newList);
		}
	}
	
	boolean isValidMove(int[] x){
		for (int i = 0; i < x.length; i++) {
			if((x[i] < 1 || x[i] > 8)) {
				return false;
			}
		}
		return true;
	}
	
	boolean isUsedMove(ArrayList<int[]> x, int[] y) {
		if(x.size() < 1) {
			return true;
		}else {
			for (int i = 0; i < x.size(); i++) {
				if(Arrays.equals(x.get(i), y)) {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean canMove(ArrayList<int[]> x, ArrayList<ArrayList<Integer>> newList) {
		if(x.size() == 0) {
			return false;
		}else {
			int counter = 1;
			for (int[] coord : x) {
				System.out.println(newList.get(coord[0]).get(coord[1]));
				if(newList.get(coord[0]).get(coord[1]) > 0) {
					counter++;
				}
			}
			if(counter == x.size()) {
				return false;
			}
		}
		return true;
	}
	
	ArrayList<int[]> genMoves(int row, int col){
		ArrayList<int[]> sendList = new ArrayList<int[]>();
		for (int i = 0; i < 2; i++) {
			if(i == 0){//vertical
				for (int j = 0; j < 2; j++) {
					if (j == 0) {//up
						for (int k = 0; k < 2; k++) {
							if (k == 0) {//right
								int[] coordInt =  new int[2];
								coordInt[0] = row + 1;
								coordInt[1] = col - 2;
								sendList.add(coordInt);
							}else {//left
								int[] coordInt =  new int[2];
								coordInt[0] = row - 1;
								coordInt[1] = col - 2;
								sendList.add(coordInt);
							}
						}
					}else {//down
						for (int k = 0; k < 2; k++) {
							if (k == 0) {//right
								int[] coordInt =  new int[2];
								coordInt[0] = row + 1;
								coordInt[1] = col + 2;
								sendList.add(coordInt);
							}else {//left
								int[] coordInt =  new int[2];
								coordInt[0] = row - 1;
								coordInt[1] = col + 2;
								sendList.add(coordInt);
							}
						}
					}
				}
			}else {//horizontal
				for (int j = 0; j < 2; j++) {
					if (j == 0) {//right
						for (int k = 0; k < 2; k++) {
							if (k == 0) {//up
								int[] coordInt =  new int[2];
								coordInt[0] = row + 2;
								coordInt[1] = col - 1;
								sendList.add(coordInt);
							}else {//down
								int[] coordInt =  new int[2];
								coordInt[0] = row + 2;
								coordInt[1] = col + 1;
								sendList.add(coordInt);
							}
						}
					}else {//left
						for (int k = 0; k < 2; k++) {
							if (k == 0) {//up
								int[] coordInt =  new int[2];
								coordInt[0] = row - 2;
								coordInt[1] = col - 1;
								sendList.add(coordInt);
							}else {//down
								int[] coordInt =  new int[2];
								coordInt[0] = row - 2;
								coordInt[1] = col + 1;
								sendList.add(coordInt);
							}
						}
					}
				}
			}
		}
		return sendList;
	}
	void printBoard(ArrayList<ArrayList<Integer>> x) {
		System.out.println("");
		int counter = 1;
		for (int i = 0; i <= 8; i++) {
			if(i == 0) {
				System.out.print("  ");
			}else {
				System.out.print(i + " ");
			}
		}
		System.out.println("");
		for (ArrayList<Integer> arrayList : x) {
			System.out.print(counter++ + " ");
			for (Integer element : arrayList) {
				System.out.print(element + " ");
			}
			System.out.println("");
		}
	}
}



