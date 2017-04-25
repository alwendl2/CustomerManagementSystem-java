package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import AccountSystem.Session;
import Engine.DealershipClientEngine;
import Engine.FieldManager;
import GUI.Appointments.AddAppointmentScreen;
import GUI.Miscellaneous.PagesPanel;
import GUI.Miscellaneous.TableRenderer;
import GUI.Tabs.ClientHistory;
import GUI.Tabs.ClientInfo;

public class DashBoardMain extends JPanel implements ActionListener, ListSelectionListener{

	private static final long serialVersionUID = 1L;
	
	private int DB_WIDTH;
	private int DB_HEIGHT;
	private Dimension DB_SIZE;
	
	private JTabbedPane DB_TABBED_PANE;
	private JTable DASHBOARD_LEAD_TABLE;
	private JTable NOTES_TABLE;
	private JScrollPane NOTES_SCROLL_PANE;
	private JScrollPane TABLE_SCROLL_PANE;
	private JButton INCREMENT_NUM_CALLS;
	private JButton INCREMENT_NUM_ANSWER;
	private JButton ADD_NOTES;
	private JButton SET_APPOINTMENT;
	private JButton DNC_BUTTON;
	private JPanel TOP_CONTAINER;
	private JPanel BOTTOM_CONTAINER;
	private JPanel NOTES_TAB;
	private JPanel NOTES_BUTTON_PANEL;
	private JPanel TEMP_NOTES_HOLDER;
	private JPanel TEMP_BOTTOM;
	
	

	
	public DashBoardMain(){
		setPanelSize();
		createContainers();
	}
	private void setPanelSize(){ //Gathers the screen size and sets the panels size according to the screen size
		DB_WIDTH = (int) (GUIManager.getScreenWidth() * 0.8);
		DB_HEIGHT = GUIManager.getScreenHeight();
		DB_SIZE = new Dimension(DB_WIDTH, DB_HEIGHT);
		this.setSize(DB_SIZE); //sets the size of the panel
		this.setMaximumSize(DB_SIZE); //Ensures that panel wont change size
		this.setMinimumSize(DB_SIZE); //Ensures that panel wont change size
	}
	private void createContainers(){
		TOP_CONTAINER = new JPanel();
		TOP_CONTAINER.setSize(DB_WIDTH - 35,DB_HEIGHT/2);
		TOP_CONTAINER.setMaximumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.setMinimumSize(TOP_CONTAINER.getSize());
		createTopComponents();

		BOTTOM_CONTAINER = new JPanel(new BorderLayout());
		BOTTOM_CONTAINER.setSize(DB_WIDTH - 35 ,DB_HEIGHT/2 - 75);
		BOTTOM_CONTAINER.setMaximumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.setMinimumSize(BOTTOM_CONTAINER.getSize());
		createBottomComponents();
		
		putTogetherPanels();
	}
	
