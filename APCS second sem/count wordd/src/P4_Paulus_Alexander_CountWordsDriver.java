import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * I felt that this lab was super interesting and also very fun. In my spare time I like to make discord bots using
 * java script, and alot of the work is deserializing and working with data structures. 
 * This lab, however, was different from my experience with working with data structures, this time I sorted the data.
 *  Typically I don't have to sort any data because it is already in order most of the time 
 * during my side projects, so this was a fun learning experience that I can hopefully use in the future.
 *  Overall I found this lab to be super interesting and great because I really enjoy working
 * with arrayLists. 
 * 
 * @author alexander Paulus
 * @version 2/8/20
 *
 */


public class P4_Paulus_Alexander_CountWordsDriver {
	static ArrayList<String> firstStoreArr = new ArrayList<String>();
	static ArrayList<String> secondStoreArr = new ArrayList<String>();
	static ArrayList<String> wordStoreArr = new ArrayList<String>();
	static ArrayList<Integer> wordCountStoreArr = new ArrayList<Integer>();
	static ArrayList<String> finArr = new ArrayList<String>();
	public static void main(String args[]) {
		P4_Paulus_Alexander_CountWordsDriver x = new P4_Paulus_Alexander_CountWordsDriver();
		x.loadFile("simple.txt", firstStoreArr);
		x.filterWords(firstStoreArr,secondStoreArr);
		x.organizeArrayLists(secondStoreArr, wordStoreArr, finArr, wordCountStoreArr);
		quickSort(finArr, 0, finArr.size()-1);
		x.printWords(finArr, wordStoreArr, secondStoreArr);
	}
	public void filterWords(ArrayList<String> firstStoreArr, ArrayList<String> secondStoreArr) {
		for (String string : firstStoreArr) {
			String temp = "";
			if(string == "-") {
				System.out.println("Hello");
			}else {
				for (int i = 0; i < string.length(); i++) {
					if(Character.isLetter(string.charAt(i)) || string.charAt(i) =='\'' || 
							((i < string.length() - 1) && string.charAt(i) == '-' && Character.isLetter(string.charAt(i+1)) && Character.isLetter(string.charAt(i - 1)))) {
							temp += string.substring(i,i+1).toLowerCase();
					}
				}
				if(temp == "" ) {
					
				}else {
					secondStoreArr.add(temp);	
				}
					
			}
		}
	}
	
	public void organizeArrayLists(ArrayList<String> secondStoreArr, ArrayList<String> wordStoreArr, ArrayList<String> finArr, ArrayList<Integer> wordCountStoreArr) {
		for (int i = 0; i < secondStoreArr.size(); i++) {
			if(!(wordStoreArr.contains(secondStoreArr.get(i))) && !(secondStoreArr.get(i).equals(" "))) {
				wordStoreArr.add(secondStoreArr.get(i));
			}
		}
		for (String string : wordStoreArr) {
			int tempNum = 0;
			for (int i = 0; i < secondStoreArr.size(); i++) {
				if(secondStoreArr.get(i).equalsIgnoreCase(string)) {
					tempNum++;
				}
			}
			wordCountStoreArr.add(tempNum);
		}
		for (int i = 0; i < wordStoreArr.size(); i++) {
			if(wordCountStoreArr.get(i) > 9) {
				finArr.add(wordCountStoreArr.get(i) + " " + wordStoreArr.get(i));
			}else {
				finArr.add(" " + wordCountStoreArr.get(i) + " " + wordStoreArr.get(i));
			}
			
		}
	}
	
	public void printWords(ArrayList<String> finArr, ArrayList<String> wordStoreArr, ArrayList<String> secondStoreArr) {
		if(finArr.size() >= 30) {
			for (int i = 0; i < 30; i++) {
				System.out.println(i+1 + " " + finArr.get(i));
			}
		}else {
			for (int i = 0; i < finArr.size(); i++) {
				System.out.println(i+1 + " " + finArr.get(i));
			}
		}
		System.out.println("");
		System.out.println("Number of unique words used = " + wordStoreArr.size());
		System.out.println("Total # of words = " + secondStoreArr.size());
	}
	
	public static int divide(ArrayList<String> x, int first, int last){
        String point = x.get(last);
        
        for(int i = first; i < last; i++){
            if( Integer.parseInt(x.get(i).substring(0,3).trim()) > Integer.parseInt(point.substring(0,3).trim())){
                String temp= x.get(first);
                x.set(first, x.get(i));
                x.set(i, temp);		
                first++;
            }
        }
        String temp = x.get(first);
        x.set(first, point);
        x.set(last, temp);
 
        return first;
    }
 
    public static void quickSort(ArrayList<String> x, int first, int last){
        int splitPoint = divide(x, first, last);
    
        if(splitPoint < last) quickSort(x, splitPoint + 1, last);
        if(splitPoint > first) quickSort(x, first, splitPoint - 1);
    }
	
	public void loadFile(String inFileName, ArrayList<String> newList){
		try {
	      File myObj = new File(inFileName);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	    	if(myReader.hasNext()) {
		    	String[] tempVal = myReader.next().split("\t");
		    	newList.add(tempVal[0]);  
	    	}else {
	    		break;
	    	}
	      }
	      
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }  
	}
	
}

