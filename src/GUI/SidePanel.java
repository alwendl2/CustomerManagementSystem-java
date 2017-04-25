package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Engine.DealershipClientEngine;
import Engine.FieldManager;
import Engine.SQLManager;
import GUI.Appointments.AppointmentMainPanel;
import GUI.Appointments.AppointmentSidePanel;
import GUI.DealershipNotifications.SidePanelNotifications;
import GUI.Portfolio.PortfolioMain;
import GUI.Portfolio.PortfolioSidePanel;

public class SidePanel extends JPanel implements ActionListener {	

	private static final long serialVersionUID = 1L;
	
	private int SP_WIDTH;
	private int SP_HEIGHT;
	private Dimension SP_SIZE;
	
	
	JButton[] BUTTONS = new JButton[4]; //Creates an array of JButtons to add to sidePanle --- INIT IN DEFAULT CONSTRUCTOR
	GridBagLayout GBL; //Creates a GridBagLayout Field --- INIT IN DEFAULT CONSTRUCTOR
	GridBagConstraints GBC; //Creates a GridBagLayout --- INIT IN DEFAULT CONSTRUCTOR
	JPanel TOP_PANEL; //Creates a JPanel that will hold the menu buttons --- INIT IN DEFAULT CONSTRUCTOR
	JPanel BOTTOM_PANEL; //Creates a JPanel that will hold the notifications --- INIT IN THE DEFAULT CONSTRUCTOR

	/**
	 * Default Constructor
	 * 
	 */
	public SidePanel(){
		setPanelSettings(); 
		putTogetherSidePanel();
	}
	
	private void setPanelSettings(){ //Calls all methods regarding size, layout, and constraints
		setSidePanelSize();
		createGBL();
		createGBC();
		setSidePanelLayout();
	}
	private void putTogetherSidePanel(){ //Calls all methods building the side panel components and combines them
		createHoldingPanels();
		createMenu();
		initBottomPanel();
		addPanels();//Adds the two panels together --- SIDE BAR COMPLETE
	}
	
	private void setSidePanelSize(){ //Gets Screen size form GUIManager and sets static size
		SP_WIDTH = (int) (GUIManager.getScreenWidth() * 0.2);
		SP_HEIGHT = GUIManager.getScreenHeight();
		SP_SIZE = new Dimension(SP_WIDTH,SP_HEIGHT);
		this.setSize(SP_SIZE); //Sets size of panel
		this.setMaximumSize(SP_SIZE); //Will ensure that the panel will not change size
		this.setMinimumSize(SP_SIZE); //^^
	}
	private void setSidePanelLayout(){ //Sets the layout of the whole SidePanel
		this.setLayout(GBL);
	}
	private void createGBL(){ //Creates and Initializes the GridBagLayout
		GUIManager.createGridBagLayout();
		GBL = GUIManager.getGridBagLayout();
	}
	private void createGBC(){ //Creates and Initializes the GridBagConstraints
		GUIManager.createGBC();
		GBC = GUIManager.getGBC();
	}
	private void createHoldingPanels(){ //Creates two Panels TOP and BOTTOM --- HOLDS THE COMPONENTS OF THE SIDE PANEL
		TOP_PANEL = new JPanel();
		TOP_PANEL.setSize(SP_WIDTH,SP_HEIGHT/2);
		TOP_PANEL.setMaximumSize(TOP_PANEL.getSize());
		TOP_PANEL.setMinimumSize(TOP_PANEL.getSize());
		BOTTOM_PANEL = new JPanel();
		BOTTOM_PANEL.setSize(SP_WIDTH,SP_HEIGHT/2);
		BOTTOM_PANEL.setMaximumSize(BOTTOM_PANEL.getSize());
		BOTTOM_PANEL.setMinimumSize(BOTTOM_PANEL.getSize());
		
	}
	
