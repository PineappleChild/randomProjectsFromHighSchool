
public class P4_Paulus_Alexander_MineSweeperController {
	int row;
	int col;
	int minenum;
	public P4_Paulus_Alexander_MineSweeperController (int row, int col, int minenum) {
		this.row = row;
		this.col = col;
		this.minenum = minenum;
	}
	public static void main(String[] args) {
		run();
	}
	public static void run() {
		P4_Paulus_Alexander_MineSweeperModel e = new P4_Paulus_Alexander_MineSweeperModel();
		try {
			while(!e.isGameOver()) {
				e.askUser();
				e.printBoard();
			}
		} catch (Exception err) {
			System.out.println(err);
		}
		
	}
}
