import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class WinException extends Exception {
	public WinException(String s) {
		super(s);
	}
} //class WinException

//You gay










//==========================================================================================

/* Entering result panel will add stats to profile at the spot
 *  and updates to file so everything is ok
 *  shows "You win!" "You lose..." "It's a draw" messages
 *  
 *  just one thing...
 *  idk how to transfer info from 
 */
/*
class ResultPanel {
	private JPanel mainPanel = new JPanel();
	
	
	public ResultPanel() {
		
		
	} //ResultPanel()
	
	public JComponent getMainPanel() {
		return mainPanel;
	} //getMainPanel()
} //class ResultPanel
*/