	private void createTopComponents(){ 
		TOP_CONTAINER.setLayout(new BorderLayout()); //sets the border of the top container to Border Layout -- WILL ALLOW TO FILL USING .CENTER
		createButtonsPanel(); //This will create the button panel once so the buttons wont duplicate
		DB_TABBED_PANE = new JTabbedPane();
		DB_TABBED_PANE.addTab("Client Info", new ClientInfo()); //WIll display the client info from the table
		DB_TABBED_PANE.addTab("Client Notes", createNotesPane()); //Will display the notes from the client
		DB_TABBED_PANE.addTab("Client History", new ClientHistory()); //Will display a full history of the client
		
		TOP_CONTAINER.add(DB_TABBED_PANE, BorderLayout.CENTER); //Adds tabbed pane and centers/fills the pane
	}
	private void createBottomComponents(){
		TEMP_BOTTOM = new JPanel();
		TEMP_BOTTOM.setLayout(new BoxLayout(TEMP_BOTTOM, BoxLayout.Y_AXIS));
		TEMP_BOTTOM.setSize(BOTTOM_CONTAINER.getSize());
		TEMP_BOTTOM.setMaximumSize(BOTTOM_CONTAINER.getSize());
		TEMP_BOTTOM.setMinimumSize(BOTTOM_CONTAINER.getSize());
		if (DealershipClientEngine.isPotentialClient()) { //Will set the table with the potential client columns
			System.out.println("Creating potential client table");
			DealershipClientEngine.PT_ROW_DATA = new String[50][DealershipClientEngine.PT_COLUMN_DATA.length];
			DASHBOARD_LEAD_TABLE = new JTable(DealershipClientEngine.PT_ROW_DATA, DealershipClientEngine.PT_COLUMN_DATA);
		} else { //Sets the default lead columns
			
			System.out.println("Creating regular clients table");
			FieldManager.rowData = new String[50][10];
			DASHBOARD_LEAD_TABLE = new JTable(FieldManager.rowData, FieldManager.columnData);
			DASHBOARD_LEAD_TABLE.getColumn("ID").setPreferredWidth(15);
			DASHBOARD_LEAD_TABLE.getColumn("State").setPreferredWidth(15);
		}
		DASHBOARD_LEAD_TABLE.setRowHeight(30);
		DASHBOARD_LEAD_TABLE.setShowGrid(true);
		DASHBOARD_LEAD_TABLE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DASHBOARD_LEAD_TABLE.getSelectionModel().addListSelectionListener(this);
		TABLE_SCROLL_PANE = new JScrollPane(DASHBOARD_LEAD_TABLE);
		TABLE_SCROLL_PANE.setSize(BOTTOM_CONTAINER.getWidth(), (int) (BOTTOM_CONTAINER.getHeight() * 0.91));
		TABLE_SCROLL_PANE.setMaximumSize(TABLE_SCROLL_PANE.getSize());
		TABLE_SCROLL_PANE.setMinimumSize(TABLE_SCROLL_PANE.getSize());
		TEMP_BOTTOM.add(new PagesPanel(BOTTOM_CONTAINER, 0.09));
		TEMP_BOTTOM.add(TABLE_SCROLL_PANE);
		BOTTOM_CONTAINER.add(TEMP_BOTTOM, BorderLayout.CENTER);
		
	}
	
	
	/**
	 * Creates a notes pane that
	 * 
	 * @return fully populated JPanel that holds the information of the notes
	 */
	private JPanel createNotesPane(){ 
		createTempHolder();
		NOTES_TAB = new JPanel(new BorderLayout());
		BoxLayout box = new BoxLayout(NOTES_TAB,BoxLayout.Y_AXIS);
		NOTES_TAB.setLayout(box);
		NOTES_TAB.add(NOTES_BUTTON_PANEL);
		NOTES_TABLE = new JTable(FieldManager.notesRow,FieldManager.notesColumn);
		NOTES_TABLE.setShowHorizontalLines(true);
		NOTES_TABLE.setShowVerticalLines(true);
		NOTES_TABLE.setRowHeight(30);
		NOTES_TABLE.setEnabled(false);
		NOTES_SCROLL_PANE = new JScrollPane(NOTES_TABLE);
		NOTES_SCROLL_PANE.setPreferredSize(TEMP_NOTES_HOLDER.getSize());
		TEMP_NOTES_HOLDER.add(NOTES_SCROLL_PANE, BorderLayout.CENTER);
		NOTES_TAB.add(TEMP_NOTES_HOLDER, BorderLayout.CENTER);
		return NOTES_TAB;
		
	}
	
	private void putTogetherPanels(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(TOP_CONTAINER);
		this.add(BOTTOM_CONTAINER);
	}
	
	private void createNumCallButton(){
		INCREMENT_NUM_CALLS=new JButton();
        INCREMENT_NUM_CALLS.addActionListener(this);
        INCREMENT_NUM_CALLS.setText("CALLED");
	}
	
	private void createNumAnswerButton(){
		INCREMENT_NUM_ANSWER=new JButton();
        INCREMENT_NUM_ANSWER.addActionListener(this);
        INCREMENT_NUM_ANSWER.setText("ANSWERED");
        INCREMENT_NUM_ANSWER.setEnabled(false);
	}
	
	private void createNotesButton(){
		ADD_NOTES=new JButton();
		ADD_NOTES.addActionListener(this);
        ADD_NOTES.setText("ADD NOTES");
	}
	private void createSetApptButton(){
		SET_APPOINTMENT = new JButton("SET APPOINTMENT");
		SET_APPOINTMENT.addActionListener(this);
		SET_APPOINTMENT.setEnabled(false);
	}
	private void createDNCButton(){
		DNC_BUTTON = new JButton("DO NOT CALL");
		DNC_BUTTON.addActionListener(this);
	}
	
	public void setColors(){
		for (int x = 0; x < FieldManager.leadColor.length; x++) {
		DASHBOARD_LEAD_TABLE.getColumnModel().getColumn(x).setCellRenderer(new TableRenderer());
		}
		DASHBOARD_LEAD_TABLE.repaint();
	}
	
