package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;

public class CardDeleteDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlContent = new JPanel();
	
	private MainAppFrame parent;
	private int inx = -1;

	/**
	 * wont be used in normal application running
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CardDeleteDialog dialog = new CardDeleteDialog(null, -1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CardDeleteDialog(MainAppFrame parent, int inx) {
		super(parent, "Delete Card", true);
		setResizable(false);
		this.parent = parent;
		this.inx = inx;
		
		initGUI();
		
	}
	
	private void initGUI() {
		setBounds(100, 100, 450, 114);
		getContentPane().setLayout(new MigLayout("", "[50px,grow]", "[50px,grow][35px]"));
		FlowLayout fl_pnlContent = new FlowLayout();
		fl_pnlContent.setVgap(0);
		fl_pnlContent.setHgap(0);
		pnlContent.setLayout(fl_pnlContent);
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, "cell 0 0,alignx leading,aligny top");
		{
			JLabel lblAreYouSure = new JLabel("Are you sure? This action cannot be undone.");
			pnlContent.add(lblAreYouSure);
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlButtons, "cell 0 1,growx,aligny top");
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setActionCommand("OK");
				pnlButtons.add(btnCancel);
				getRootPane().setDefaultButton(btnCancel);
			}
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							deleteCard();
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
				btnDelete.setActionCommand("Cancel");
				pnlButtons.add(btnDelete);
			}
		}
	}
	
	private void deleteCard() throws Exception {
		parent.deckTable.deleteCard(inx);
		parent.updateUI();
		parent.clearSelection();
	}

}
