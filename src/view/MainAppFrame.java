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
import javax.swing.filechooser.FileFilter;

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
import javax.swing.JFileChooser;

import model.CSVFilter;
import model.Card;
import model.DeckFilter;
import model.DeckTable;
import model.DeckTableSelectionListener;
import model.JSONFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MainAppFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblDeckTable;
	private JButton btnEditCard;
	private JButton btnDeleteCard;
	
	protected DeckTable deckTable;
	private int selectedInx = -1;
	
	final JFileChooser fileChooser = new JFileChooser();

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
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new DeckFilter());
		fileChooser.addChoosableFileFilter(new CSVFilter());
		fileChooser.addChoosableFileFilter(new JSONFilter());
		initGUI();

	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		/**
		 * Menu Bar
		 */
		
		JMenuBar mnbrMainMenu = new JMenuBar();
		setJMenuBar(mnbrMainMenu);
		
		JMenu mnFile = new JMenu("File");
		mnbrMainMenu.add(mnFile);
		
		JMenuItem mntmLoad = new JMenuItem("Load Deck");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadFile();
			}
		});
		mnFile.add(mntmLoad);
		
		JMenuItem mntmSave = new JMenuItem("Save Deck");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		mnFile.add(mntmSave);
		
		/*
		 * JSeparator separator_1 = new JSeparator(); mnFile.add(separator_1);
		 * 
		 * JMenu mnImport = new JMenu("Import Deck..."); mnFile.add(mnImport);
		 */
		
		/**
		 * YDK gives a list of card IDs
		 * Connecting to external card databases is a bit beyond the scope
		 */
		/*
		 * JMenuItem mntmFromYDK = new JMenuItem("... from YDK");
		 * mnImport.add(mntmFromYDK);
		 */
		
		
		
		
		
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
		tblDeckTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// handling the cards in the deck 
		deckTable = new DeckTable();
		tblDeckTable.setModel(deckTable);
		
		// handling the selection event
		ListSelectionModel tblDeckTableSelectionModel = tblDeckTable.getSelectionModel();
		tblDeckTableSelectionModel.addListSelectionListener(new DeckTableSelectionListener(this));
		
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

		btnEditCard = new JButton("Edit Card");
		btnEditCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCardModalDialog(selectedInx);
			}
		});
		btnEditCard.setEnabled(false);
		pnlDeckEdit.add(btnEditCard, "cell 0 1");

		btnDeleteCard = new JButton("Delete Card");
		btnDeleteCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDeleteCardDialog(selectedInx);
			}
		});
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
	
	private void openCardModalDialog(int inx) {
		CardModalDialog dialog;
		try {
			dialog = new CardModalDialog(this, inx);
			dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void openDeleteCardDialog(int inx) {
		// TODO Auto-generated method stub
		CardDeleteDialog dialog;
		try {
			dialog = new CardDeleteDialog(this, inx);
			dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void noSelection() {
		btnEditCard.setEnabled(false);
		btnDeleteCard.setEnabled(false);
	}

	public void singleSelection(int i) {
		btnEditCard.setEnabled(true);
		btnDeleteCard.setEnabled(true);
		selectedInx = i;
	}

	public void multiSelection(int[] selected) {
		// TODO Auto-generated method stub
		// currently not possible to select multiple
		
	}
	
	/**
	 * Opens a filechooser to select a file to load, exits if action canceled
	 */
	private void loadFile() {
		// TODO: Check if changes have been made to open deck
		// if so, there should be a dialog to ask if the user wants to save their changes
		int returnVal = fileChooser.showOpenDialog(this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			deckTable.loadFromFile(file);
			updateUI();
		} else {
			System.out.println("action canceled");
		}
	}
	
	/**
	 * Opens a file chooser to select a save file, exits if action canceled or file write error
	 * if successful, it will save the current deck to the selected file
	 */
	private void saveFile() {
		// TODO: Check if the deck already has a filename
		// regular "Save" should automatically overwrite if deck already has filename
		// opens file chooser if "Save As" chosen or if deck doesn't have filename already
		int returnVal = fileChooser.showSaveDialog(this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();			
				
			try {
				FileWriter fileWriter = new FileWriter(file);
				
				ArrayList<String> lines = deckTable.toCSVLines();
				fileWriter.write("");
				for (String l: lines) {
					fileWriter.append(l);
					fileWriter.append("\n");
				}
				fileWriter.close();
				
			} catch (IOException e) {
				System.out.println("file write error");
				e.printStackTrace();
			}
			
		} else {
			System.out.println("action canceled");
		}
	}
	
	
	/**
	 * updates the table element
	 */
	protected void updateUI() {
		tblDeckTable.updateUI();
	}
	
	/**
	 * clear the selection after deletion
	 */
	protected void clearSelection() {
		tblDeckTable.getSelectionModel().clearSelection();
	}
}
