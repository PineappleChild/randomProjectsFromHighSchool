package sortingQuiz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P4_Paulus_Alexander_PracticeQuiz {

	public static void main(String[] args) {

		String[] words = { "abe", "bat", "car", "cat", "dab", "elf", "fig", "get" };
		ArrayList<Comparable> wordList = new ArrayList<Comparable>();

		for (String word : words) {
			wordList.add(word);
		}

		System.out.println("***** Selection Sort *****");
		Collections.shuffle(wordList);
		System.out.println("Before sorting: " + wordList);
		selectionSort(wordList);
		System.out.println("After sorting:  " + wordList);

		int[] intArray = generateRandomIntArray(8);
		
		System.out.println("\n***** Midsertion Sort *****");
		System.out.println("Before sorting: " + Arrays.toString(intArray));
		midsertionSort(intArray);
		System.out.println("After sorting:  " + Arrays.toString(intArray));

		intArray = generateRandomIntArray(8);
		
		System.out.println("\n***** V Sort *****");
		System.out.println("Before sorting: " + Arrays.toString(intArray));
		vSort(intArray);
		System.out.println("After sorting:  " + Arrays.toString(intArray));
	}

	private static int[] generateRandomIntArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
        	arr[i] = (int)(Math.random() * length);
        }
		return arr;
	}
	
	public static void selectionSort(ArrayList <Comparable> list){
	      for(int a = 0; a < list.size() - 1; a++) {
	            int curr = a;
	            for (int b = a + 1; b < list.size(); b++) {
	                if(list.get(curr).compareTo(list.get(b)) < 0) {
	                    curr = b;
	                }
	            }
	            Comparable tempVal = list.get(curr);
	            list.set(curr,list.get(a));
	            list.set(a, tempVal);
	        }
	  }
	public static void insertionSort(ArrayList <Comparable> list){
	      for (int a = 1; a < list.size(); a++) {
	            Comparable curr = list.get(a);
	            int prev = a - 1;
	            while((prev >= 0) && list.get(prev).compareTo(curr) > 0) {
	                list.set(prev + 1, list.get(prev));
	                prev--;
	            }
	            list.set(prev + 1,curr);
	        }
	    
	  }

	public static void midsertionSort(int[] arr) {
		double iters = arr.length/2;
			for(int j = 0; j < iters; j++) {
				if(arr[arr.length - j - 1] < arr[j]) {
					int tempVal = arr[arr.length - j - 1];
					arr[arr.length - j - 1] = arr[j];
					arr[j] = tempVal;
				}
			}
			for (int i = (int) iters - 1; i > 0 ; i--) {
				int indexLeft = i - 1;
				int indexRight = arr.length - i;
				int leftIndexSwap = -1;
				int rightIndexSwap = -1;
				System.out.println("");
				for(int j = i; j < indexRight; j++) {
					if(arr[indexLeft] > arr[j]) {
						leftIndexSwap = j;
					}
					if(arr[indexRight] < arr[j]) {
						rightIndexSwap = j;
					}
				}
				System.out.print("(" + indexLeft + ", " + indexRight + ")");
				System.out.print(" - ");
				System.out.print("(" + leftIndexSwap + ", " + rightIndexSwap + ")");
				if(leftIndexSwap != -1) {
					System.out.println(leftIndexSwap - indexLeft );
					int tempVal = indexLeft;
					int tempVal2 = leftIndexSwap;
					for(int k = indexLeft; k <= leftIndexSwap; k++) {
						int temp = arr[k + 1];
						arr[k+1] = arr[k];
						arr[k] = temp;
					}
				}else {
					
					
				}
				
				if(rightIndexSwap != -1) {
					int tempVal = indexRight;
					int tempVal2 = rightIndexSwap;
					for(int k = 0; k < indexRight - rightIndexSwap; k++) {
						int temp = arr[k + 1];
						System.out.print(arr[k+1] + " ");
						arr[k] = temp;
						arr[k + 1] = arr[k];
					}
				}else {
					
					
				}
			}
			System.out.println("");
	}
	

	public static void vSort(int[] arr) {
		int length = arr.length;
		ArrayList<Comparable> oneList = new ArrayList<Comparable>();
		ArrayList<Comparable> twoList = new ArrayList<Comparable>();
		
		for (int i = 0; i < length; i++){
			if (i < (length + 1)/2) {
				oneList.add(arr[i]);
			}else {
				twoList.add(arr[i]);
			}
		}
		
		selectionSort(oneList);
		insertionSort(twoList);
		for(int i = 0; i < twoList.size();i++) {
			oneList.add(twoList.get(i));
		}
		for (int i = 0; i < oneList.size(); i++) {
			arr[i] = (int) oneList.get(i);
		}
	}
	
}
