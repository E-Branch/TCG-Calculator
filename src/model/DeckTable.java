/**
 * used to handle the table of cards
 */
package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class DeckTable extends AbstractTableModel {
	
	private ArrayList<Card> deck;

	/**
	 * Creates a new Deck with no cards in it
	 */
	public DeckTable() {
		// TODO Auto-generated constructor stub
		deck = new ArrayList<Card>();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0:
			return "Name";
		case 1:
			return "Description";
		case 2:
			return "Copies";
		default:
			return "";
		}
	}

	@Override
	public int getRowCount() {
		return deck.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch(arg1) {
		case 0:
			return deck.get(arg0).getName();
		case 1:
			return deck.get(arg0).getDescription();
		case 2:
			return deck.get(arg0).getCopies();
		default:
			return "";
		}	
	}
	
	
	/**
	 * Returns the full deck size, counting each copy of each card
	 * 
	 * @return the deck size
	 */
	public int getDeckSize() {
		int count = 0;
		for (Card c : deck) {
			count += c.getCopies();
		}
		return count;
	}
	
	/**
	 * Adds the provided card to the end of the list
	 * 
	 * @param card the Card to be added
	 */
	public void addCard(Card card) {
		deck.add(card);
	}
	
	/**
	 * Edits the card at index inx
	 * 
	 * @param inx the index of the card to edit
	 * @param name what to set the cards name to
	 * @param Description what to set the cards description to
	 * @param copies what to set the number of copies to
	 * @throws Exception if value set fails, or if index out of range
	 */
	public void editCard(int inx, String name, String Description, int copies) throws Exception {
		if (inx >= deck.size()) {
			throw new Exception("Selected index out of range");
		} 
		else {
			deck.get(inx).setName(name);
			deck.get(inx).setDescription(Description);
			deck.get(inx).setCopies(copies);
		}
	}
	
	/**
	 * Deletes the card at index inx, shifting the cards after it up one index
	 * 
	 * @param inx the index of the card to delete
	 * @throws Exception if index out of range
	 */
	public void deleteCard(int inx) throws Exception {
		if (inx >= deck.size()) {
			throw new Exception("Selected index out of range");
		} else {
			deck.remove(inx);
		}
	}
	
	/**
	 * gets the name of the card at inx
	 * 
	 * @param inx the index of the card
	 * @return the name of the card
	 * @throws Exception if the index is out of range
	 */
	public String getName(int inx) throws Exception {
		if (inx >= deck.size()) {
			throw new Exception("Selected index out of range");
		} else {
			return deck.get(inx).getName();
		}
	}
	
	/**
	 * returns the description of the card at inx
	 * 
	 * @param inx the index of the card
	 * @return the description of the card
	 * @throws Exception if the index is out of range
	 */
	public String getDescription(int inx) throws Exception {
		if (inx >= deck.size()) {
			throw new Exception("Selected index out of range");
		} else {
			return deck.get(inx).getDescription();
		}
	}
	
	/**
	 * returns the number of copies of the card at inx
	 * 
	 * @param inx the index of the card
	 * @return the value of copies of the card
	 * @throws Exception if the index is out of range
	 */
	public int getCopies(int inx) throws Exception {
		if (inx >= deck.size()) {
			throw new Exception("Selected index out of range");
		} else {
			return deck.get(inx).getCopies();
		}
	}
	
	/**
	 * returns the card at inx
	 * 
	 * @param inx the index of the card
	 * @return the Card at inx
	 * @throws Exception if the index is out of range
	 */
	public Card getCard(int inx) throws Exception {
		if (inx >= deck.size()) {
			throw new Exception("Selected index out of range");
		} else {
			return deck.get(inx);
		}
	}

}
