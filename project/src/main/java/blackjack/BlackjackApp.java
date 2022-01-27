package blackjack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlackjackApp extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Blackjack");
		primaryStage.setScene(new Scene(FXMLLoader.load(BlackjackController.class.getResource("Blackjack.fxml"))));
		primaryStage.show();
	}
		
	public static void main(String[] args) {
		BlackjackApp.launch(args);
	}
}
 