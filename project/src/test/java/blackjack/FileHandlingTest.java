package blackjack;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FileHandlingTest {

	BlackjackGame game;
	int exceptedBank;
	private IFileHandling fileHandling = new FileHandling();
	
	@BeforeEach
	public void setup() {
		game =  new BlackjackGame();
		exceptedBank = 1000;
	}
	
	
	@Test
	public void testLoadBank() {
		try {
			game.setBank(fileHandling.loadBank("test-file"));
		} catch (FileNotFoundException e) {
			fail("File not found");
			return;
		}
		assertEquals(exceptedBank, game.getBank());
	}
	
	@Test
	public void testLoadFileThatDoesNotExist() {
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			game.setBank(fileHandling.loadBank("thisFileDoesNotExist"));
		});
	}
	
	@Test
	public void testSaveBank() {
		game.setBank(exceptedBank);
		try {
			fileHandling.saveBank("test-file-2", game);
		} catch (FileNotFoundException e) {
			fail("Failed to save file");
		}
		
		String testFile = null;
		String newFile = null;
		
		try {
			testFile = Files.readString(Path.of(FileHandling.getFilePath("test-file")));
		} catch (IOException e) {
			fail("Failed to load testfile");
		}
		try {
		newFile = Files.readString(Path.of(FileHandling.getFilePath("test-file-2")));
		} catch (IOException e) {
			fail("Failed to load saved file");
		}
		
		assertNotNull(testFile);
		assertNotNull(newFile);
		assertEquals(testFile.strip(), newFile.strip());
	}
	
	@AfterAll
	static void teardown() {
		File fileToDelete = new File(FileHandling.getFilePath("test-file-2"));
		fileToDelete.delete();
	}
}
