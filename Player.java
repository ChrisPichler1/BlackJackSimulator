package simulation;

import java.util.ArrayList;

public class Player {
	//id is an integer representing the caller's ID number
	public int handTotal;
	public ArrayList<Card> cards;

	public Player() {
		handTotal = 0;
		cards = new ArrayList<Card>();
	}
	

	public Player(int total) {
		handTotal = total;
	}
}