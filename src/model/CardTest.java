/**
 * 
 */
package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class CardTest {
	
	Card testCard;
	
	Exception exception;

	/**
	 * Test method for {@link model.Card#Card(java.lang.String, java.lang.String, int)}.
	 * @throws Exception 
	 */
	@Test
	void testCardStringStringInt() throws Exception {
		
		/// normal case
		testCard = new Card("Card Name", "Card Description", 1);
		assertEquals("Card Name", testCard.getName());
		assertEquals("Card Description", testCard.getDescription());
		assertEquals(1, testCard.getCopies());
		
		
		/// remove leading and trailing spaces 
		// name
		testCard = new Card("  Card Name", "Card Description", 1);
		assertEquals("Card Name", testCard.getName());
		
		testCard = new Card("Card Name  ", "Card Description", 1);
		assertEquals("Card Name", testCard.getName());
		
		testCard = new Card("  Card Name  ", "Card Description", 1);
		assertEquals("Card Name", testCard.getName());
		
		// strange unicode whitespace characters
		/*
		 * testCard = new Card(" ​⠀ Card Name ⠀", "Card Description", 1);
		 * assertEquals("Card Name", testCard.getName());
		 */
		
		
		// description
		testCard = new Card("Card Name", " Description", 1);
		assertEquals("Description", testCard.getDescription());
		
		testCard = new Card("Card Name", "Description ", 1);
		assertEquals("Description", testCard.getDescription());
		
		testCard = new Card("Card Name", " Description ", 1);
		assertEquals("Description", testCard.getDescription());
		
		//strange unicode whitespace characters
		/*
		 * testCard = new Card("Card Name", "    ​Description​ ⠀", 1);
		 * assertEquals("Description", testCard.getDescription());
		 */
		
		
		/// blank description
		testCard = new Card("Card Name", "", 1);
		assertEquals("", testCard.getDescription());
		
		testCard = new Card("Card Name", "  ", 1);
		assertEquals("", testCard.getDescription());
		
		
		/// different number of copies
		testCard = new Card("Card Name", "Card Description", 4);
		assertEquals(4, testCard.getCopies());
		
		testCard = new Card("Card Name", "Card Description", 7);
		assertEquals(7, testCard.getCopies());
		
		
		/// EXCEPTIONS
		
		/// empty name
		exception = assertThrows(Exception.class, () -> {
			new Card("","description", 1);
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		/// whitespace only name
		exception = assertThrows(Exception.class, () -> {
			new Card("  ","description", 1);
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		/// strange unicode whitespace characters
		/*
		 * exception = assertThrows(Exception.class, () -> { new
		 * Card("    ","description", 1); });
		 * assertEquals("Name cannot be empty or only whitespace",
		 * exception.getMessage());
		 */
		
		/// zero copies
		exception = assertThrows(Exception.class, () -> {
			new Card("Name", "Description", 0);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		
	}

	/**
	 * Test method for {@link model.Card#Card(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testCardStringString() throws Exception {
		testCard = new Card("Name","Description");
		assertEquals("Name", testCard.getName());
		assertEquals("Description", testCard.getDescription());
		assertEquals(1,testCard.getCopies());
		
		testCard = new Card("Name","");
		assertEquals("Name", testCard.getName());
		assertEquals("", testCard.getDescription());
		
		// leading and trailing spaces
		testCard = new Card("  Name","  Description");
		assertEquals("Name", testCard.getName());
		assertEquals("Description", testCard.getDescription());
		
		testCard = new Card("Name  ","Description  ");
		assertEquals("Name", testCard.getName());
		assertEquals("Description", testCard.getDescription());
		
		testCard = new Card("  Name  ","  Description  ");
		assertEquals("Name", testCard.getName());
		assertEquals("Description", testCard.getDescription());
		
		/*
		 * //strange unicode whitespace characters testCard = new Card("​ ⠀Name    ​",
		 * "    ​Description​ ⠀"); assertEquals("Description",
		 * testCard.getDescription()); assertEquals("Name", testCard.getName());
		 */
		
		
		/// blank description
		testCard = new Card("Card Name", "");
		assertEquals("", testCard.getDescription());
		
		testCard = new Card("Card Name", "  ");
		assertEquals("", testCard.getDescription());
		
		/// EXCEPTIONS
		/// empty name
		exception = assertThrows(Exception.class, () -> {
			new Card("","description");
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		/// whitespace only name
		exception = assertThrows(Exception.class, () -> {
			new Card("  ","description");
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		/// strange unicode whitespace characters
		/*
		 * exception = assertThrows(Exception.class, () -> { new
		 * Card("    ","description"); });
		 * assertEquals("Name cannot be empty or only whitespace",
		 * exception.getMessage());
		 */
		
		
	}

	/**
	 * Test method for {@link model.Card#Card(java.lang.String, int)}.
	 * @throws Exception 
	 */
	@Test
	void testCardStringInt() throws Exception {
		testCard = new Card("name", 1);
		assertEquals("name",testCard.getName());
		assertEquals("",testCard.getDescription());
		assertEquals(1, testCard.getCopies());
		
		// remove trailing and leading whitespace
		testCard = new Card("  Card Name", 1);
		assertEquals("Card Name", testCard.getName());
		
		testCard = new Card("Card Name  ", 1);
		assertEquals("Card Name", testCard.getName());
		
		testCard = new Card("  Card Name  ", 1);
		assertEquals("Card Name", testCard.getName());
		
		/*
		 * // strange unicode whitespace characters testCard = new
		 * Card(" ​⠀ Card Name ⠀", 1); assertEquals("Card Name", testCard.getName());
		 */
		
		/// EXCEPTIONS
		// name
		exception = assertThrows(Exception.class, () -> {
			testCard = new Card("",1);
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		exception = assertThrows(Exception.class, () -> {
			testCard = new Card("  ", 1);
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		// strange unicode whitespace characters
		/*
		 * exception = assertThrows(Exception.class, () -> { testCard = new
		 * Card("    ",1); }); assertEquals("Name cannot be empty or only whitespace",
		 * exception.getMessage());
		 */
		
		
		// copies
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(0);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(-1);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		
	}

	/**
	 * Test method for {@link model.Card#Card(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testCardString() throws Exception {
		testCard = new Card("name", 1);
		assertEquals("name",testCard.getName());
		assertEquals("",testCard.getDescription());
		assertEquals(1,testCard.getCopies());
		
		/// EXCEPTIONS
		exception = assertThrows(Exception.class, () -> {
			testCard = new Card("");
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		exception = assertThrows(Exception.class, () -> {
			testCard = new Card("  ");
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		// strange unicode whitespace characters
		/*
		 * exception = assertThrows(Exception.class, () -> { testCard = new
		 * Card("    "); }); assertEquals("Name cannot be empty or only whitespace",
		 * exception.getMessage());
		 */
	}

	/**
	 * Test method for {@link model.Card#setName(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testSetName() throws Exception {
		testCard = new Card("name");
		
		testCard.setName("name");
		assertEquals("name", testCard.getName());
		
		// remove leading and trailing whitespace
		testCard.setName("  name");
		assertEquals("name", testCard.getName());
		
		testCard.setName("name  ");
		assertEquals("name", testCard.getName());
		
		testCard.setName("    name  ");
		assertEquals("name", testCard.getName());
		
		// strange unicode whitespace characters
		/*
		 * testCard.setName(" ​⠀ name ⠀"); assertEquals("name", testCard.getName());
		 */
		
		/// EXCEPTIONS
		exception = assertThrows(Exception.class, () -> {
			testCard.setName("");
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		exception = assertThrows(Exception.class, () -> {
			testCard.setName("  ");
		});
		assertEquals("Name cannot be empty or only whitespace", exception.getMessage());
		
		// strange unicode whitespace characters
		/*
		 * exception = assertThrows(Exception.class, () -> {
		 * testCard.setName("    "); });
		 * assertEquals("Name cannot be empty or only whitespace",
		 * exception.getMessage());
		 */
	}

	/**
	 * Test method for {@link model.Card#setDescription(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testSetDescription() throws Exception {
		testCard = new Card("name");
		
		testCard.setDescription("Description");
		assertEquals("Description", testCard.getDescription());
		
		// leading and trailing whitespace
		testCard.setDescription("  Description");
		assertEquals("Description", testCard.getDescription());
		
		testCard.setDescription("Description  ");
		assertEquals("Description", testCard.getDescription());
		
		testCard.setDescription("  Description  ");
		assertEquals("Description", testCard.getDescription());
		
		// strange unicode whitespace characters
		/*
		 * testCard.setDescription("    ​Description​ ⠀"); assertEquals("Description",
		 * testCard.getDescription());
		 */
		
		
		// description can be empty
		
		testCard.setDescription("");
		assertEquals("", testCard.getDescription());
		
		testCard.setDescription("  ");
		assertEquals("", testCard.getDescription());
		
		// strange unicode whitespace characters
		/*
		 * testCard.setDescription("    "); assertEquals("", testCard.getDescription());
		 */
		
	}

	/**
	 * Test method for {@link model.Card#setCopies(int)}.
	 * @throws Exception 
	 */
	@Test
	void testSetCopies() throws Exception {
		testCard = new Card("name");
		
		testCard.setCopies(1);
		assertEquals(1, testCard.getCopies());
		
		testCard.setCopies(5);
		assertEquals(5, testCard.getCopies());
		
		
		// edge case
		
		testCard.setCopies(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, testCard.getCopies());
		
		testCard.setCopies(Integer.MIN_VALUE - 1);
		assertEquals(Integer.MAX_VALUE, testCard.getCopies());
		
		
		/// EXCEPTIONS
		
		// throws exception if copies = 0
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(0);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		
		// throws exception if copies is negative
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(-1);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(-10);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(Integer.MIN_VALUE);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
		
		// integer overflow
		exception = assertThrows(Exception.class, () -> {
			testCard.setCopies(Integer.MAX_VALUE + 1);
		});
		assertEquals("Copies must be an integer over 0", exception.getMessage());
		
	}

}
