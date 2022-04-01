import java.util.ArrayList;
import java.util.Random;

public class P4_Paulus_Alexander_QuickSort {
	public static int divide(ArrayList<Comparable> x, int first, int last){
        Comparable point = x.get(last);
        
        for(int i = first; i < last; i++){
            if(x.get(i).compareTo(point) > 0){
                Comparable temp= x.get(first);
                x.set(first, x.get(i));
                x.set(i, temp);		
                first++;
            }
        }
        Comparable temp = x.get(first);
        x.set(first, point);
        x.set(last, temp);
 
        return first;
    }
 
    public static void quickSort(ArrayList<Comparable> x, int first, int last){
        int splitPoint = divide(x, first, last);
    
        if(splitPoint < last) quickSort(x, splitPoint + 1, last);
        if(splitPoint > first) quickSort(x, first, splitPoint - 1);
    }
    
    public static void main(String[] x) {
		Random rand = new Random();
		
		ArrayList<Comparable> newList = new ArrayList<Comparable>();
		
        for (int i = 0; i < 200; i++) newList.add(rand.nextInt(200));
		
        quickSort(newList, 0, newList.size()-1);
        
        for (int i = 0; i < newList.size() - 1; i++) {
			if(i % 30 == 0) System.out.println("");
			System.out.print(newList.get(i) + "  ");
		}
    }
    
}
