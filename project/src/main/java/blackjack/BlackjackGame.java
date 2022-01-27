package blackjack;

import java.util.ArrayList;

public class BlackjackGame {
	
	private CardDeck deck;
	private Player player;
	private Dealer dealer;
	private Bank bank;

//	Initialiserer spillet
 	public BlackjackGame() {
		this.dealer = new Dealer();
		this.player = new Player();
		this.deck = new CardDeck();
		this.bank = new Bank();
 	}
 	
 	public void setBank(int deposit) {
		bank.setBank(deposit);
	}
	
	public void shuffleDeck() {
		deck.shuffleDeck();
	}
		
	public void dealStartCards() { //Deler ut startkortene og oppdaterer verdiene
		for (int i = 0; i < 2; i++) {
			player.addCard(deck.handOutCard());
			dealer.addCard(deck.handOutCard());
			player.calculateTotalHandValue();
			dealer.calculateTotalHandValue();
		}
	}

	public void checkPlayerBusted() {
		player.calculateTotalHandValue();
		if (player.checkIfBusted())
			player.setPlayerBusted();
	}
	
	public void checkDealerBusted() {
		dealer.calculateTotalHandValue();
		if (dealer.checkIfBusted())
			dealer.setDealerBusted();
	}
	
	public void cantHitThatButton() {
		throw new IllegalArgumentException("You can't hit that button at the current state!");
	}
	
	public void checkPlayerBlackjack() {
		if (player.checkPlayerBlackjack())
			player.setPlayerBlackjack();
	}
	
	public void checkDealerBlackjack() {
		if (dealer.checkDealerBlackjack())
			dealer.setDealerBlackjack();
	}
	
	public void checkPlayerAndDealerBlackjack() {
		if (player.isPlayerBlackjack() && !dealer.isDealerBlackjack()) {
			player.PlayerBlackJack();
			bank.playerBlackJack();
		}
		else if (player.isPlayerBlackjack() && dealer.isDealerBlackjack()) {
			player.PlayerTie();
			bank.playerTie();
		}
	}
	
	public void hit() {
		if (getPlayersTotalHandValue() == 0)
			throw new IllegalArgumentException("Can't hit before you have betted");
		
		else if (player.isPlayerBusted())
			throw new IllegalArgumentException("Can't hit when you are busted!");
		
		else if (getPlayersTotalHandValue() <= 21) {
		Card hitCard = deck.handOutCard();
		player.addCard(hitCard);
		player.calculateTotalHandValue();
		checkPlayerBusted();
		}	
	}

	public void dealersTurnToPlay() {
		if (!player.isPlayerBlackjack() && !player.isPlayerBusted()) {
			dealer.calculateTotalHandValue();
			
			while (dealer.getTotalHandValue() < 17 ) {
				Card hitCard = deck.handOutCard();
				dealer.addCard(hitCard);
				dealer.calculateTotalHandValue();
			}
		}
		checkDealerBusted();
	}
		
	//Sjekker alle muligheter for hvem som kan vinne h�nden.
	public void handWinner() {
		if (dealer.isDealerBlackjack() && player.isPlayerBlackjack()) {
			player.PlayerTie();
			bank.playerTie();
		}
		else if (dealer.isDealerBlackjack()) {
			player.PlayerLoss();
			bank.playerLoss();
		}
		else if (player.isPlayerBusted()) {
			player.PlayerLoss();
			bank.playerLoss();
		}
		else if (dealer.isDealerBusted()) {
			player.PlayerWin();
			bank.playerWin();
		}
		else if (player.getTotalHandValue() == dealer.getTotalHandValue()) {
			player.PlayerTie();
			bank.playerTie();
		}
		else if (player.getTotalHandValue() > dealer.getTotalHandValue()) {
			player.PlayerWin();
			bank.playerWin();
		}
		else if (player.getTotalHandValue() < dealer.getTotalHandValue()) {
			player.PlayerLoss();
			bank.playerLoss();
		}
		else
			throw new IllegalStateException();
//Bruker denne for å hindre at man kan trykke på knappene når er i en tilstand der det ikke er lov.
		player.setRoundStarted(false); 
	}

	public boolean isRoundStarted() {
		return player.isRoundStarted();
	}
	
	public boolean isPlayerWon() {
		return player.isPlayerWon();
	}
	
	public boolean isPlayerLost() {
		return player.isPlayerLost();
	}
	
	public boolean isPlayerDraw() {
		return player.isPlayerDraw();
	}
	public boolean isPlayerBlackjack() {
		return player.isPlayerBlackjack();
	}
	
	public boolean isDealerBlackjack() {
		return dealer.isDealerBlackjack();
	}
	
	//Tar kortene tilbake til stokken.
	public void resetHands() {
		player.clearHand();
		dealer.clearHand();
		this.deck = new CardDeck();
		this.player = new Player();
		this.dealer = new Dealer();
	}
	
	public void bet10() {
		bank.bet10();
	}
	
	public void bet100() {
		bank.bet100();
	}
	
	public void bet1000() {
		bank.bet1000();
	}
	
	public void betAllIn() {
		bank.betAllIn();
	}
	
	public int getBank() {
		return bank.getBank();
	}
	
	public String getPlayersCard(int index) {
		return player.getPlayersHand().get(index).toString();
	}
	
	public String getDealersCard(int index) {
		return dealer.getDealersHand().get(index).toString();
	}
	
	public ArrayList<Card> getPlayersHand() {
		return player.getPlayersHand();
	}
	
	public ArrayList<Card> getDealersHand() {
		return dealer.getDealersHand();
	}
	
	public ArrayList<Card> getDeck() {
		return deck.getDeck();
	}
	
	public int getPlayersTotalHandValue() {
		return player.getTotalHandValue();
	}
	
	public int getDealersTotalHandValue() {
		return dealer.getTotalHandValue();
	}
	
	public int getDealersFirstCardValue() {
		return dealer.valueOfFirstCard();
	}
	
	public void getTotalBet() {
		bank.getBet();
	}

	public void roundStarted() {
		player.setRoundStarted(true);
	}
	
	public void roundEnded() {
		player.setRoundStarted(false);
	}
	
	public boolean isDealerBusted() {
		return dealer.isDealerBusted();
	}
	
	public boolean isPlayerBusted() {
		return player.isPlayerBusted();
	}
	
	public boolean isBetable() {
		return player.isBetable();
	}
	
	public void setCannotBet() {
		player.setBetable(false);
	}
	
	public void setCanBet() {
		player.setBetable(false);
	}
	
	public void setRoundEnded() {
		player.setRoundStarted(false);
	}

	public void calculatePlayersTotalHandValue() {
		player.calculateTotalHandValue();
	}
	
	public void calculateDealersTotalHandValue() {
		dealer.calculateTotalHandValue();
	}
}