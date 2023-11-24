package simulation;

import java.util.*;

public class Simulation {

	public static void main(String[] args) {
		int testHands = 1000000;
		
		int playerWins = 0;
		int dealerWins = 0;
		int pushes = 0;
		
		
		//Dealers draw from a pool of cards of 6 complete decks at the start of each hand
		while(testHands > 0) {
			testHands--;
			WaitingQueue<Card> deck = new WaitingQueue<>(-1);
			ArrayList<Card> cards = new ArrayList<Card>();
			
			int randCardIndex;
			
			//Add 24 of each card number (4 cards per deck * 6 decks)
			for(int i = 2; i < 11; i++) {
				for(int j = 0; j < 24; j++) {
					cards.add(new Card(i, false));
				}
			}
			
			//Add 18 face cards with value = 10 (12 face cards per deck * 6 decks)
			for(int i = 0; i < 72; i++) {
				cards.add(new Card(10, false));
			}
			
			//Add 24 aces to the deck (4 aces per deck * 6 decks)
			for(int i = 0; i < 24; i++) {
				cards.add(new Card(11, true));
			}
			
			//The deck has been initialized for this iteration
			
			Dealer dealer = new Dealer();
			Player player = new Player();
			
			Random rand = new Random();
			
			while(cards.size() > 0) {
				randCardIndex = rand.nextInt(cards.size());
				Card card = cards.get(randCardIndex);
				deck.offer(card);
				cards.remove(randCardIndex);
			}
			
			//The deck is inserted in random order into the queue "deck"
			
			Card nextCard;
			
			
			//Initialize the opening hands
			nextCard = deck.poll();
			dealer.cards.add(nextCard);
			dealer.handTotal += nextCard.value;

			
			nextCard = deck.poll();
			player.cards.add(nextCard);
			player.handTotal += nextCard.value;
			
			nextCard = deck.poll();
			player.cards.add(nextCard);
			player.handTotal += nextCard.value;
			
			
			if(player.handTotal == 21) {
				System.out.println("The player was dealt a 21");
				playerWins++;
				continue;
			}
			
			
			//Begin the dealer scenarios
			
			int acesAccountedFor = 0;
			Card card;
			
			if(dealer.cards.get(0).value == 2) {
				do {
					if(player.handTotal <= 12) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
						
						if(player.handTotal > 21) {
							int aces = numOfAces(player);
							if(aces - acesAccountedFor == 0) {
								//Player broke out
								break;
							}
							else {
								acesAccountedFor++;
								player.handTotal -= 10;
								continue;
							}
						}
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			else if(dealer.cards.get(0).value == 3) {
				do {
					if(player.handTotal <= 12) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
						
						if(player.handTotal > 21) {
							int aces = numOfAces(player);
							if(aces - acesAccountedFor == 0) {
								//Player broke out
								break;
							}
							else {
								acesAccountedFor++;
								player.handTotal -= 10;
								continue;
							}
						}
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			else if(dealer.cards.get(0).value == 4) {
				do {
					if(player.handTotal <= 11) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			else if(dealer.cards.get(0).value == 5) {
				do {
					if(player.handTotal <= 11) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			else if(dealer.cards.get(0).value == 6) {
				do {
					if(player.handTotal <= 11) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			else if(dealer.cards.get(0).value > 6) {
				do {
					if(player.handTotal <= 16) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
						
						if(player.handTotal > 21) {
							int aces = numOfAces(player);
							if(aces - acesAccountedFor == 0) {
								//Player broke out
								break;
							}
							else {
								acesAccountedFor++;
								player.handTotal -= 10;
								continue;
							}
						}
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			else if(dealer.cards.get(0).value == 8) {
				do {
					if(player.handTotal <= 12) {
						//Hit
						card = deck.poll();
						player.handTotal += card.value;
						player.cards.add(card);
						
						if(player.handTotal > 21) {
							int aces = numOfAces(player);
							if(aces - acesAccountedFor == 0) {
								//Player broke out
								break;
							}
							else {
								acesAccountedFor++;
								player.handTotal -= 10;
								continue;
							}
						}
					}
					else {
						//Stand
						break;
					}
				}while(true);
			}
			
			acesAccountedFor = 0;
			
			if(player.handTotal > 21) {
				dealerWins++;
				System.out.println("The player broke out and the dealer won");
			}
			else {
				do {
					//The dealer draws another card
					card = deck.poll();
					dealer.cards.add(card);
					dealer.handTotal += card.value;
					
					if(dealer.handTotal > 21) {
						int aces = numOfAces(dealer);
						if(aces - acesAccountedFor == 0) {
							//Dealer broke out
							playerWins++;
							System.out.println("The dealer broke out!");
							break;
						}
						else {
							acesAccountedFor++;
							dealer.handTotal -= 10;
							continue;
						}
					}
					else if(dealer.handTotal > 16) {
						//The dealer stands;
						if(dealer.handTotal > player.handTotal) {
							//The dealer wins
							dealerWins++;
							System.out.println("The dealer outscored the player");
						}
						else if(dealer.handTotal == player.handTotal) {
							//The player and dealer tie
							pushes++;
							System.out.println("The dealer and the player tied");
						}
						else {
							//The player wins
							playerWins++;
							System.out.println("The player outscores the dealer");
						}
						break;
					}
					else {
						//The dealer will draw another card
						continue;
					}
				}while(true);
			}
		}
		
		System.out.println("Player wins: " + playerWins);
		System.out.println("Dealer wins: " + dealerWins);
		System.out.println("Pushes: " + pushes);
	}
	
	public static int numOfAces(Dealer dealer) {
		int numOfAces = 0;
		
		for(int i = 0; i < dealer.cards.size(); i++) {
			Card card = dealer.cards.get(i);
			if(card.isAce == true) {
				numOfAces++;
			}
		}
		
		return numOfAces;
	}
	
	public static int numOfAces (Player player) {
		int numOfAces = 0;
		
		for(int i = 0; i < player.cards.size(); i++) {
			Card card = player.cards.get(i);
			if(card.isAce == true) {
				numOfAces++;
			}
		}
		
		return numOfAces;
	}

}
