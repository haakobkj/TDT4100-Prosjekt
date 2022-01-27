package blackjack;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

public class BlackjackController {
	
//	Controller-klassen har ikke logikk annet enn å kalle på andre klasser og knytte GUI og logikk sammen.
//	Det er derfor ikke nødvendig å implementere testkode for denne klassen.
	
	@FXML private TextField username;
	@FXML private TextArea dealersHand, playersHand, playersHandValue, dealersHandValue, playersBalance;
	@FXML Pane table;
	@FXML Text bustedText, wonText, lostText, drawText, blackjackText,
	fileNotFoundMessage, cantHitNow, cantStandNow, cantPlayAgainNow, cantBetNow, gameOverText;
    @FXML TextField filename;
	private IFileHandling file = new FileHandling();
	private BlackjackGame game;

	@FXML
	public void initialize() {
		game = new BlackjackGame();
		game.setBank(1000);
		playersBalance.setText(Integer.toString(game.getBank()));
		game.roundStarted(); //Sørger for at man ikke kan trykke p� play again f�r man har begynt
	}
	
	@FXML
	private void resetCantHitText() {
		cantHitNow.setVisible(false);
		cantStandNow.setVisible(false);
		cantPlayAgainNow.setVisible(false);
	}
	
	@FXML
	public void startGame() {
		game.roundStarted();
		game.shuffleDeck();
		game.dealStartCards();
		printPlayersCards();
		dealersHand.setText("Dealers first card is: \n");
		dealersHand.appendText(game.getDealersCard(0) + "\n"); //Viser bare dealers f�rste kort:
		dealersHandValue.setText(Integer.toString(game.getDealersFirstCardValue()));
		checkPlayerVSDealerBlackjack();
		if (game.isDealerBlackjack() && game.isPlayerBlackjack()) {
			dealersHand.appendText(game.getDealersCard(1) + "\n");
			dealersHandValue.setText(Integer.toString(game.getDealersTotalHandValue()));
		}
		playersBalance.setText(Integer.toString(game.getBank()));
	}
	
	@FXML
	public void printDealersCards() {
		dealersHand.clear();
		dealersHand.setText("Dealers cards are:" + "\n");
		for (int i = 0; i < game.getDealersHand().size() ; i++) 
	      { 		      
			dealersHand.appendText(game.getDealersCard(i) + "\n"); 		
	      }
		dealersHandValue.setText(Integer.toString(game.getDealersTotalHandValue()));
	}
	
	@FXML
	public void printPlayersCards() {
		playersHand.clear();
		playersHand.setText("Your cards are:" + "\n");
		for (int i = 0; i < game.getPlayersHand().size() ; i++) 
	      { 		      
			playersHand.appendText(game.getPlayersCard(i) + "\n"); 		
	      }
		playersHandValue.setText(Integer.toString(game.getPlayersTotalHandValue()));
	}
	
	@FXML
	public void checkPlayerVSDealerBlackjack() {
		game.checkDealerBlackjack();
		game.checkPlayerBlackjack();
		game.checkPlayerAndDealerBlackjack();
		if (game.isPlayerBlackjack() && !game.isDealerBlackjack()) {
			game.roundEnded();
			blackjackText.setVisible(true);
		}
		else if (game.isPlayerBlackjack() && game.isDealerBlackjack()) {
			game.roundEnded();
			drawText.setVisible(true);
		}
	}
	
	@FXML
	public void gameisWonOrLost() {
		game.handWinner();
		
		if (game.isPlayerBusted()) {
			playersBalance.setText(Integer.toString(game.getBank()));
			bustedText.setVisible(true);
		}
		else if (game.isPlayerWon())
			wonText.setVisible(true);
		else if (game.isPlayerLost())
			lostText.setVisible(true);
		else if (game.isPlayerDraw())
			drawText.setVisible(true);
		
		if (game.getBank() == 0) {
			bustedText.setVisible(false);
			lostText.setVisible(false);
			gameOverText.setVisible(true);
		}
	}

