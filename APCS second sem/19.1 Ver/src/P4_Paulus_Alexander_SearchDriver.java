import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * Completing this lab was not very difficult. Originally I did not have my method to read a file finished meaning that I did not have a 
 * way to test if the code would work in its indented form. Luckily I was able to test my binary search method using sopl. Overall the main 
 * bottle neck for this lab was the method that read the file and populated the array with the item objects.
 * 
 * @author alexander Paulus
 * @version
 *
 */
public class P4_Paulus_Alexander_SearchDriver {
	
	public static void main(String[] args) {
		
		Store s = new Store("file50.txt");
		System.out.println(s);
		
		s.sort();
		s.displayStore();
		s.testSearch();
	}
	
}

		
class Item implements Comparable<Item> {

	private int myId;
	private int myInv;

	/**
	*  Constructor for the Item object
	*
	* @param  id   id value
	* @param  inv  inventory value
	*/	
	public Item(int id, int inv){
		myId = id;
		myInv = inv;
	}
	
	/**
	*  Gets the id attribute of the Item object
	*
	* @return    The id value
	*/	
	public int getId(){
		return myId;
		// Your code here
	}
	
	/**
	*  Gets the inv attribute of the Item object
	*
	* @return    The inv value
	*/	
	public int getInv(){
		return myInv;
		// Your code here
	}
	
	/**
	*  Compares this item to another item based on id number. Returns the
	*  difference between this item's id and the other item's id. A
	*  difference of zero means the items' ids are equal in value.
	*
	* @param  other  Item object to compare to
	* @return        positive int if myId > other.myId
	*                0 if myId == other.myId
	*                negative int if myId < other.myId
	*/	
	public int compareTo(Item other){
		Integer thisItem = this.getId();
		Integer otherItem = other.getId();
		return thisItem.compareTo(otherItem);
		
	}
	
	/**
	*  Compares the Item to the specified object
	*
	* @param  otherObject  Item object to compare to
	* @return              true if equal, false otherwise
	*/	
	public boolean equals(Item other){
		if(this.equals(other)){
			return true;
		}
		return false;
	}

	/**
	*  Overrides the default toString() of Object.
	*  Returns a String representation of this object. It's up to you
	*  exactly what this looks like.
	*/
	public String toString(){
		return this.getId() + " | " + this.getInv();
	}
}	


class Store {

	private ArrayList <Item> myStore = new ArrayList <Item>();

	/**
	*  Creates a Store object from data stored in the given file name
	*
	*  @param  fName  name of the file containing id/inv pairs of data
	*/
	public Store(String fName){
		loadFile(fName);
		
	}
	
	public void testSearch(){
		   int idToFind;
		   int invReturn;
		   int index;
		   Scanner in = new Scanner(System.in);

		   System.out.println("Testing search algorithm\n");
		   do{
		      System.out.println();
		      System.out.print("Enter Id value to search for (-1 to quit) ---> ");
		      idToFind = in.nextInt();
		      //index = bsearch(new Item(idToFind, 0));
		      //recursive version call
		      index = bsearch (new Item(idToFind, 0), 0, myStore.size()-1);
		      System.out.print("Id # " + idToFind);
		      if (index == -1){
		         System.out.println(" No such part in stock");
		      }else{
		         System.out.println(" Inventory = " + myStore.get(index).getInv());
		      }
		   } while (idToFind >= 0);
		}
	
	

		/**
		   * Searches the myStore ArrayList of Item Objects for the specified
		   * item object using a iterative binary search algorithm
		   *
		   * @param idToSearch Item object containing id value being searched for
		   * @return index of Item if found, -1 if not found
		*/
	
	
		private int bsearch(Item idToSearch){
			int first = 0;
			int last = myStore.size() - 1;
			while(last >= first) {
				int middle = ((last - first)/2) + first;
				if(myStore.get(middle).getId() == idToSearch.getId()) {
					return middle;
				}else {
					if(myStore.get(middle).getId() > idToSearch.getId()) {
						first = middle - 1;
					}else {
						first = middle + 1;
					}
				}
			}
			return -1;
		}
	
	

