package simulation;

public class Card {

	public int value;
	public boolean isAce;
	
	public Card() {
		value = 0;
		isAce = false;
	}
	
	public Card(int value, boolean isAce) {
		this.value = value;
		this.isAce = isAce;
	}
}
