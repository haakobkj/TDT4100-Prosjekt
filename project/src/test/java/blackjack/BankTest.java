package blackjack;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BankTest {

	private Bank bank;
	
	@BeforeEach
	public void setup() {
		bank = new Bank();
	}
	
	@Test
	public void testBet10() {
		bank.setBank(1000);
		bank.bet10();
		assertEquals(10, bank.getBet());
	}
	
	@Test
	public void testBet100() {
		bank.setBank(1000);
		bank.bet100();
		assertEquals(100, bank.getBet());
	}
	
	@Test
	public void testBet1000() {
		bank.setBank(1000);
		bank.bet1000();
		assertEquals(1000, bank.getBet());
	}
	
	@Test
	public void testBetAllIn() {
		bank.setBank(1000);
		bank.betAllIn();
		assertEquals(1000, bank.getBet());
	}
	
	@Test
	public void testPlayerWin() {
		bank.setBank(1000);
		bank.bet100();
		bank.playerWin();
		assertEquals(1100, bank.getBank());
	}
	
	@Test
	public void testPlayerLoss() {
		bank.setBank(1000);
		bank.bet100();
		bank.playerLoss();
		assertEquals(900, bank.getBank());
	}
	@Test
	public void testPlayerTie() {
		bank.setBank(1000);
		bank.bet100();
		bank.playerTie();
		assertEquals(1000, bank.getBank());
	}
	
	@Test
	public void testPlayerBlackjack() {
		bank.setBank(1000);
		bank.bet100();
		bank.playerBlackJack();;
		assertEquals(1150, bank.getBank());
	}
	
	@Test
	public void betMoreThanYouHave() {
		bank.setBank(100);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			bank.bet1000();;
		});
	}
}
