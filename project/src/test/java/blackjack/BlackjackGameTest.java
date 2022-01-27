package blackjack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
	
//	Tester alle metoder som har mer logikk enn enke if-setninger og bare å kalle metoder i andre klasser.
	
	private BlackjackGame game;
	
	@BeforeEach
	public void setup() {
		game = new BlackjackGame();
	}

	@Test
	public void testDealStartCards() {
		Card card1 = game.getDeck().get(0);
		Card card2 = game.getDeck().get(1);
		Card card3 = game.getDeck().get(2);
		Card card4 = game.getDeck().get(3);
		
		game.dealStartCards();
		
		assertEquals(card1, game.getPlayersHand().get(0));
		assertEquals(card2, game.getDealersHand().get(0));
		assertEquals(card3, game.getPlayersHand().get(1));
		assertEquals(card4, game.getDealersHand().get(1));
	}
	
	@Test
	public void testHit() {
//		Når summen er under 21
		game.getPlayersHand().add(new Card(0, 7));
		game.getPlayersHand().add(new Card(0, 8));
		game.calculatePlayersTotalHandValue();
		Assertions.assertDoesNotThrow(() -> {
			game.hit();
		});
		game.resetHands();
		
//		Når summen er 21
		game.getPlayersHand().add(new Card(0, 1));
		game.getPlayersHand().add(new Card(0, 13));
		game.calculatePlayersTotalHandValue();
		Assertions.assertDoesNotThrow(() -> {
			game.hit();
		});
		game.resetHands();
		
//		Når man har bustet
		game.getPlayersHand().add(new Card(0, 7));
		game.getPlayersHand().add(new Card(1, 10));
		game.getPlayersHand().add(new Card(0, 6));
		game.calculatePlayersTotalHandValue();
		game.checkPlayerBusted();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			game.hit();;
		});
		game.resetHands();
		
//		Før man har fått kort
		game.calculatePlayersTotalHandValue();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			game.hit();;
		});
	}
	
	@Test
	public void testDealersTurnToPlay() {
//		Tester at den legger til et kort når summen er under 17
		game.getDealersHand().add(new Card(0, 4));
		game.getDealersHand().add(new Card(0, 10));
		game.dealersTurnToPlay();
		assertTrue(game.getDealersHand().size() > 2);
		game.resetHands();
		
//		Tester at den ikke legger til et kort når summen er 17
		game.getDealersHand().add(new Card(0, 13));
		game.getDealersHand().add(new Card(0, 7));
		game.dealersTurnToPlay();
		assertFalse(game.getDealersHand().size() > 2);
		game.resetHands();
		
//		Tester at den ikke legger til et kort når summen er over 17
		game.getDealersHand().add(new Card(0, 3));
		game.getDealersHand().add(new Card(0, 13));
		game.getDealersHand().add(new Card(0, 6));
		game.dealersTurnToPlay();
		assertFalse(game.getDealersHand().size() > 3);
		game.resetHands();
	}
	
	@Test
	public void testHandWinner() {
//		Player og dealer har blackjack
		game.getPlayersHand().add(new Card(0, 1));
		game.getPlayersHand().add(new Card(1, 10));
		game.getDealersHand().add(new Card(1, 1));
		game.getDealersHand().add(new Card(0, 13));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerDraw());
		game.resetHands();
		
//		Player har blackjack
		game.getPlayersHand().add(new Card(0, 1));
		game.getPlayersHand().add(new Card(1, 10));
		game.getDealersHand().add(new Card(1, 7));
		game.getDealersHand().add(new Card(0, 13));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerBlackjack());
		game.resetHands();
		
//		Dealer har blackjack
		game.getPlayersHand().add(new Card(0, 4));
		game.getPlayersHand().add(new Card(1, 10));
		game.getDealersHand().add(new Card(1, 1));
		game.getDealersHand().add(new Card(0, 13));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerLost());
		game.resetHands();
		
//		Player har blackjack, dealer har 21
		game.getPlayersHand().add(new Card(0, 1));
		game.getPlayersHand().add(new Card(1, 10));
		game.getDealersHand().add(new Card(1, 7));
		game.getDealersHand().add(new Card(0, 13));
		game.getDealersHand().add(new Card(0, 4));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerBlackjack());
		game.resetHands();
		
//		Dealer har blackjack, player har 21
		game.getPlayersHand().add(new Card(0, 4));
		game.getPlayersHand().add(new Card(1, 10));
		game.getPlayersHand().add(new Card(1, 7));
		game.getDealersHand().add(new Card(1, 1));
		game.getDealersHand().add(new Card(0, 13));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerLost());
		game.resetHands();
		
//		Player har høyere enn dealer, ingen buster
		game.getPlayersHand().add(new Card(0, 9));
		game.getPlayersHand().add(new Card(1, 10));
		game.getDealersHand().add(new Card(0, 8));
		game.getDealersHand().add(new Card(0, 10));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerWon());
		game.resetHands();
		
//		Dealer har høyere enn player, ingen buster
		game.getPlayersHand().add(new Card(0, 5));
		game.getPlayersHand().add(new Card(3, 10));
		game.getDealersHand().add(new Card(2, 9));
		game.getDealersHand().add(new Card(1, 10));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerLost());
		game.resetHands();
		
//		Normal tie
		game.getPlayersHand().add(new Card(0, 7));
		game.getPlayersHand().add(new Card(3, 10));
		game.getDealersHand().add(new Card(2, 8));
		game.getDealersHand().add(new Card(1, 9));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerDraw());
		
//		Player buster
		game.getPlayersHand().add(new Card(0, 7));
		game.getPlayersHand().add(new Card(3, 10));
		game.getPlayersHand().add(new Card(3, 8));
		game.getDealersHand().add(new Card(2, 8));
		game.getDealersHand().add(new Card(1, 9));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isPlayerBusted());
		
//		Dealer buster
		game.getPlayersHand().add(new Card(0, 7));
		game.getPlayersHand().add(new Card(3, 10));
		game.getDealersHand().add(new Card(3, 8));
		game.getDealersHand().add(new Card(2, 8));
		game.getDealersHand().add(new Card(1, 9));
		game.calculatePlayersTotalHandValue();
		game.calculateDealersTotalHandValue();
		game.checkPlayerBusted();
		game.checkDealerBusted();
		game.checkPlayerBlackjack();
		game.checkDealerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		game.handWinner();
		assertTrue(game.isDealerBusted());
	}
	
	@Test
	public void testResetHands() {
		game.dealStartCards();
		assertFalse(game.getPlayersHand().isEmpty());
		assertFalse(game.getPlayersHand().isEmpty());
		
		game.resetHands();
		
		assertTrue(game.getPlayersHand().isEmpty());
		assertTrue(game.getDealersHand().isEmpty());
	}
}
