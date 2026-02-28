package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {
	
	File csvFile;
	File jsonFile;
	File otherFile;
	File blankextFile;
	
	String escaped = "test \\\" quotes \\\" and \\n \\r newlines";
	String notEscaped = "test \" quotes \" and \n \r newlines";
	String normal = "test no quotes and no newlines";

	@BeforeEach
	void setUp() throws Exception {
		csvFile = File.createTempFile("temp", ".csv");
		jsonFile = File.createTempFile("temp", ".json");
		otherFile = File.createTempFile("temp", ".txt");
		blankextFile = File.createTempFile("temp", "");
	}

	@Test
	void testGetExtension() {
		assertEquals("csv", Utils.getExtension(csvFile));
		assertEquals("json", Utils.getExtension(jsonFile));
		assertEquals("txt", Utils.getExtension(otherFile));
		assertEquals("", Utils.getExtension(blankextFile));
	}

	@Test
	void testExtensionIsBlank() {
		assertTrue(Utils.extensionIsBlank(blankextFile));
		
		assertFalse(Utils.extensionIsBlank(csvFile));
		assertFalse(Utils.extensionIsBlank(jsonFile));
		assertFalse(Utils.extensionIsBlank(otherFile));
	}

	@Test
	void testIsCSV() {
		assertTrue(Utils.isCSV(csvFile));
		
		assertFalse(Utils.isCSV(jsonFile));
		assertFalse(Utils.isCSV(otherFile));
		assertFalse(Utils.isCSV(blankextFile));
	}

	@Test
	void testIsJSON() {
		assertTrue(Utils.isJSON(jsonFile));
		
		assertFalse(Utils.isJSON(csvFile));
		assertFalse(Utils.isJSON(otherFile));
		assertFalse(Utils.isJSON(blankextFile));
	}

	@Test
	void testEscapeSpecialCharacters() {
		assertEquals(escaped, Utils.escapeSpecialCharacters(notEscaped));
		
		assertEquals(normal, Utils.escapeSpecialCharacters(normal));
	}

	@Test
	void testReverseEscapeSpecialCharacters() {
		assertEquals(notEscaped, Utils.reverseEscapeSpecialCharacters(escaped));
		
		assertEquals(normal, Utils.reverseEscapeSpecialCharacters(normal));
	}

}
