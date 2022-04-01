

import java.io.File;

public class mainTester {

	public static void main(String[] args) {
		File testert = new File("life100.txt");
		P6_Vallabhaneni_Vishal_Life tester = new P6_Vallabhaneni_Vishal_Life(testert);
		tester.printBoard();
	}

}
