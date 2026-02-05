package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import model.Card;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardModalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextArea txtDescription;
	private JSpinner spnCopies;
	
	private MainAppFrame parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardModalDialog dialog = new CardModalDialog(null);			// main shouldnt be run, so it's fine
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public CardModalDialog(MainAppFrame parent) {
		super(parent, "Card Dialog", true);	// Eventually should be changed to say add or edit depending on what the user is doing
		this.parent = parent;
		initGUI();
	}
	
	private void initGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlForm = new JPanel();
		getContentPane().add(pnlForm);
		pnlForm.setLayout(new MigLayout("", "[118px][316px,grow]", "[19px][19px,grow][19px,grow 2][]"));
		
		JLabel lblCardName = new JLabel("Card Name");
		pnlForm.add(lblCardName, "cell 0 0,alignx right,aligny center");
		
		txtName = new JTextField();
		pnlForm.add(txtName, "cell 1 0,growx,aligny top");
		txtName.setColumns(10);
		
		
		JLabel lblCardDescription = new JLabel("Card Description");
		pnlForm.add(lblCardDescription, "cell 0 1,alignx right,aligny center");
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		pnlForm.add(txtDescription, "cell 1 1,grow");
		
		JLabel lblCopiesInDeck = new JLabel("Copies in Deck");
		pnlForm.add(lblCopiesInDeck, "cell 0 2,alignx right,aligny center");
		
		JPanel pnlCopies = new JPanel();
		pnlForm.add(pnlCopies, "cell 1 2,grow");
		SpringLayout sl_pnlCopies = new SpringLayout();
		pnlCopies.setLayout(sl_pnlCopies);
		
		spnCopies = new JSpinner();
		spnCopies.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		sl_pnlCopies.putConstraint(SpringLayout.NORTH, spnCopies, 0, SpringLayout.NORTH, pnlCopies);
		sl_pnlCopies.putConstraint(SpringLayout.WEST, spnCopies, 0, SpringLayout.WEST, pnlCopies);
		sl_pnlCopies.putConstraint(SpringLayout.EAST, spnCopies, 40, SpringLayout.WEST, pnlCopies);
		pnlCopies.add(spnCopies);
		
		//button bar
		
		JPanel pnlBottom = new JPanel();
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);
		pnlBottom.setLayout(new MigLayout("", "[350px][][][]", "[35px]"));
		
		JPanel pnlButtons = new JPanel();
		pnlBottom.add(pnlButtons, "cell 3 0,alignx left,aligny top");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();			// in the future, have a way to see if there's unsaved changes and have an "are you sure?" dialog if so, also for exiting the dialog
			}
		});
		pnlButtons.add(btnCancel);
		btnCancel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton btnEdit = new JButton("Edit");
		pnlButtons.add(btnEdit);
		btnEdit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnEdit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEdit.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInput()) {
					// add an item to the deck
					addCard();
					dispose();
				}
			}
		});
		pnlButtons.add(btnAdd);
		btnAdd.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAdd.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton btnAddAnother = new JButton("Add Another");
		pnlButtons.add(btnAddAnother);
		btnAddAnother.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnAddAnother.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddAnother.setVerticalAlignment(SwingConstants.BOTTOM);

	}
	
	private boolean validateInput() {
		// check the fields
		// if one or more is wrong, put up an error message
		// then return false
		int errors = 0;
		if (txtName.getText().strip().isBlank()) {
			errors += 1;
			// display an error under the text box
		}
		//System.out.println(spnCopies.getValue().getClass());
		if (!spnCopies.getValue().getClass().isAssignableFrom(Integer.class) || (int) spnCopies.getValue()<=0) {
			errors += 1;
			// display an error under the spinner
		}
		
		if (errors > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	private void addCard() {
		parent.addCard(txtName.getText(), 
				txtDescription.getText(), 
				(int)spnCopies.getValue());
	}
}
