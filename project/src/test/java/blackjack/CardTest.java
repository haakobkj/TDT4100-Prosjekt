package blackjack;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CardTest {
	
	private boolean checkCard(Card card, int suit, int rank) {
		return card.getSuit() == suit && card.getRank() == rank;
	}

	@Test
	public void testConstructor() {
		assertTrue(checkCard(new Card(0, 1), 0, 1));
		assertTrue(checkCard(new Card(1, 5), 1, 5));
		assertTrue(checkCard(new Card(2, 10), 2, 10));
		assertTrue(checkCard(new Card(3, 13), 3, 13));
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Card(14, 0);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Card(-1, 1);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Card(4, 10);
		});
	}
	
	@Test
	public void testGetValue() {
		assertEquals(new Card(0,2).getValue(), 2);
		assertEquals(new Card(1,7).getValue(), 7);
		assertEquals(new Card(1,10).getValue(), 10);
		assertEquals(new Card(2,11).getValue(), 10);
		assertEquals(new Card(2,12).getValue(), 10);
		assertEquals(new Card(3,13).getValue(), 10);
		assertEquals(new Card(3,1).getValue(), 11);
	}
	
	@Test
	public void testToString() {
		assertEquals("A of Clubs ♣", new Card(0,1).toString());
		assertEquals("2 of Diamonds ♦", new Card(1,2).toString());
		assertEquals("J of Hearts ♥", new Card(2,11).toString());
		assertEquals("K of Clubs ♠", new Card(3,13).toString());
	}
}
