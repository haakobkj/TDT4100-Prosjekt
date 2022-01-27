package blackjack;

import java.util.ArrayList;

public class Dealer {

	private ArrayList<Card> dealersHand = new ArrayList<Card>();
	private int totalHandValue;
	private int numberOfAces;
	private boolean dealerBlackjack = false;
	private boolean dealerBusted = false;
	
	public void clearHand() {
		dealersHand.clear();
	}
	
	public boolean isDealerHitting() {
		return (totalHandValue < 17);
	}
	
	public boolean isBlackjack() {
		return (totalHandValue == 21 && dealersHand.size() == 2);
		
	}
	
	public void addCard(CardDeck deck) {
		dealersHand.add(deck.handOutCard());
	}
	
	public int valueOfFirstCard() {
		return dealersHand.get(0).getValue();
	}
	
	public void calculateTotalHandValue() {
		this.totalHandValue = 0;
		initializeNumberOfAces();
		
		if (dealersHand.isEmpty())
			return;
		
		for (int i = 0 ; i < dealersHand.size(); i++) {
			totalHandValue += dealersHand.get(i).getValue();
			}
		if (numberOfAces == 2 && dealersHand.size() == 2)
			totalHandValue = 12; //Dersom man får AA
		else if (numberOfAces == 1 && totalHandValue > 21) {
			totalHandValue -= 10;
			numberOfAces--;
		}
		else if (numberOfAces >= 2 && totalHandValue > 21) {
			while ( totalHandValue > 21 && numberOfAces > 0) {
			totalHandValue -= 10;
			numberOfAces -= 1;
			}
		}
	}	
	
	public void initializeNumberOfAces() {
		int Aces = 0;
		for (Card card : dealersHand) {
			 if (card.getRank() == 1)
				 Aces += 1;
		}
		this.numberOfAces = Aces;
	}
	
	public boolean checkDealerBlackjack() {
		return dealersHand.size() == 2 && getTotalHandValue() == 21;
	}
	
	public boolean totalHandValueOver21() {
		return (totalHandValue > 21);
	}
	
	public boolean checkIfBusted() {
		return (totalHandValueOver21() && !(numberOfAces > 0));
	}
	

	public void setDealerBlackjack() {
		dealerBlackjack = checkDealerBlackjack();
	}
	
	public boolean isDealerBlackjack() {
		return dealerBlackjack;
	}
	
	public ArrayList<Card> getDealersHand() {
		return dealersHand;
	}

	public void addCard(Card card) {
		dealersHand.add(card);
	}

	
	public int getTotalHandValue() {
		return totalHandValue;
	}
	
	public int getNumberOfAces() {
		return numberOfAces;
	}

	public boolean isDealerBusted() {
		return dealerBusted ;
	}

	public void setDealerBusted() {
		dealerBusted = true;
	}
}