		/**
		* Searches the specified ArrayList of Item Objects for the specified
		   * id using a recursive binary search algorithm
		   *
		   * @param idToSearch Id value being search for
		   * @param first Starting index of search range
		   * @param last Ending index of search range
		   * @return index of Item if found, -1 if not found
		*/
	
	
		private int bsearch(Item idToSearch, int first, int last){
			if(last >= first) {
				
				int middle = ((last - first)/2) + first;
				
				if(myStore.get(middle).getId() == idToSearch.getId()) {
					return middle;
				}else {
					if(myStore.get(middle).getId() < idToSearch.getId()) {
						return bsearch(idToSearch, middle + 1, last);
					}else {
						return bsearch(idToSearch, first, middle - 1);
					}
				}
			}
			return -1;
		}
		
		
	
	/**
	*  Reads a file containing id/inv data pairs one pair per line. 
	*
	*  @param  inFileName  name of file containing id/inv pairs of data
	*/
	private void loadFile(String inFileName){
		ArrayList<String> newList = new ArrayList<String>();
		int counter = 0;
		try {
	      File myObj = new File(inFileName);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	    	String[] tempVal = myReader.next().split("\t");
	    	newList.add(tempVal[0]);
//	        Item inArrData = new Item(Integer.parseInt(words[0].trim()), Integer.parseInt(words[1]));
//	        System.out.println(inArrData.toString());
//	        myStore.add(inArrData);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }  
		for (int i = 0; i < newList.size() - 1; i++) {
			Item inArrData = new Item(Integer.parseInt(newList.get(i)), Integer.parseInt(newList.get(i + 1)));
//			System.out.println(inArrData.toString());
	        myStore.add(inArrData);
		}
	    
	}
	
	/**
	*  Prints the store contents in the format shown below
	*  Line #   	Id	     	Inv
	*  1	       	184	    	14
	*  2	       	196	    	60
	*/
	public void displayStore(){
		System.out.println("Line#  ID     Inv");
		int counter = 0;
		for (Item item : myStore) {
			System.out.print(++counter + "      " + item.getId() + "      " + item.getInv() + "\n");
		} 
	}

	/**
	*  Sorts the store ArrayList using recursive mergesort
	*/
	public void sort(){
		mergeSort(myStore, 0, myStore.size()-1);
	}
	
	public void merge(ArrayList<Item> x, ArrayList<Item> left, ArrayList<Item> right){
        
       ArrayList<Item> storage = new ArrayList<Item>(); 

       
       int indexNum = 0;
       int indexR = 0;
       int indexL = 0;
       
       while (indexL < left.size() && indexR < right.size()) {
          if (left.get(indexL).compareTo(right.get(indexR)) < 0 ) {
              x.set(indexNum, left.get(indexL));
              indexL++;
          } else {
              x.set(indexNum, right.get(indexR));
              indexR++;
          }
          indexNum++;
        }
      int tempIndex = 0;
      if (indexL >= left.size()) {
          storage = right;
          tempIndex = indexR;
      } else {
          storage = left;
          tempIndex = indexL;
      }

      for (int i = tempIndex; i < storage.size(); i++) {
          x.set(indexNum, storage.get(i));
          indexNum++;
      }
}
	
	/**
	*  Recursive mergesort of an ArrayList of Items
	*
	* @param  a      reference to an ArrayList of Items to be sorted
	* @param  first  starting index of range of values to be sorted
	* @param  last   ending index of range of values to be sorted
	*/
	 public void mergeSort(ArrayList<Item> x, int first, int last){ 
	      ArrayList<Item> l = new ArrayList<Item>();
	      ArrayList<Item> r = new ArrayList<Item>();
	      
	      if (x.size() > 1) {
	        int middle = x.size() / 2;
	        for (int i = 0; i < middle; i++) l.add(x.get(i));
	        for (int j = middle; j < x.size(); j++) r.add(x.get(j));
	        mergeSort(l, first, middle);
	        mergeSort(r, middle + 1, last);
	        merge(x, l, r);
	      }
	    }
}	