	private void createButtonsPanel(){
		NOTES_BUTTON_PANEL = new JPanel();
		NOTES_BUTTON_PANEL.setSize(TOP_CONTAINER.getWidth(),(int) (TOP_CONTAINER.getHeight()*0.2));
		NOTES_BUTTON_PANEL.setMaximumSize(NOTES_BUTTON_PANEL.getSize());
		NOTES_BUTTON_PANEL.setMinimumSize(TOP_CONTAINER.getSize());
		createNumCallButton();
		createNumAnswerButton();
		createNotesButton();
		createSetApptButton();
		createDNCButton();
		NOTES_BUTTON_PANEL.add(INCREMENT_NUM_CALLS);
		NOTES_BUTTON_PANEL.add(INCREMENT_NUM_ANSWER);
		NOTES_BUTTON_PANEL.add(ADD_NOTES);
		NOTES_BUTTON_PANEL.add(SET_APPOINTMENT);
		NOTES_BUTTON_PANEL.add(DNC_BUTTON);
		
	}
	
	private void createTempHolder(){
		TEMP_NOTES_HOLDER = new JPanel(new BorderLayout());
		TEMP_NOTES_HOLDER.setSize(TOP_CONTAINER.getWidth(),(int) (TOP_CONTAINER.getHeight() * 0.8));
		TEMP_NOTES_HOLDER.setMaximumSize(TEMP_NOTES_HOLDER.getSize());
		TEMP_NOTES_HOLDER.setMinimumSize(TOP_CONTAINER.getSize());
	}
	
	//Replaces the panels with updated panels
	public void showLeadInfoPanel(){
		DB_TABBED_PANE.setComponentAt(0, new ClientInfo());
		DB_TABBED_PANE.setComponentAt(1, createNotesPane());
		DB_TABBED_PANE.setComponentAt(2, new ClientHistory());
		DB_TABBED_PANE.validate();
		DB_TABBED_PANE.repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==INCREMENT_NUM_CALLS){
			FieldManager.incrementNumTimesCalled();
			INCREMENT_NUM_ANSWER.setEnabled(true);
			Session.incrementSessionCalls();
			try {
				FieldManager.populateTable(FieldManager.currentPage);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			GUIManager.DASHBOARD_PANEL.setColors();
		}
		
		
		if(e.getSource()==INCREMENT_NUM_ANSWER){ //Answered Button
			FieldManager.incrementNumTimesAnswered(); //Increments the number of times answered
			INCREMENT_NUM_ANSWER.setEnabled(false); //sets enabled false to prevent double clicks
			SET_APPOINTMENT.setEnabled(true); //Unlocks the appointment set button
			try {
				FieldManager.populateTable(FieldManager.currentPage);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			GUIManager.DASHBOARD_PANEL.setColors();

		}
		
		if(e.getSource()==ADD_NOTES){ //This button will open a JOptionPane to add messages to servers
			String NOTE_INPUT = JOptionPane.showInputDialog("Add Note");
			if(NOTE_INPUT != null){
			FieldManager.addNote(NOTE_INPUT);
			FieldManager.displayClientInfo();
			showLeadInfoPanel();
			NOTES_TAB.validate();
			NOTES_TAB.repaint();

			}
			else
				JOptionPane.showMessageDialog(null, "Message was canceled");
			
		}
		if(e.getSource() == SET_APPOINTMENT){
			AddAppointmentScreen appt = new AddAppointmentScreen();
			SET_APPOINTMENT.setEnabled(false);
			Session.incrementSessionAppts();
			Session.incrementTotalAppts();
			try {
				FieldManager.populateTable(FieldManager.currentPage);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	GUIManager.DASHBOARD_PANEL.setColors();

		}
		if(e.getSource() == DNC_BUTTON){
			int confirmButton = JOptionPane.showConfirmDialog(null, "Are you sure you want client to never be reached again?", "Do Not Call", JOptionPane.YES_NO_OPTION);
			if(confirmButton == JOptionPane.YES_OPTION){
				FieldManager.setDNC();
				try {
					FieldManager.populateTable(FieldManager.currentPage);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    	GUIManager.DASHBOARD_PANEL.setColors();

			}
			else
				JOptionPane.showMessageDialog(null, "Do not call was not set");
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(DASHBOARD_LEAD_TABLE.getSelectedRow() > -1){
			System.out.println("Row Selected: " + DASHBOARD_LEAD_TABLE.getSelectedRow());
			Object temp = DASHBOARD_LEAD_TABLE.getValueAt(DASHBOARD_LEAD_TABLE.getSelectedRow(), 0);
			String temp2 = (String) temp;
			int foo = Integer.parseInt(temp2);
			FieldManager.setCURRENT_LEAD_ID(foo);
			FieldManager.displayClientInfo();
			INCREMENT_NUM_ANSWER.setEnabled(false); //sets enabled false to prevent double clicks
			showLeadInfoPanel();
		}
		
	}

}
