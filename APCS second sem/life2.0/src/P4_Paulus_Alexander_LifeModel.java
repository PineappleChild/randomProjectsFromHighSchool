import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 
 * @author alexander paulus
 *
 */
public class P4_Paulus_Alexander_LifeModel extends GridModel<Boolean>{
	int generation = 0;
	private ArrayList<GenerationListener> genArr; 
	P4_Paulus_Alexander_LifeGUI_2 lifeGUI = new P4_Paulus_Alexander_LifeGUI_2();
	public P4_Paulus_Alexander_LifeModel(Boolean[][] gridData) {
		super(gridData);
		genArr = new ArrayList<GenerationListener>();
	}
	private void runLife(int numGenerations) {
		Boolean[][] newBoolArr = new Boolean[lifeGUI.getNumRows()][lifeGUI.getNumCols()];
		for(int i = 0; i < newBoolArr.length; i++) {
			for(int j = 0; j < newBoolArr[0].length; j ++) {
				if(!staysAlive(i,j)) {
					lifeGUI.setValueAt(i, j, false);
				} else {
					lifeGUI.setValueAt(i, j, true);
				}
			}
		}	
		lifeGUI.setGrid(newBoolArr);
	}
	public int countCol(int col) {
		int counter = 0;
		if(col < 0 || col >= lifeGUI.getNumCols()) {
			return -1;
		}
		for(int i = 0; i < lifeGUI.getNumRows(); i ++) {
			if(lifeGUI.getValueAt(i, col)) {
				counter++;
			}
		}
		return counter;
	}
	public int countRow(int row) {
		int counter = 0;
		if(row < 0 || row >= lifeGUI.getNumRows()) {
			return -1;
		}
		for(int i = 0; i < lifeGUI.getNumCols(); i ++) {
			if(lifeGUI.getValueAt(row, i)) {
				counter++;
			}
		}
		return counter;
	}
	public boolean staysAlive(int rowE, int colE) {
		int row = rowE;
		int col = colE;

		for(int i = row - 1; i < row + 2; i++) {
			for(int j = col - 1; j < col + 2; j++) {
				if(i >= 0 && i < lifeGUI.getNumRows()) {
					if(j >= 0 && j < lifeGUI.getNumCols()) {
						if(!(i == row && j == col)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public int totalCount() {
		int counter = 0;
		for(int i = 0; i < lifeGUI.getNumRows(); i ++) {
			counter += countRow(i);
		}
		return counter;
	}
	void addGenerationListener(GenerationListener l) {
		genArr.add(l);
	}
	void removeGenerationListener(GenerationListener l) {
		int index = genArr.indexOf(l);
		genArr.remove(index);
	}
	void setGeneration(int gen) {
		generation = gen;
	}
	int getGeneration() {
		return generation;
	}

}
