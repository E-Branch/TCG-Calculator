/**
 * TODO: add assertions to check functions throw exceptions correctly
 */

package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class DeckTableTest {
	
	private DeckTable dt;
	
	private int cols = 3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dt = new DeckTable();
		dt.addCard(new Card("name"));
		dt.addCard(new Card("name2","description"));
		dt.addCard(new Card("name3",5));
		dt.addCard(new Card("name4","description",5));
	}

	/**
	 * Test method for {@link model.DeckTable#DeckTable()}.
	 */
	@Test
	void testDeckTable() {
		dt = new DeckTable();
		
		// number of cards and total deck size should be 0
		assertEquals(0, dt.getRowCount());
		assertEquals(0, dt.getDeckSize());
	}

	/**
	 * Test method for {@link model.DeckTable#getColumnCount()}.
	 */
	@Test
	void testGetColumnCount() {
		assertEquals(cols,dt.getColumnCount());
	}

	/**
	 * Test method for {@link model.DeckTable#getColumnName(int)}.
	 */
	@Test
	void testGetColumnNameInt() {
		// 0 = "Name"
		assertEquals("Name", dt.getColumnName(0));
		// 1 = "Description
		assertEquals("Description", dt.getColumnName(1));
		// 2 = "Copies
		assertEquals("Copies", dt.getColumnName(2));
		// else = ""
		assertEquals("", dt.getColumnName(3));
	}

	/**
	 * Test method for {@link model.DeckTable#getRowCount()}.
	 */
	@Test
	void testGetRowCount() {
		assertEquals(4, dt.getRowCount());
	}

	/**
	 * Test method for {@link model.DeckTable#getValueAt(int, int)}.
	 */
	@Test
	void testGetValueAt() {
		assertEquals("name", dt.getValueAt(0, 0));
		assertEquals("", dt.getValueAt(0, 1));
		assertEquals(1, dt.getValueAt(0, 2));
		
		assertEquals("name2", dt.getValueAt(1, 0));
		assertEquals("description", dt.getValueAt(1, 1));
		assertEquals(1, dt.getValueAt(1, 2));
		
		assertEquals("name3", dt.getValueAt(2, 0));
		assertEquals("", dt.getValueAt(2, 1));
		assertEquals(5, dt.getValueAt(2, 2));
		
		assertEquals("name4", dt.getValueAt(3, 0));
		assertEquals("description", dt.getValueAt(3, 1));
		assertEquals(5, dt.getValueAt(3, 2));
		
	}

	/**
	 * Test method for {@link model.DeckTable#getDeckSize()}.
	 */
	@Test
	void testGetDeckSize() {
		assertEquals(12, dt.getDeckSize());
	}

	/**
	 * Test method for {@link model.DeckTable#addCard(model.Card)}.
	 * @throws Exception if the card fails to create, or theres something wrong with the indexing
	 */
	@Test
	void testAddCard() throws Exception {
		
		// just name
		int oldSize = dt.getRowCount();
		int oldDeckSize = dt.getDeckSize();
		
		dt.addCard(new Card("new name"));
		
		assertEquals(oldSize + 1, dt.getRowCount());
		assertEquals(oldDeckSize + 1, dt.getDeckSize());
		
		assertEquals("new name",dt.getName(oldSize));
		assertEquals("", dt.getDescription(oldSize));
		assertEquals(1, dt.getCopies(oldSize));
		
		
		// name and description		
		oldSize = dt.getRowCount();
		oldDeckSize = dt.getDeckSize();
		
		dt.addCard(new Card("new name", "description"));
		
		assertEquals(oldSize + 1, dt.getRowCount());
		assertEquals(oldDeckSize + 1, dt.getDeckSize());
		
		assertEquals("new name",dt.getName(oldSize));
		assertEquals("description", dt.getDescription(oldSize));
		assertEquals(1, dt.getCopies(oldSize));
		
		
		// name and copies		
		oldSize = dt.getRowCount();
		oldDeckSize = dt.getDeckSize();
		
		dt.addCard(new Card("new name", 2));
		
		assertEquals(oldSize + 1, dt.getRowCount());
		assertEquals(oldDeckSize + 2, dt.getDeckSize());
		
		assertEquals("new name",dt.getName(oldSize));
		assertEquals("", dt.getDescription(oldSize));
		assertEquals(2, dt.getCopies(oldSize));
		
		
		// name, description, and copies		
		oldSize = dt.getRowCount();
		oldDeckSize = dt.getDeckSize();
		
		dt.addCard(new Card("new name", "desc", 3));
		
		assertEquals(oldSize + 1, dt.getRowCount());
		assertEquals(oldDeckSize + 3, dt.getDeckSize());
		
		assertEquals("new name",dt.getName(oldSize));
		assertEquals("desc", dt.getDescription(oldSize));
		assertEquals(3, dt.getCopies(oldSize));
		
	}

	/**
	 * Test method for {@link model.DeckTable#editCard(int, java.lang.String, java.lang.String, int)}.
	 * @throws Exception if index out of range, or card inputs are invalid
	 */
	@Test
	void testEditCard() throws Exception {
		// edit first card
		dt.editCard(0, "edit name", "edit desc", 2);
		assertEquals("edit name", dt.getName(0));
		assertEquals("edit desc", dt.getDescription(0));
		assertEquals(2, dt.getCopies(0));
		
		assertEquals(4, dt.getRowCount());
		assertEquals(13, dt.getDeckSize());
		
		
		// edit last card
		dt.editCard(3, "name", "", 1);
		assertEquals("name", dt.getName(3));
		assertEquals("", dt.getDescription(3));
		assertEquals(1, dt.getCopies(3));
		
		assertEquals(4, dt.getRowCount());
		assertEquals(9, dt.getDeckSize());
		
	}

	/**
	 * Test method for {@link model.DeckTable#deleteCard(int)}.
	 * @throws Exception if index out of range
	 */
	@Test
	void testDeleteCard() throws Exception {
		dt.deleteCard(0);
		assertEquals(11, dt.getDeckSize());
		assertEquals(3, dt.getRowCount());
		
		// check that the elements have moved up
		
		assertEquals("name2", dt.getValueAt(1 - 1, 0));
		assertEquals("description", dt.getValueAt(1 - 1, 1));
		assertEquals(1, dt.getValueAt(1 - 1, 2));
		
		assertEquals("name3", dt.getValueAt(2 - 1, 0));
		assertEquals("", dt.getValueAt(2 - 1, 1));
		assertEquals(5, dt.getValueAt(2 - 1, 2));
		
		assertEquals("name4", dt.getValueAt(3 - 1, 0));
		assertEquals("description", dt.getValueAt(3 - 1, 1));
		assertEquals(5, dt.getValueAt(3 - 1, 2));
		
	}

	/**
	 * Test method for {@link model.DeckTable#getName(int)}.
	 * @throws Exception if index out of range
	 */
	@Test
	void testGetName() throws Exception {
		assertEquals("name", dt.getName(0));
		assertEquals("name4", dt.getName(3));
	}

	/**
	 * Test method for {@link model.DeckTable#getDescription(int)}.
	 * @throws Exception if index out of range
	 */
	@Test
	void testGetDescription() throws Exception {
		assertEquals("", dt.getDescription(0));
		assertEquals("description", dt.getDescription(3));
	}

	/**
	 * Test method for {@link model.DeckTable#getCopies(int)}.
	 * @throws Exception if index out of range
	 */
	@Test
	void testGetCopies() throws Exception {
		assertEquals(1, dt.getCopies(0));
		assertEquals(5, dt.getCopies(3));
	}

	/**
	 * Test method for {@link model.DeckTable#getCard(int)}.
	 * @throws Exception if index out of range
	 */
	@Test
	void testGetCard() throws Exception {
		// TODO this might be easier if Card had its own "=" overwrite, 
		/*
		 * assertEquals(new Card("name"), dt.getCard(0)); 
		 * assertEquals(new Card("name4","description",5), dt.getCard(3));
		 */
		Card card1 = new Card("name");
		Card testCard = dt.getCard(0);
		
		assertEquals(card1.getName(), testCard.getName());
		assertEquals(card1.getDescription(), testCard.getDescription());
		assertEquals(card1.getCopies(), testCard.getCopies());
		
		
		card1 = new Card("name4","description",5);
		testCard = dt.getCard(3);
		
		assertEquals(card1.getName(), testCard.getName());
		assertEquals(card1.getDescription(), testCard.getDescription());
		assertEquals(card1.getCopies(), testCard.getCopies());
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	void testToString() {
		// System.out.println(dt);
		assertTrue(dt.toString().startsWith("model.DeckTable@"));
	}

}
