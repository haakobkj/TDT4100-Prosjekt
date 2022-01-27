package blackjack;

public class Card {

	private int suit;
	private int rank;
	private int value;
	
	private String[] suits = {"Clubs ♣","Diamonds ♦","Hearts ♥","Clubs ♠"};
	private String[] ranks = {"Joker", "A","2","3","4","5","6","7","8","9","10","J","Q","K"}; //Har med jokeren for å forenkle indekseringen
	
	public Card(int suit, int rank) {
		if (suit > suits.length -1 || suit < 0)
			throw new IllegalArgumentException("The suit is not accepted");
		if (rank > ranks.length - 1 || rank < 1)
			throw new IllegalArgumentException("The rank is not accepted");
		
		this.suit = suit;
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public  int getRank() {
		return rank;
	}
	
	public int getValue() {
		if (rank == 1)
			value = 11;
		else if (rank >= 10)
			value = 10;
		else
			value = rank;
		
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return ranks[rank]+" of "+suits[suit];
	}
}
