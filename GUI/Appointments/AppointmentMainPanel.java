
package GUI.Appointments;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Engine.FieldManager;
import GUI.GUIManager;
import GUI.Tabs.ClientHistory;
import GUI.Tabs.ClientInfo;

public class AppointmentMainPanel extends JPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	private int AS_WIDTH; //Width of the Panel -- SCREEN WIDTH X 0.7
	private int AS_HEIGHT; //Height of the Panel -- SCREEN HEIGHT
	private Dimension AS_SIZE; //Dimension of the Panel -- (SCREEN WIDTH, SCREEN HEIGHT)
	
	private JPanel AS_TOP_CONTAINER; //Top Panel that holds the top components
	private JPanel AS_BOTTOM_CONTAINER; //Bottom Panel that holds the bottom components
	private JTabbedPane AS_TABBED_PANE; //Tabbed Pane that holds the 3 panels: CLIENT INFO, CLIENT HISORY, APPT SETTINGS
	private JTable AS_LEAD_TABLE; //JTable that holds the appointments for the dealership
	private JScrollPane AS_LEAD_TABLE_SCROLL_PANE; //Scroll Pane that holds the table
	private JPanel AS_APPT_SETTINGS; ///Panel that holds the appointment settings -- REFER TO NOTES FOR LAYOUT

	
	
	/* ----- DEFAULT CONSTRUCTOR ----- */
	public AppointmentMainPanel(){
		setPanelSize();
		createContainers();
		
	}
	
	private void setPanelSize(){ //Gathers the screen size and sets the panels size according to the screen size
		AS_WIDTH = (int) (GUIManager.getScreenWidth() * 0.8);
		System.out.println(AS_WIDTH);
		AS_HEIGHT = GUIManager.getScreenHeight();
		AS_SIZE = new Dimension(AS_WIDTH, AS_HEIGHT);
		this.setSize(AS_SIZE); //sets the size of the panel
		this.setMaximumSize(AS_SIZE); //Ensures that panel wont change size
		this.setMinimumSize(AS_SIZE); //Ensures that panel wont change size
	}
	
	private void createContainers(){
		AS_TOP_CONTAINER = new JPanel();
		AS_TOP_CONTAINER.setSize(this.getWidth(),this.getHeight()/2);
		AS_TOP_CONTAINER.setMaximumSize(AS_TOP_CONTAINER.getSize());
		AS_TOP_CONTAINER.setMinimumSize(AS_TOP_CONTAINER.getSize());
		createTopComponents();
		
		AS_BOTTOM_CONTAINER = new JPanel();
		AS_BOTTOM_CONTAINER.setSize(this.getWidth(),this.getHeight()/2);
		AS_BOTTOM_CONTAINER.setMaximumSize(AS_BOTTOM_CONTAINER.getSize());
		AS_BOTTOM_CONTAINER.setMinimumSize(AS_BOTTOM_CONTAINER.getSize());
		createBottomComponents();
		
		putTogetherPanels();
	}
	
	private void createTopComponents(){
		AS_TOP_CONTAINER.setLayout(new BorderLayout());
		AS_TABBED_PANE = new JTabbedPane();
		AS_TABBED_PANE.addTab("Client Info", new ClientInfo());
		AS_TABBED_PANE.addTab("Client History", new ClientHistory());
		createApptSettings();
		AS_TABBED_PANE.addTab("Appointment Settings", createApptSettings());
		
		AS_TOP_CONTAINER.add(AS_TABBED_PANE, BorderLayout.CENTER);
	}
	private void createBottomComponents(){
		AS_BOTTOM_CONTAINER.setLayout(new BorderLayout());
		FieldManager.setAppointmentArray();
		AS_LEAD_TABLE = new JTable(FieldManager.apptRow,FieldManager.apptColumn);
		AS_LEAD_TABLE.setShowGrid(true);
		AS_LEAD_TABLE.getSelectionModel().addListSelectionListener(this);
		AS_LEAD_TABLE_SCROLL_PANE = new JScrollPane(AS_LEAD_TABLE);
		AS_LEAD_TABLE_SCROLL_PANE.setSize(AS_BOTTOM_CONTAINER.getWidth()-35, AS_BOTTOM_CONTAINER.getHeight()-35);
		AS_LEAD_TABLE_SCROLL_PANE.setMaximumSize(AS_LEAD_TABLE_SCROLL_PANE.getSize());
		AS_LEAD_TABLE_SCROLL_PANE.setMinimumSize(AS_LEAD_TABLE_SCROLL_PANE.getSize());
		
		AS_BOTTOM_CONTAINER.add(AS_LEAD_TABLE_SCROLL_PANE,BorderLayout.CENTER);
	}
	
	
	
	private JPanel createApptSettings(){
		AS_APPT_SETTINGS = new JPanel();
		AS_APPT_SETTINGS.setSize(AS_TOP_CONTAINER.getWidth()-35,AS_TOP_CONTAINER.getHeight()-35);
		AS_APPT_SETTINGS.setMaximumSize(AS_APPT_SETTINGS.getSize());
		AS_APPT_SETTINGS.setMinimumSize(AS_APPT_SETTINGS.getSize());
		return AS_APPT_SETTINGS;
	}
	
	private void putTogetherPanels(){
		BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.add(AS_TOP_CONTAINER);
		this.add(AS_BOTTOM_CONTAINER);
	}
	
	private void showLeadInfoPanel(){
		AS_TABBED_PANE.setComponentAt(0, new ClientInfo());
		AS_TABBED_PANE.setComponentAt(1, new ClientHistory());
		AS_TABBED_PANE.revalidate();
		AS_TABBED_PANE.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(AS_LEAD_TABLE.getSelectedRow() > -1){
			//System.out.println("Row Selected: "+AS_LEAD_TABLE.getSelectedRow());
			Object temp = AS_LEAD_TABLE.getValueAt(AS_LEAD_TABLE.getSelectedRow(), 0);
			String temp2 = (String) temp;
			int foo = Integer.parseInt(temp2);
			FieldManager.setCURRENT_LEAD_ID(foo);
			FieldManager.displayClientInfo();
			showLeadInfoPanel();
		}
		
	}
	
}
