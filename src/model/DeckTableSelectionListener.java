/**
 * 
 */
package model;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.MainAppFrame;

import javax.swing.ListSelectionModel;

/**
 * 
 */
public class DeckTableSelectionListener implements ListSelectionListener {
	
	private MainAppFrame parent;

	/**
	 *  constructor for DeckTableSelectionListener
	 *  
	 *  @param parent the frame that created this, in order for the buttons on the main frame to update
	 */
	public DeckTableSelectionListener(MainAppFrame parent) {
		this.parent = parent;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		
		int[] selected = lsm.getSelectedIndices();
		switch(selected.length){
		case 0:
			parent.noSelection();
		case 1:
			parent.singleSelection(selected[0]);
		default:
			parent.multiSelection(selected);
		}

	}

}