	@FXML
	public void onHit() {
		try {
			game.checkPlayerBusted();
			if (game.isPlayerBusted() || !game.isRoundStarted() || game.isBetable())
				game.cantHitThatButton();
			game.hit();
			printPlayersCards();
			if (game.isPlayerBusted())
				gameisWonOrLost();
		} catch (IllegalArgumentException e) {
			cantHitNow.setVisible(true);
		}
	}
	
	@FXML
	public void onStand() {
		try {
			game.checkPlayerBusted();
			if (game.isPlayerBusted() || !game.isRoundStarted() || game.isBetable())
				game.cantHitThatButton();
			game.dealersTurnToPlay();
			printDealersCards();
			gameisWonOrLost();
			playersBalance.setText(Integer.toString(game.getBank()));
		} catch (IllegalArgumentException e) {
			cantStandNow.setVisible(true);
		}
	}
	
	@FXML 
	public void onPlayAgain() {
		try {
			if (game.isRoundStarted())
				game.cantHitThatButton();
			
			game.setCanBet();
			game.resetHands();
			playersHand.clear();
			playersHandValue.clear();
			dealersHand.clear();
			dealersHandValue.clear();
			wonText.setVisible(false); //Sørger for at resultatet fra forrige runde skjules
			lostText.setVisible(false);
			drawText.setVisible(false);
			blackjackText.setVisible(false);
			bustedText.setVisible(false);
			resetCantHitText();
			cantBetNow.setVisible(false);
			game.roundStarted();
		} catch (IllegalArgumentException e) {
			cantPlayAgainNow.setVisible(true);
		}
	}

	@FXML 
	public void on10() {
		try {
			resetCantHitText();
			cantBetNow.setVisible(false);
			if (!game.isBetable())
				game.cantHitThatButton();
			game.bet10();
			game.setCannotBet();
			startGame();
		} catch (IllegalArgumentException e) {
			cantBetNow.setVisible(true);
		}
	}
	
	@FXML 
	public void on100() {
		try {
			resetCantHitText();
			cantBetNow.setVisible(false);
			if (!game.isBetable())
				game.cantHitThatButton();
			game.bet100();
			game.setCannotBet();
			startGame();
		} catch (IllegalArgumentException e) {
			cantBetNow.setVisible(true);
		}
	}

	@FXML 
	public void on1000() {
		try {
			resetCantHitText();
			cantBetNow.setVisible(false);
			if (!game.isBetable())
				game.cantHitThatButton();
			game.bet1000();
			game.setCannotBet();
			startGame();
		} catch (IllegalArgumentException e) {
			cantBetNow.setVisible(true);
		}
	}

	@FXML 
	public void onAllIn() {
		try {
			resetCantHitText();
			cantBetNow.setVisible(false);
			if (!game.isBetable())
				game.cantHitThatButton();
			game.betAllIn();
			game.setCannotBet();
			startGame();
		} catch (IllegalArgumentException e) {
			cantBetNow.setVisible(true);
		}
	}

	@FXML 
	public void onLoad() throws FileNotFoundException {
			try {
			int bank = file.loadBank(getFilename());
			gameOverText.setVisible(false);
			game.setBank(bank);
			playersBalance.setText(Integer.toString(game.getBank()));
			fileNotFoundMessage.setVisible(false);
			game.setRoundEnded();
			onPlayAgain();
			} catch (FileNotFoundException e) {
				fileNotFoundMessage.setVisible(true);
			}
	}

//	Lagrer bank-feltet i fil, oppretter en ny fil vis den ikke allerede eksisterer.
	@FXML 
	public void onSave() throws FileNotFoundException {
    		try {
    			file.saveBank(getFilename(), game);
    			fileNotFoundMessage.setVisible(false);
    		} catch (FileNotFoundException e) {
    			fileNotFoundMessage.setVisible(true);
    		}
	}
	
	private String getFilename() throws FileNotFoundException {
    	String filename = this.filename.getText();
    	if (filename.isEmpty()) {
    		throw new FileNotFoundException();
    	}
    	return filename;
    }
}
