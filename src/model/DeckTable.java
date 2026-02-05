/**
 * used to handle the table of cards
 */
package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class DeckTable extends AbstractTableModel {
	
	private ArrayList<Card> Deck;

	/**
	 * creates a new Deck with no cards in it
	 */
	public DeckTable() {
		// TODO Auto-generated constructor stub
		Deck = new ArrayList<Card>();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}
	
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
		return Deck.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch(arg1) {
		case 0:
			return Deck.get(arg0).getName();
		case 1:
			return Deck.get(arg0).getDescription();
		case 2:
			return Deck.get(arg0).getCopies();
		default:
			return "";
		}	
	}
	
	
	public int getDeckSize() {
		int count = 0;
		for (Card c : Deck) {
			count += c.getCopies();
		}
		return count;
	}
	
	public void addCard(Card card) {
		Deck.add(card);
	}

}
