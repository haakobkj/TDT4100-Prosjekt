package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck { 
	
	private ArrayList<Card> deck;
	
	public CardDeck() {
	    deck = new ArrayList<Card>();
	    for (int i=0; i<4; i++)
	    {
	        for (int j=1; j<=13; j++) {
	            deck.add(new Card(i,j));
	        }
	    }
	}

	public void shuffleDeck () {
		Collections.shuffle(deck);
	}
	
	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	public Card handOutCard() {
		return deck.remove(0);
	}
}
