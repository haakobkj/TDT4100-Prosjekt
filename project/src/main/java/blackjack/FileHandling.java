package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * FileHandling-klassen implementerer ikke den mest avanserte koden, men snakket med læringsassistent som
 * var enig i at alle kravene for filhåndtering var oppfylt. Hvis man skulle lagret mer av tilstanden måtte
 * prosjektet blitt utvidet med ny funksjonalitet, noe som ikke var ønskelig.
 */

public class FileHandling implements IFileHandling {

	public final static String FOLDER = "src/main/java/blackjack/saves/";
	
	@Override
	public void saveBank(String filename, BlackjackGame game) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(getFilePath(filename))) {
			writer.println(game.getBank());
		}
	}

	@Override
	public int loadBank(String filename) throws FileNotFoundException{
		try (Scanner scanner = new Scanner(new File(getFilePath(filename)))) {
			int bank = scanner.nextInt();
			return bank;
		}
	}

	public static String getFilePath(String filename) {
		return FOLDER + filename + ".txt";
	}
}
