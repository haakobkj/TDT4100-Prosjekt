package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
	
	private CardDeck cardDeck;
	private CardDeck shuffledCardDeck;

	@BeforeEach
	public void setup() {
		cardDeck = new CardDeck();
		shuffledCardDeck = new CardDeck();
		shuffledCardDeck.shuffleDeck();
	}
	
//	Tester noen vilkårlige kort i kortstokken
	@Test
	public void testConstructor() {
		ArrayList<Card> deck = new ArrayList<Card>();
		deck.add(new Card(0,1));
		deck.add(new Card(1,8));
		deck.add(new Card(2,6));
		deck.add(new Card(3,13));
		assertEquals(deck.get(0).toString(), cardDeck.getDeck().get(0).toString());
		assertEquals(deck.get(1).toString(), cardDeck.getDeck().get(20).toString());
		assertEquals(deck.get(2).toString(), cardDeck.getDeck().get(31).toString());
		assertEquals(deck.get(3).toString(), cardDeck.getDeck().get(51).toString());
	}
	
	@Test
	public void testShuffleDeck() {
		assertNotEquals(cardDeck, shuffledCardDeck);
	}
	
	@Test
	public void testHandOutCard() {
		CardDeck shuffledCardDeck1 = shuffledCardDeck;
		CardDeck shuffledCardDeck2 = shuffledCardDeck;
		assertEquals(shuffledCardDeck2.getDeck().get(0), shuffledCardDeck1.handOutCard());
	}
}
