package blackjack;

import java.util.ArrayList;

public class Player {
	
	private int totalHandValue;
	private int numberOfAces;
	private ArrayList<Card> playersHand = new ArrayList<Card>();
	private boolean playerWon = false;
	private boolean playerLost = false;
	private boolean playerDraw = false;
	private boolean playerBlackjack = false;
	private boolean playerBusted = false;
	private boolean roundStarted = false;
	private boolean betable = true;
	
	
	public boolean isPlayerWon() {
		return playerWon;
	}

	public boolean isPlayerBlackjack() {
		return playerBlackjack;
	}

	public boolean isPlayerLost() {
		return playerLost;
	}

	public boolean isPlayerDraw() {
		return playerDraw;
	}

	public void clearHand() {
		playersHand.clear();
	}
	
	public boolean isPlayerWonRound() {
		return playerWon;
	}

	public void setPlayerBlackjack() {
		this.playerBlackjack = true;
	}
	
	public int getTotalHandValue() {
		return totalHandValue;
	}

	public void calculateTotalHandValue() {
		this.totalHandValue = 0;
		initializeNumberOfAces();
		
		if (playersHand.isEmpty())
			return;
		
		for (int i = 0 ; i < playersHand.size(); i++) {
			totalHandValue += playersHand.get(i).getValue();
			}
		if (numberOfAces == 2 && playersHand.size() == 2)
			totalHandValue = 12; //Dersom man får AA
		else if (numberOfAces == 1 && totalHandValue > 21) {
			totalHandValue -= 10;
			numberOfAces--;
		}
		else if (numberOfAces >= 2 && totalHandValue > 21) {
			while ( totalHandValue > 21 && numberOfAces > 0) {
			totalHandValue -= 10;
			numberOfAces --;
			}
		}
	}

	public void initializeNumberOfAces() {
		int Aces = 0;
		for (Card card : playersHand) {
			 if (card.getRank() == 1)
				 Aces ++;
		}
		this.numberOfAces = Aces;
	}

	public void PlayerWin() {
		playerWon = true;
	}
	
	public void PlayerLoss() {
		playerLost = true;
	}
	
	public void PlayerBlackJack() {
		playerBlackjack = true;
	}
	
	public void PlayerTie() {
		playerDraw = true;
	}
	
	public boolean totalHandValueOver21() {
		return (totalHandValue > 21);
	}
	
	public boolean checkIfBusted() {
		return totalHandValueOver21() && !(numberOfAces > 0);
	}
	
	public boolean checkPlayerBlackjack() {
		return playersHand.size() == 2 && getTotalHandValue() == 21;
	}
	
	public void addCard(Card card) {
		playersHand.add(card);
	}
	
	public ArrayList<Card> getPlayersHand() {
		return playersHand;
	}
	
	public int getNumberOfAces() {
		return numberOfAces;
	}

	public void setPlayerBlackjack(boolean playerBlackjack) {
		this.playerBlackjack = playerBlackjack;
	}

	public boolean isRoundStarted() {
		return roundStarted;
	}

	public void setRoundStarted(boolean roundStarted) {
		this.roundStarted = roundStarted;
	}

	public boolean isBetable() {
		return betable;
	}

	public void setBetable(boolean betable) {
		this.betable = betable;
	}

	public void setPlayerBusted() {
		playerBusted = true;
	}
	
	public boolean isPlayerBusted() {
		return playerBusted;
	}
}
