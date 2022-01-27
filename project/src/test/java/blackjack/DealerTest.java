package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {
	
	private Dealer dealer;
	
	@BeforeEach
	public void setup() {
		dealer = new Dealer();
	}

	@Test
	public void testInitializeNumberOfAces() {
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 1));
		dealer.initializeNumberOfAces();
		assertEquals(dealer.getNumberOfAces(), 2);		
	}
	
	@Test
	public void testCalculateTotalHandValue() {
		dealer.getDealersHand().add(new Card(0, 7));
		dealer.getDealersHand().add(new Card(1, 10));
		dealer.calculateTotalHandValue();
		assertEquals(dealer.getTotalHandValue(), 17);
		dealer.clearHand();
		
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 1));
		dealer.calculateTotalHandValue();
		assertEquals(dealer.getTotalHandValue(), 12);
		dealer.clearHand();
		
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 10));
		dealer.calculateTotalHandValue();
		assertEquals(dealer.getTotalHandValue(), 21);
		dealer.clearHand();
	
		dealer.getDealersHand().add(new Card(0, 7));
		dealer.getDealersHand().add(new Card(1, 8));
		dealer.getDealersHand().add(new Card(0, 10));
		dealer.calculateTotalHandValue();
		assertEquals(dealer.getTotalHandValue(), 25);
		dealer.clearHand();
		
//		Tester logikk for flere A
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 1));
		dealer.getDealersHand().add(new Card(1, 10));
		dealer.calculateTotalHandValue();
		assertEquals(dealer.getTotalHandValue(), 12);
		dealer.clearHand();
		
//		Tester logikk for flere A
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 1));
		dealer.getDealersHand().add(new Card(1, 4));
		dealer.calculateTotalHandValue();
		assertEquals(dealer.getTotalHandValue(), 16);
		dealer.clearHand();
	}
	
	@Test
	public void testCheckIfBusted() {
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 10));
		dealer.calculateTotalHandValue();
		assertFalse(dealer.checkIfBusted());
		dealer.clearHand();
		
		dealer.getDealersHand().add(new Card(0, 7));
		dealer.getDealersHand().add(new Card(1, 8));
		dealer.getDealersHand().add(new Card(0, 10));
		dealer.calculateTotalHandValue();
		assertTrue(dealer.checkIfBusted());
	}
	
	@Test
	public void testCheckDealerBlackjack() {
		dealer.getDealersHand().add(new Card(0, 1));
		dealer.getDealersHand().add(new Card(1, 10));
		dealer.calculateTotalHandValue();
		assertTrue(dealer.checkDealerBlackjack());
		dealer.clearHand();
		
		dealer.getDealersHand().add(new Card(0, 4));
		dealer.getDealersHand().add(new Card(1, 10));
		dealer.getDealersHand().add(new Card(1, 7));
		assertFalse(dealer.checkDealerBlackjack());
	}
}
