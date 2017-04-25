
package GUI.Appointments;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Engine.FieldManager;
import GUI.GUIManager;

public class AddAppointmentScreen extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private final String[] hours = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private final String[] mins = {"00","15","30","45"};
	private final String[] ampm = {"AM","PM"};
	double WIDTH_RATIO = 0.5; //Ratio this to main frame -- WIDTH
	double HEIGHT_RATIO = 0.4; //Ratio this to main frame -- HEIGHT
	double PANEL_RATIO = 0.2; //Ratio of setting the JPanels to fit
	int POPUP_WIDTH; //Width of the popup
	int POPUP_HEIGHT; //Height of the popup
	Dimension POPUP_SIZE; //Dimension (WIDTH, HEIGHT)
	
	Date selectedDate;
	Timestamp castedDate;
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	JComboBox HOURS;
	JComboBox MIN;
	JComboBox AMPM;
	
	
	JPanel TOP_PANEL; //Panel that will hold the JLabel for the LEAD NAME
	JPanel CENTER_PANEL; //Panel that holds the Date and Time select
	JPanel BOTTOM_PANEL; //Panel that holds the add note for appointment
	
	JLabel LEAD_NAME; //Label that displays the name of the lead 
	JLabel CENTER_PANEL_DATE; //Label for the date selection
	JLabel CENTER_PANEL_TIME; //Label for the time selection
	JTextField CENTER_PANEL_DATE_SELECT; //Text field for the date selection
	JTextField CENTER_PANEL_TIME_SELECT; //Text field fot the time selection
	JLabel BOTTOM_PANEL_NOTE_LABEL; //Label for the appt notes
	JTextArea BOTTOM_PANEL_NOTES; //JTextArea for the appt notes
	JButton BOTTOM_PANEL_SUBMIT; //Submit button to submit the appt
	JTextField field; //temp input for notes -- GET TEXT AREA TO SHOW
	
	String APPOINTMENT_NOTE; //Note for the appointment
	
	/* ----- DEFAULT CONSTRUCTOR ----- */
	public AddAppointmentScreen(){
		setSizes();
		createPanels();
		addPanels();
		finalizePopUp();
	}
	
	
	private void setSizes(){ //Uses GUIManager to retrieve size of the screen and then adjusts using ratios
		POPUP_WIDTH = (int) (GUIManager.getScreenWidth() * WIDTH_RATIO);
		POPUP_HEIGHT = (int) (GUIManager.getScreenHeight() * HEIGHT_RATIO);
		POPUP_SIZE = new Dimension(POPUP_WIDTH, POPUP_HEIGHT);
		this.setSize(POPUP_SIZE); //sets the size of the popup screen
	}

	private void finalizePopUp(){
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private void createPanels(){
		TOP_PANEL = new JPanel();
		TOP_PANEL.setSize(POPUP_WIDTH, (int) (POPUP_HEIGHT * PANEL_RATIO));
		TOP_PANEL.setMaximumSize(TOP_PANEL.getSize());
		TOP_PANEL.setMinimumSize(TOP_PANEL.getSize());
		//TOP_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		populateTopPanel();
		
		CENTER_PANEL = new JPanel();
		CENTER_PANEL.setSize(POPUP_WIDTH, (int) (POPUP_HEIGHT * PANEL_RATIO));
		CENTER_PANEL.setMaximumSize(CENTER_PANEL.getSize());
		CENTER_PANEL.setMinimumSize(CENTER_PANEL.getSize());
		//CENTER_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		populateCenterPanel();
		
		BOTTOM_PANEL = new JPanel();
		BOTTOM_PANEL.setSize(POPUP_WIDTH, (int) (POPUP_HEIGHT * PANEL_RATIO * 2));
		BOTTOM_PANEL.setMaximumSize(BOTTOM_PANEL.getSize());
		BOTTOM_PANEL.setMinimumSize(BOTTOM_PANEL.getSize());
		//BOTTOM_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		populateBottomPanel();
	}
	
	private void populateTopPanel(){
		LEAD_NAME = new JLabel();
		LEAD_NAME.setText("Set Appointment For: " + FieldManager.getLEAD_NAME_FIRST() + " " + FieldManager.getLEAD_NAME_LAST());
		TOP_PANEL.add(LEAD_NAME);
	}
	private void populateCenterPanel(){
		model = new UtilDateModel();
		Properties x = new Properties();
		DateFormatter y= new DateFormatter();
		datePanel = new JDatePanelImpl(model,x);
		datePicker = new JDatePickerImpl(datePanel,y);
		
		HOURS= new JComboBox<String>(hours);
		MIN= new JComboBox<String>(mins);
		AMPM= new JComboBox<String>(ampm);
		
		CENTER_PANEL_DATE = new JLabel("Date: ");
		//CENTER_PANEL_TIME = new JLabel("Time: ");
		//CENTER_PANEL_DATE_SELECT = new JTextField(10);
		//CENTER_PANEL_TIME_SELECT = new JTextField(10);
		CENTER_PANEL.add(CENTER_PANEL_DATE);
		CENTER_PANEL.add(datePicker);
		CENTER_PANEL.add(HOURS);
		CENTER_PANEL.add(MIN);
		CENTER_PANEL.add(AMPM);
		//CENTER_PANEL.add(CENTER_PANEL_TIME);
		//CENTER_PANEL.add(CENTER_PANEL_TIME_SELECT);
	}
	private void populateBottomPanel(){
		BOTTOM_PANEL_NOTE_LABEL = new JLabel("Appointment Note: ");
		BOTTOM_PANEL_NOTES = new JTextArea(5,10);
		field = new JTextField(30);
		
		BOTTOM_PANEL_SUBMIT = new JButton("Submit");
		BOTTOM_PANEL_SUBMIT.addActionListener(this);
		
		GUIManager.createGridBagLayout();
		GridBagLayout GBL = GUIManager.getGridBagLayout();
		GUIManager.createGBC();
		GridBagConstraints GBC = GUIManager.getGBC();
		BOTTOM_PANEL.setLayout(GBL);
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.FIRST_LINE_START;
		BOTTOM_PANEL.add(BOTTOM_PANEL_NOTE_LABEL, GBC);
		
		GBC.gridy = 1;
		GBC.anchor = GridBagConstraints.CENTER;
		BOTTOM_PANEL.add(field, GBC);
		
		GBC.gridy = 2;
		GBC.anchor = GridBagConstraints.LAST_LINE_END;
		BOTTOM_PANEL.add(BOTTOM_PANEL_SUBMIT, GBC);

		
	}
	
	private void addPanels(){
		BoxLayout box = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
		getContentPane().setLayout(box);
		this.add(TOP_PANEL);
		this.add(CENTER_PANEL);
		this.add(BOTTOM_PANEL);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String hour = (String) HOURS.getSelectedItem();
		String min = (String) MIN.getSelectedItem();
		String ampm2 = (String) AMPM.getSelectedItem();
		if(ampm2.equals("PM")){
			hour =""+(Integer.parseInt(hour)+12);
		}
		selectedDate= (Date) datePicker.getModel().getValue();
		castedDate=new Timestamp(selectedDate.getTime());
		castedDate.setHours(Integer.parseInt(hour));
		castedDate.setMinutes(Integer.parseInt(min));
		
		
		
		APPOINTMENT_NOTE = field.getText();
		FieldManager.addAppointment(APPOINTMENT_NOTE,castedDate,ampm2);
		this.dispose();
		
	}

}
