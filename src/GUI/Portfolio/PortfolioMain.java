package GUI.Portfolio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import AccountSystem.Session;
import GUI.GUIManager;

public class PortfolioMain extends JPanel {


	private static final long serialVersionUID = 1L;
	private int PM_WIDTH;
	private int PM_HEIGHT;
	private Dimension PM_SIZE;
	
	private JPanel TOP_CONTAINER;
	private JPanel BOTTOM_CONTAINER;
	private JPanel TOP_RIGHT_PANEL;
	private JPanel TOP_LEFT_PANEL;
	private JTable SAVED_LEADS;
	private JScrollPane SAVED_LEADS_SCROLL;
	
	private String[][] savedRow = new String [10][4];
	private String[] savedColumn = {"ID","Date","Lead Name","Reason Saved"};
	
	public PortfolioMain(){
		setPanelSize();
		createContainers();
		putPanelsTogether();
	}
	
	private void setPanelSize(){
		PM_WIDTH = (int) (GUIManager.getScreenWidth() * 0.8);
		PM_HEIGHT = GUIManager.getScreenHeight();
		PM_SIZE = new Dimension(PM_WIDTH,PM_HEIGHT);
		this.setSize(PM_SIZE);
		this.setMaximumSize(PM_SIZE);
		this.setMinimumSize(PM_SIZE);
	}
	
	private void createContainers(){
		TOP_CONTAINER = new JPanel();
		TOP_CONTAINER.setSize(PM_WIDTH,PM_HEIGHT/2);
		TOP_CONTAINER.setMaximumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.setMinimumSize(TOP_CONTAINER.getSize());
		populateTopContainer();
		
		BOTTOM_CONTAINER = new JPanel();
		BOTTOM_CONTAINER.setSize(PM_WIDTH,PM_HEIGHT/2);
		BOTTOM_CONTAINER.setMaximumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.setMinimumSize(BOTTOM_CONTAINER.getSize());
		populateBottomContainer();
	}
	
	private void populateTopContainer(){
		TOP_CONTAINER.setLayout(new BoxLayout(TOP_CONTAINER,BoxLayout.X_AXIS));
		TOP_LEFT_PANEL = new JPanel();
		TOP_LEFT_PANEL.setSize(PM_WIDTH/2,PM_HEIGHT/2);
		TOP_LEFT_PANEL.setMaximumSize(TOP_LEFT_PANEL.getSize());
		TOP_LEFT_PANEL.setMinimumSize(TOP_LEFT_PANEL.getSize());
		TOP_LEFT_PANEL.add(new JLabel("My Info"));
		TOP_LEFT_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		createTopLeftPanel();
		TOP_CONTAINER.add(TOP_LEFT_PANEL);
		
		TOP_RIGHT_PANEL = new JPanel();
		TOP_RIGHT_PANEL.setSize(PM_WIDTH/2,PM_HEIGHT/2);
		TOP_RIGHT_PANEL.setMaximumSize(TOP_RIGHT_PANEL.getSize());
		TOP_RIGHT_PANEL.setMinimumSize(TOP_RIGHT_PANEL.getSize());
		TOP_RIGHT_PANEL.add(new JLabel("Company Info"));
		TOP_RIGHT_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		TOP_CONTAINER.add(TOP_RIGHT_PANEL);
	}
	
	private void createTopLeftPanel(){
		TOP_LEFT_PANEL.setLayout(new BoxLayout(TOP_LEFT_PANEL,BoxLayout.Y_AXIS));
		JLabel[] labels = new JLabel[8];
		for(int a = 0;a<labels.length;a++){
			labels[a] = new JLabel();
			labels[a].setFont(new Font(labels[a].getFont().getFontName(),Font.PLAIN,24));
		}
		labels[0].setText("Username: " + Session.getUsername());
		labels[1].setText("Name: " + Session.EMPLOYEE_NAME_FIRST + " " + Session.EMPLOYEE_NAME_LAST);
		labels[2].setText("Email: " + Session.getEmail());
		labels[3].setText("Today's Dials: " + Session.getSESSION_NUM_CALLS());
		labels[4].setText("Today's Appts Set: " + Session.getSESSION_NUM_APPTS());
		labels[5].setText("Today's Talk Time: " + Session.getSESSION_TALK_TIME());
		labels[6].setText("Total Appts Set: " + Session.getTOTAL_APPTS_SET());
		for(int a = 0; a<labels.length;a++){
			TOP_LEFT_PANEL.add(labels[a]);
		}
	}
	
	private void populateBottomContainer(){
		BOTTOM_CONTAINER.setLayout(new BorderLayout());
		SAVED_LEADS = new JTable(savedRow,savedColumn);
		SAVED_LEADS.setShowHorizontalLines(true);
		SAVED_LEADS.setShowVerticalLines(true);
		SAVED_LEADS.setRowHeight(30);
		SAVED_LEADS_SCROLL = new JScrollPane(SAVED_LEADS);
		BOTTOM_CONTAINER.add(SAVED_LEADS_SCROLL,BorderLayout.CENTER);
	}
	
	private void putPanelsTogether(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(TOP_CONTAINER);
		this.add(BOTTOM_CONTAINER);
	}
	
}
