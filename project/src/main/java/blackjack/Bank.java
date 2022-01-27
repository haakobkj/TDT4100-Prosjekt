package blackjack;

public class Bank {

	private int bet;
	private int bank;
	
	public void playerWin() {
		bank += bet;
		bet = 0;
	}
	
	public void playerLoss() {
		bank -= bet;
		bet = 0;
	}
	
	public void playerBlackJack() {
		bank += bet *1.5;
		bet = 0;
	}
	
	public void playerTie() {
		bet = 0;
	}
	
	public int getBet() {
		return this.bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getBank() {
		return this.bank;
	}

	public void setBank(int bank) {
		if (bank < 0)
			throw new IllegalArgumentException("Bank can't be negative");
		this.bank = bank;
	}

	public void bet10() {
		if (bank < 10)
			throw new IllegalArgumentException("You cant bet more money than you have!");
		bet = 10;
	}
	
	public void bet100() {
		if (bank < 100)
			throw new IllegalArgumentException("You cant bet more money than you have!");
		bet = 100;
	}
	
	public void bet1000() {
		if (bank < 1000)
			throw new IllegalArgumentException("You cant bet more money than you have!");
		bet = 1000;
	}
	
	public void betAllIn() {
		if (bank == 0)
			throw new IllegalArgumentException("You are out of money!");
		bet = bank;
	}
}
