import java.util.ArrayList;
import java.util.Random;
public class sorting_practice {
	public static void main(String[] args) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		ArrayList<Integer> two = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			newList.add(rand.nextInt(30));
			one.add(rand.nextInt(30));
			two.add(rand.nextInt(30));
		}
//		System.out.println(newList + " Before");
//		selectionSort(newList);
//		System.out.println(newList + " After");
//		System.out.println(one + " LIST ONE");
//		System.out.println(two + " LIST TWO");
		mergeSort(one, 0, one.size() - 1);
		System.out.println(one);
	}
	public static void selectionSort(ArrayList<Integer> list) {
		  for (int outer = list.size() - 1; outer >= 0; outer--){
		   int min = outer;
		    for (int inner = 0 ; inner < outer; inner++){
		    	System.out.print( "-(" + list.get(inner) + ", " + list.get(min) + ")");
		      if (list.get(inner) > list.get(min)) {
		        min = inner;
		      }
		    }
		    System.out.println("");

		    int temp = list.get(outer);
		    list.set(outer, list.get(min));
		    list.set(min, temp);
		  }
	}
	public static void mergeSort(ArrayList<Integer> x, int first, int last){ 
	      ArrayList<Integer> l = new ArrayList<Integer>();
	      ArrayList<Integer> r = new ArrayList<Integer>();
	      
	      if (x.size() > 1) {
	        int middle = x.size() / 2;
	        for (int i = 0; i < middle; i++) l.add(x.get(i));
	        for (int j = middle; j < x.size(); j++) r.add(x.get(j));
	        mergeSort(l, first, middle);
	        mergeSort(r, middle + 1, last);
	        merge(x, l, r);
	      }
	 }
	public static void merge(ArrayList<Integer> x, ArrayList<Integer> left, ArrayList<Integer> right){
        
	       ArrayList<Integer> storage = new ArrayList<Integer>(); 

	       
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
}
