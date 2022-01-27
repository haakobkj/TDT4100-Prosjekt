package blackjack;

import java.io.FileNotFoundException;

public interface IFileHandling {

	void saveBank(String filename, BlackjackGame game) throws FileNotFoundException;
	
	int loadBank(String filename) throws FileNotFoundException;

}
