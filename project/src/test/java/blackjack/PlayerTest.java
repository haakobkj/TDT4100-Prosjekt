package blackjack;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	
	private Player player;

	
	@BeforeEach
	public void setup() {
		player = new Player();
	}
	
	@Test
	public void testCalculateTotalHandValue() {
		player.getPlayersHand().add(new Card(0, 7));
		player.getPlayersHand().add(new Card(1, 10));
		player.calculateTotalHandValue();
		assertEquals(player.getTotalHandValue(), 17);
		player.clearHand();
		
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 1));
		player.calculateTotalHandValue();
		assertEquals(player.getTotalHandValue(), 12);
		player.clearHand();
		
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 10));
		player.calculateTotalHandValue();
		assertEquals(player.getTotalHandValue(), 21);
		player.clearHand();
	
		player.getPlayersHand().add(new Card(0, 7));
		player.getPlayersHand().add(new Card(1, 8));
		player.getPlayersHand().add(new Card(0, 10));
		player.calculateTotalHandValue();
		assertEquals(player.getTotalHandValue(), 25);
		player.clearHand();
		
//		Tester logikk for flere A
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 1));
		player.getPlayersHand().add(new Card(1, 10));
		player.calculateTotalHandValue();
		assertEquals(player.getTotalHandValue(), 12);
		player.clearHand();
		
//		Tester logikk for flere A
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 1));
		player.getPlayersHand().add(new Card(1, 4));
		player.calculateTotalHandValue();
		assertEquals(player.getTotalHandValue(), 16);
		player.clearHand();
	}

	@Test
	public void testInitializeNumberOfAces() {
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 1));
		player.initializeNumberOfAces();
		assertEquals(player.getNumberOfAces(), 2);		
	}
	
	@Test
	public void testCheckIfBusted() {
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 10));
		player.calculateTotalHandValue();
		assertFalse(player.checkIfBusted());
		player.clearHand();
		
		player.getPlayersHand().add(new Card(0, 7));
		player.getPlayersHand().add(new Card(1, 8));
		player.getPlayersHand().add(new Card(0, 10));
		player.calculateTotalHandValue();
		assertTrue(player.checkIfBusted());
	}

	@Test
	public void testCheckPlayerBlackjack() {
		player.getPlayersHand().add(new Card(0, 1));
		player.getPlayersHand().add(new Card(1, 10));
		player.calculateTotalHandValue();
		assertTrue(player.checkPlayerBlackjack());
		player.clearHand();
		
		player.getPlayersHand().add(new Card(0, 4));
		player.getPlayersHand().add(new Card(1, 10));
		player.getPlayersHand().add(new Card(1, 7));
		assertFalse(player.checkPlayerBlackjack());
	}
}
