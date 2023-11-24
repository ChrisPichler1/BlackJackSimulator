package simulation;

import java.util.ArrayList;

public class Dealer {
	//id is an integer representing the caller's ID number
	public int handTotal;
	public ArrayList<Card> cards;

	public Dealer() {
		handTotal = 0;
		cards = new ArrayList<Card>();
	}
	

	public Dealer(int total) {
		handTotal = total;
	}
}