	private void createMenu() { //Creates and initializes buttons and adds them into the TOP_PANEL using GBL
		TOP_PANEL.setLayout(GBL);
		for (int a = 0; a < BUTTONS.length; a++) {
			BUTTONS[a] = new JButton();
			BUTTONS[a].addActionListener(this);
			GBC.gridx = 0;
			GBC.gridy = a;
			GBC.weightx = 1;
			GBC.weighty = 1;
			GBC.fill = GridBagConstraints.BOTH;
			TOP_PANEL.add(BUTTONS[a],GBC);
		}
		
		BUTTONS[0].setText("Client Select");
		BUTTONS[1].setText("Client Appointments");
		BUTTONS[2].setText("My Portfolio");
		BUTTONS[3].setText("Logout");
		BUTTONS[0].setFont(new Font("Serif", Font.BOLD,20));
		BUTTONS[1].setFont(new Font("Serif", Font.BOLD,20));
		BUTTONS[2].setFont(new Font("Serif", Font.BOLD,20));
		BUTTONS[3].setFont(new Font("Serif", Font.BOLD,20));
	}
	
	private void initBottomPanel() { //Creates and initializes the BOTTOM_PANEL which holds the notifications
		BOTTOM_PANEL.setLayout(new BorderLayout()); 
		BOTTOM_PANEL.add(new SidePanelNotifications(BOTTOM_PANEL),BorderLayout.CENTER);
		BOTTOM_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void addPanels() { //Adds both panels into the Side Panel
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.fill = GridBagConstraints.BOTH;
		this.add(TOP_PANEL, GBC);
		GBC.gridy = 1;
		this.add(BOTTOM_PANEL, GBC);
	}
	
	public void createNewNotifications() {
		BOTTOM_PANEL.removeAll();
		BOTTOM_PANEL.add(new SidePanelNotifications(BOTTOM_PANEL),BorderLayout.CENTER);
    	BOTTOM_PANEL.revalidate();
    	BOTTOM_PANEL.repaint();
    	this.revalidate();
    	this.repaint();
	}
	
	public void invokeListener() {
		for (ActionListener x : BUTTONS[0].getActionListeners()) {
			x.actionPerformed(new ActionEvent(BUTTONS[0],
					ActionEvent.ACTION_PERFORMED, null));
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == BUTTONS[0]){
			String[] databases =FieldManager.getDealershipDatabases(); 
			for(String db:databases){
				System.out.println(db);
			}
			System.out.println("------------------------");
			String[] names = FieldManager.getDealershipNames();
			for(String nombres:names){
				System.out.println(nombres);
			}
			System.out.println("------------------------");

			String input = (String)JOptionPane.showInputDialog(null,"Select Client...","Client Select",JOptionPane.QUESTION_MESSAGE, null,names, names[0]); 
			    System.out.println(input);
			    for(int x=0;x<names.length;x++) {
			    	if(input.equals(names[x])) {
			    		try {
			    			//DealershipClientEngine.checkPotentialClientDatabase(databases[x]);
			    			FieldManager.currentPage = 1;
			    			SQLManager.DatabaseConnector(databases[x]);
							FieldManager.populateTable(FieldManager.currentPage);
							GUIManager.DASHBOARD_PANEL.revalidate();
							GUIManager.DASHBOARD_PANEL.repaint();
							FieldManager.setCURRENT_DEALER_DB(databases[x]);
							FieldManager.setTotalLeads();
							GUIManager.DASHBOARD_PANEL = new DashBoardMain();
							System.out.println(DealershipClientEngine.isPotentialClient());
							GUIManager.switchPanels(GUIManager.SIDE_PANEL, GUIManager.DASHBOARD_PANEL);
							GUIManager.DASHBOARD_PANEL.revalidate();
							GUIManager.DASHBOARD_PANEL.repaint();
							FieldManager.populateTable(FieldManager.currentPage);
							createNewNotifications();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
			    	
			    	}
			    }
		    	GUIManager.DASHBOARD_PANEL.setColors();

		}
		if(e.getSource() == BUTTONS[1]) {
			GUIManager.switchPanels(new AppointmentSidePanel(), new AppointmentMainPanel());
		}
		if(e.getSource() == BUTTONS[2]){
			GUIManager.switchPanels(new PortfolioSidePanel(), new PortfolioMain());
		}
		if(e.getSource() == BUTTONS[3]){
			GUIManager.logout();
		}
		}
	
	
	
	
}
