package GUI.Miscellaneous;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Engine.FieldManager;
import GUI.GUIManager;

public class PagesPanel extends JPanel implements ActionListener,MouseListener {

	
	private static final long serialVersionUID = 1L;
	
	private double PP_HEIGHT_RATIO;
	private int PP_WIDTH;
	private int PP_HEIGHT;
	private Dimension PP_SIZE;
	
	private JButton BACK_BUTTON;
	private JButton NEXT_BUTTON;
	private JTextPane PAGE_NUMBER;
	private JPanel LEFT_GLUE;
	private JPanel BUTTON_HOLDER;
	private JPanel RIGHT_GLUE;
	private JPanel TEMP_HOLDER;
	
	
	public PagesPanel(JPanel panel, double ratio){
		setPanelSize(panel,ratio);
		createComponents();
		addComponents();
		putPanelsTogether();
		
	}
	
	private void setPanelSize(JPanel panel, double ratio){
		PP_HEIGHT_RATIO = ratio;
		PP_WIDTH = panel.getWidth();
		PP_HEIGHT = (int) (panel.getHeight() * PP_HEIGHT_RATIO);
		PP_SIZE = new Dimension(PP_WIDTH,PP_HEIGHT);
		this.setSize(PP_SIZE);
		this.setMaximumSize(PP_SIZE);
		this.setMinimumSize(PP_SIZE);
	}
	
	private void createComponents(){
		createButtons();
		PAGE_NUMBER = new JTextPane();
		PAGE_NUMBER.setText("1");
		PAGE_NUMBER.setEditable(false);
		PAGE_NUMBER.setFont(new Font(PAGE_NUMBER.getFont().getFontName(), Font.PLAIN,18));
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
		PAGE_NUMBER.setParagraphAttributes(attribs,true);
		PAGE_NUMBER.addMouseListener(this);
		LEFT_GLUE = new JPanel();
		LEFT_GLUE.setSize((int) (this.getWidth() * 0.4),PP_HEIGHT);
		LEFT_GLUE.setMaximumSize(LEFT_GLUE.getSize());
		LEFT_GLUE.setMinimumSize(LEFT_GLUE.getSize());
		RIGHT_GLUE = new JPanel();
		RIGHT_GLUE.setSize((int) (this.getWidth() * 0.4),PP_HEIGHT);
		RIGHT_GLUE.setMaximumSize(RIGHT_GLUE.getSize());
		RIGHT_GLUE.setMinimumSize(RIGHT_GLUE.getSize());
		BUTTON_HOLDER = new JPanel(new GridBagLayout());
		BUTTON_HOLDER.setSize((int) (this.getWidth() * 0.2),PP_HEIGHT);
		BUTTON_HOLDER.setMaximumSize(BUTTON_HOLDER.getSize());
		BUTTON_HOLDER.setMinimumSize(BUTTON_HOLDER.getSize());
		addComponents();
		
	}
	
	private void addComponents(){
		GridBagConstraints GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.fill = GridBagConstraints.BOTH;
		BUTTON_HOLDER.add(BACK_BUTTON, GBC);
		GBC.gridx = 1;
		BUTTON_HOLDER.add(PAGE_NUMBER, GBC);
		GBC.gridx = 2;
		BUTTON_HOLDER.add(NEXT_BUTTON, GBC);
	}
	
	private void putPanelsTogether(){
		TEMP_HOLDER = new JPanel();
		TEMP_HOLDER.setLayout(new BoxLayout(TEMP_HOLDER,BoxLayout.X_AXIS));
		TEMP_HOLDER.add(LEFT_GLUE);
		TEMP_HOLDER.add(BUTTON_HOLDER);
		TEMP_HOLDER.add(RIGHT_GLUE);
		this.setLayout(new BorderLayout());
		this.add(TEMP_HOLDER, BorderLayout.CENTER);
	}
	private void createButtons(){
		BACK_BUTTON = new JButton("<---");
		BACK_BUTTON.setEnabled(false);
		BACK_BUTTON.addActionListener(this);
		NEXT_BUTTON = new JButton("--->");
		NEXT_BUTTON.addActionListener(this);
	}
	private void switchToPage(int page){
		FieldManager.currentPage = Math.abs(page);
		PAGE_NUMBER.setText("" + page);
		try {
			FieldManager.populateTable(FieldManager.currentPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GUIManager.DASHBOARD_PANEL.revalidate();
		GUIManager.DASHBOARD_PANEL.repaint();
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BACK_BUTTON) {
			if (FieldManager.currentPage == 2) {
				BACK_BUTTON.setEnabled(false);
				NEXT_BUTTON.setEnabled(true);
				switchToPage(FieldManager.currentPage - 1);
			} else {
				NEXT_BUTTON.setEnabled(true);
				switchToPage(FieldManager.currentPage - 1);
			}

		}
		if (e.getSource() == NEXT_BUTTON) {
			if (FieldManager.totalRowCount % 50 == 0) {
				if (FieldManager.currentPage == FieldManager.totalRowCount / 50) {
					BACK_BUTTON.setEnabled(true);
					NEXT_BUTTON.setEnabled(false);
				} else {
					BACK_BUTTON.setEnabled(true);
					switchToPage(FieldManager.currentPage + 1);

				}
			} else {
				if (FieldManager.currentPage == FieldManager.totalRowCount / 50 + 1) {
					NEXT_BUTTON.setEnabled(false);
					BACK_BUTTON.setEnabled(true);
				} else {
					BACK_BUTTON.setEnabled(true);
					switchToPage(FieldManager.currentPage + 1);

				}

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String x = JOptionPane.showInputDialog(null, "What page number would you like to go to?");
		Integer pageNumberEntered = Integer.parseInt(x);
		if(pageNumberEntered >=0 && pageNumberEntered != null && (pageNumberEntered <= FieldManager.totalRowCount / 50 + 1)){
			switchToPage(pageNumberEntered);
			if (FieldManager.totalRowCount % 50 == 0) {
				if (FieldManager.currentPage == FieldManager.totalRowCount / 50) {
					BACK_BUTTON.setEnabled(true);
					NEXT_BUTTON.setEnabled(false);
				}
			} else {
				if (FieldManager.currentPage == FieldManager.totalRowCount / 50 + 1) {
					NEXT_BUTTON.setEnabled(false);
					BACK_BUTTON.setEnabled(true);
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Please Enter A Valid Page Number");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
