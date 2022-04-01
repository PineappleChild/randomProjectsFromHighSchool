package sortingQuiz;
/*
 * 
 * I found this lab to still be somewhat challenging. Most of the lab was not that bad, the cards section was not a major hurdle, but the midesertion
 * was very confusing. I had trouble figuring the action of inserting the element into the middle of the list, I had all the indexes figured out, but the 
 * swapping part did not work for me. Overall this lab did take longer than I expected and was filled with some hard challenges. 
 * 
 * @Author Alexander Paulus
 * @version 2/8/2020
 */
public class P4_Paulus_Alexander_Card implements Comparable<P4_Paulus_Alexander_Card>{

	/* Attributes */
	private char symbol;
	private int value;
	private String suit;
	private boolean isTrump;

	/* Constructors */
	public P4_Paulus_Alexander_Card(char symbol, int value, String suit) {
		this.symbol = symbol;
		this.value = value;
		this.suit = suit;
		this.isTrump = false;
	}

	public P4_Paulus_Alexander_Card(char symbol, int value, String suit, boolean isTrump) {
		this.symbol = symbol;
		this.value = value;
		this.suit = suit;
		this.isTrump = isTrump;
	}

	/* Getters and Setters */
	public char getSymbol() {
		return symbol;
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}
	
	public boolean isTrump() {
		return isTrump;
	}

	public void setTrump(boolean isTrump) {
		this.isTrump = isTrump;
	}

	/* Other methods */
	@Override
	public String toString() {
		return symbol + "" + suit.charAt(0);
	}
	public int compareTo(P4_Paulus_Alexander_Card x) {
		if((this.getSuit().equals(x.getSuit())) && (this.getValue() == x.getValue()) && (!this.isTrump() && !x.isTrump())){
			return 0;
		}else if((this.getSuit().equals(x.getSuit())) && (!this.isTrump() && !x.isTrump())) {
			if(this.getValue() > x.getValue()) {
				return this.getValue();
			}
			return -x.getValue();
		}else if(this.getSuit().equals(x.getSuit()) && (this.isTrump() && x.isTrump())) {
			if(this.getValue() > x.getValue()) {
				return this.getValue();
			}
			return -x.getValue();
		}else if(!(this.getSuit().equals(x.getSuit())) && (this.isTrump() || x.isTrump())) {
			if(this.isTrump()) {
				return this.getValue();
			}
		}else if((!this.isTrump() && !x.isTrump())) {
			int thisVal = suitVal(this.getSuit());
			int xVal = suitVal(x.getSuit());
			if(thisVal > xVal) {
				return this.getValue();
			}
			return -x.getValue();
		}
		return -1;
	}
	public int suitVal(String string) {
		if(string.equals("Spades")) {
			return 4;
		}else if(string.equals("Hearts")) {
			return 3;
		}else if(string.equals("Diamonds")) {
			return 2;
		}
		return 1;
	}
}
