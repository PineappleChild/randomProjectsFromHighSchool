import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class P4_Paulus_Alexander_MergeTemplate {
 
	/**
	*  Sorts any ArrayList of Comparable objects using Selection Sort.
	*
	* @param  list  reference to an array of integers to be sorted
	*/
	public void selectionSort(ArrayList <Comparable> list) {
		for (int i = 0; i < list.size()-1; i++) 
        { 
            int curr = i; 
            for (int j = i+1; j < list.size(); j++) 
                if (list.get(j).compareTo(list.get(curr)) < 0) 
                    curr = j; 
            Comparable temp = list.get(curr); 
            list.set(curr, list.get(i));
            list.set(i, temp);
        } 
	}
 
	/**
	 *  Write a merge method to merge two sorted lists.
	 *
	 *  Preconditions: Lists A and B are sorted in nondecreasing order.
	 *  Action:        Lists A and B are merged into one list, C.
	 *  Postcondition: List C contains all the values from
	 *                 Lists A and B, in nondecreasing order.
	 */
	public void merge (ArrayList <Comparable> a, ArrayList <Comparable> b, ArrayList <Comparable> c) {
		int n = a.size() + b.size();
		int mid = (n - 2)/ 2;
		for(Comparable element : a) {
			c.add(element);
		}
		for(Comparable element : b) {
			c.add(element);
		}
		mergeSort(c, 0, n - 2);
	}
	
	void mergeSort(ArrayList <Comparable> a, int first, int last){
				ArrayList <Comparable> leftList = new ArrayList<Comparable>();
				ArrayList <Comparable> rightList = new ArrayList<Comparable>();
				for (int i = 0; i < (a.size()-1) / 2; i++) {
					leftList.add(a.get(i));
				}
				for (int i = (a.size() - 1) / 2; i < a.size() - 1; i++) {
					rightList.add(a.get(i));
				}
				if (a.size() == 1){
				      
					   
				}else if(a.size() == 2){
					if(a.get(1).compareTo(a.get(0)) > 0) {
						a.set(1, a.get(0));
						a.set(0, a.get(1));
					}
				}else{
					   
				mergeSort(leftList, 0, (a.size() - 1)/2);
				mergeSort(rightList, 0, a.size()-1);
				merge(leftList, rightList, a);
					   
				}
	}

	/**
	*  Write a method to
	*    - Ask the user how many numbers to generate
	*    - Ask the user to enter the largest integer to generate
	*    - Initialize an ArrayList of random Integers from 1 to largestInt
	*	- Return the ArrayList
	*
	* @return an ArrayList of size specified by the user filled
	*          with random numbers
	*/
	public ArrayList <Comparable> fillArray() {
		ArrayList<Comparable> newList = new ArrayList<Comparable>();
		Random random = new Random();
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("How many numbers would you like to generatre?");

	    int numberAmount = myObj.nextInt();  // Read user input
	    
	    System.out.println("What is the larget integer you would like to generater?");
	    
	    int bigInterger = myObj.nextInt();
	    

	    for (int i = 0; i < numberAmount; i++)
	    {
	        newList.add(random.nextInt(bigInterger));
	    }
	    System.out.println(newList);
	    return newList;
	}

	/**
	*  Write a method to print out the contents of the ArrayList
	*  in tabular form, 20 columns.  You can use the \t escape character
	*  or use printf to format using fields. Thus, you should go to the
	*  next line every 20 elements.
Example:
98	7	8	56	45	6	4	3	2	1	9	888	7	7	6	5	4	3	2	9
7	6	4	8	6	7	9	0	6	8	7	4	3	7	2	4	7	6	100	68
6	4	3	8	5	7	5	67	9	7

	*/
	public void screenOutput(ArrayList <Comparable> temp) {
		for (int a = 1; a <= temp.size() - 1; a++) {
			if(a%20 == 0) {
				System.out.print(temp.get(a) + "\n");
			}else {
				System.out.print(temp.get(a) + "   ");
			}
		}
	}
}

