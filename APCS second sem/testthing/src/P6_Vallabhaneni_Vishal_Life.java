
import java.util.*;
import java.io.File;
import java.io.IOException;
public class P6_Vallabhaneni_Vishal_Life {
	Scanner s;// = null;
	boolean[][] gen;
	int numberOfRows;
	int numberOfCols;
	
	public P6_Vallabhaneni_Vishal_Life(File f) {
		try {
			Scanner s = new Scanner(f);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		if(!(s == null)) {
			if(s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				numberOfRows  = s2.nextInt();
				numberOfCols = s2.nextInt();
				gen = new boolean[numberOfRows][numberOfCols];
			}
			while(s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				gen[s2.nextInt()][s2.nextInt()] = true;
			}
		}
	}
	
	public void runLife(int numGenerations) {
		
		for(int i = 0; i < numGenerations; i ++) {
			boolean[][] temp = new boolean[gen.length][gen[0].length];
			for(int l = 0; l < temp.length; l++) {
				for(int k = 0; k < temp[0].length; k ++) {
					if(getLiveOrDie( l, k)) {
						temp[l][k] = true;
					} else {
						temp[l][k] = false;
					}
				}
			}	
			gen = temp;
		}
	}
	
	
	public boolean getLiveOrDie(int yPos, int xPos) {
		return true;
	}
	
	
	public int rowCount(int row) {
		if(row < 0 || row >= gen.length) {
			return -1;
		}
		int count = 0;
		for(int i = 0; i < gen[row].length; i ++) {
			if(gen[row][i]) {
				count++;
			}
		}
		return count;
	}
	
	
	public int colCount(int col) {
		if(col < 0 || col >= gen[0].length) {
			return -1;
		}
		int count = 0;
		for(int i = 0; i < gen.length; i ++) {
			if(gen[i][col]) {
				count++;
			}
		}
		return count;
	}
	
	public int totalCount() {
		int count = 0;
		for(int i = 0; i < gen.length; i ++) {
			count += rowCount(i);
		}
		return count;
	}
	
	public void printBoard() {
		System.out.print(" ");
		for(int i = 0; i < gen[0].length;i++) {
			System.out.print(" " + i);
		}
		System.out.println();
		for(int i = 0; i < gen.length;i++) {
			System.out.print(i + " ");
			for(int l = 0; l < gen[0].length; i++) {
				if(gen[i][l]) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.println("The number of living cells in row 9 --> " + rowCount(9));
		System.out.println("The number of living cells in col 9 --> " + colCount(9));
		System.out.println("The number of living cells total --> " + totalCount());

	}
	
}