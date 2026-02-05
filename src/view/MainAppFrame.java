/*
 * The main window of the application
 * this is the main() that should be run
 * 
 */


package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

import model.Card;
import model.DeckTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblDeckTable;
	
	protected DeckTable deckTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppFrame frame = new MainAppFrame();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainAppFrame() {
		setTitle("TCG Calculator by E-BRANCH");
		initGUI();

	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		/* Area for the Deck Table,
		 * a table that lists the deck, with columns being information like # of copies */
		JScrollPane spDeckTable = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, spDeckTable, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, spDeckTable, 3, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spDeckTable, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(spDeckTable);

		tblDeckTable = new JTable();
		
		deckTable = new DeckTable();
		tblDeckTable.setModel(deckTable);
		
		tblDeckTable.setFillsViewportHeight(true);
		spDeckTable.setViewportView(tblDeckTable);

		/* Area for the controls, functions, and features of the application
		 * will be divided into subsections*/
		JPanel pnlControlPanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pnlControlPanel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pnlControlPanel, -150, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pnlControlPanel, 0, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, spDeckTable, -6, SpringLayout.WEST, pnlControlPanel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pnlControlPanel, 252, SpringLayout.NORTH, contentPane);
		contentPane.add(pnlControlPanel);
		pnlControlPanel.setLayout(new MigLayout("", "[109px][10px]", "[29px][223px]"));

		/* Control Panel/ Deck Edit,
		 * features to edit the deck list */
		JPanel pnlDeckEdit = new JPanel();
		pnlDeckEdit.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlDeckEdit.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlControlPanel.add(pnlDeckEdit, "cell 0 0,grow");
		pnlDeckEdit.setLayout(new MigLayout("", "[65px][]", "[15px][][]"));

		JLabel lblDeckEdit = new JLabel("Deck Edit");
		pnlDeckEdit.add(lblDeckEdit, "cell 0 0,alignx left,aligny top");

		JButton btnAddCard = new JButton("Add Card");
		btnAddCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCardModalDialog();
			}
		});
		pnlDeckEdit.add(btnAddCard, "flowy,cell 0 1");

		JButton btnEditCard = new JButton("Edit Card");
		btnEditCard.setEnabled(false);
		pnlDeckEdit.add(btnEditCard, "cell 0 1");

		JButton btnDeleteCard = new JButton("Delete Card");
		btnDeleteCard.setEnabled(false);
		pnlDeckEdit.add(btnDeleteCard, "cell 0 2");

		/* Control Panel/ Test,
		 * Holds various basic tests */
		JPanel pnlTest = new JPanel();
		pnlTest.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pnlTest.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlControlPanel.add(pnlTest, "flowx,cell 0 1,alignx left,growy");
		pnlTest.setLayout(new MigLayout("", "[39px]", "[15px][]"));

		JLabel lblTests = new JLabel("Tests");
		pnlTest.add(lblTests, "cell 0 0,alignx left,aligny top");

		JButton btnSampleHand = new JButton("Sample Hand");
		pnlTest.add(btnSampleHand, "cell 0 1");
	}

	private void start() {
		setVisible(true);
	}
	
	private void openCardModalDialog() {
		CardModalDialog dialog = new CardModalDialog(this);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	public void addCard(String name, String description, int copies) {
		try {
			deckTable.addCard(new Card(name, description, copies));
			tblDeckTable.updateUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
