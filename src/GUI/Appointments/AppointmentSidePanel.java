
package GUI.Appointments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.GUIManager;
import GUI.DealershipNotifications.SidePanelNotifications;



public class AppointmentSidePanel extends JPanel implements ActionListener {

	private int ASP_WIDTH;
	private int ASP_HEIGHT;
	private Dimension ASP_SIZE;
	
	
	
	private JPanel ASP_TOP_CONTAINER;
	private JPanel ASP_BOTTOM_CONTAINER;
	private JButton[] ASP_BUTTONS = new JButton[3];
	private JButton ASP_DASHBOARD_BUTTON;
	private JButton ASP_TEMP_BUTTON; //DONT KNOW WHAT THIS BUTTON WILL DO YET CHANGE NAME LATER!!!!! <---------------------
	private JButton ASP_LOGOUT_BUTTON;
	
	private GridBagConstraints GBC;
	
	public AppointmentSidePanel(){
		GBC = new GridBagConstraints();
		setPanelSize();
		createContainers();
	}
	
	private void setPanelSize(){
		ASP_WIDTH = (int) (GUIManager.getScreenWidth() * 0.2);
		System.out.println(ASP_WIDTH);
		ASP_HEIGHT = GUIManager.getScreenHeight();
		ASP_SIZE = new Dimension(ASP_WIDTH,ASP_HEIGHT);
		this.setSize(ASP_SIZE);
	}
	
	private void createContainers(){
		ASP_TOP_CONTAINER = new JPanel();
		ASP_TOP_CONTAINER.setSize(ASP_WIDTH,ASP_HEIGHT/2);
		ASP_TOP_CONTAINER.setMaximumSize(ASP_TOP_CONTAINER.getSize());
		ASP_TOP_CONTAINER.setMinimumSize(ASP_TOP_CONTAINER.getSize());
		createTopComponents();
		
		ASP_BOTTOM_CONTAINER = new JPanel();
		ASP_BOTTOM_CONTAINER.setSize(ASP_WIDTH,ASP_HEIGHT/2);
		ASP_BOTTOM_CONTAINER.setMaximumSize(ASP_BOTTOM_CONTAINER.getSize());
		ASP_BOTTOM_CONTAINER.setMinimumSize(ASP_BOTTOM_CONTAINER.getSize());
		ASP_BOTTOM_CONTAINER.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		createBottomComponents();
		
		putTogetherPanels();
	}
	private void createTopComponents(){
		ASP_TOP_CONTAINER.setLayout(new GridBagLayout());
		createButtons();
		
	}
	private void createBottomComponents(){
		ASP_BOTTOM_CONTAINER.setLayout(new BorderLayout());
		ASP_BOTTOM_CONTAINER.add(new SidePanelNotifications(ASP_BOTTOM_CONTAINER), BorderLayout.CENTER);
	}
	
	private void createButtons(){
		ASP_DASHBOARD_BUTTON = new JButton("Dashboard");
		ASP_BUTTONS[0] = ASP_DASHBOARD_BUTTON;
		
		ASP_TEMP_BUTTON = new JButton();
		ASP_BUTTONS[1] = ASP_TEMP_BUTTON;
		
		ASP_LOGOUT_BUTTON = new JButton("Logout");
		ASP_BUTTONS[2] = ASP_LOGOUT_BUTTON;
		ASP_BUTTONS[0].setFont(new Font("Serif", Font.BOLD,20));
		ASP_BUTTONS[1].setFont(new Font("Serif", Font.BOLD,20));
		ASP_BUTTONS[2].setFont(new Font("Serif", Font.BOLD,20));
		
		for(int a = 0; a< ASP_BUTTONS.length; a++){
			ASP_BUTTONS[a].addActionListener(this);
			setGBC(a);
			ASP_TOP_CONTAINER.add(ASP_BUTTONS[a],GBC);
		}
	}
	
	private void putTogetherPanels(){
		BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.add(ASP_TOP_CONTAINER);
		this.add(ASP_BOTTOM_CONTAINER);
	}
	
	private void setGBC(int x){
		GBC.gridx = 0;
		GBC.gridy = x;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.fill = GridBagConstraints.BOTH;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ASP_BUTTONS[0]){
			GUIManager.switchPanels(GUIManager.SIDE_PANEL, GUIManager.DASHBOARD_PANEL);
		}
		if(e.getSource() == ASP_BUTTONS[2]){
			GUIManager.logout();
		}
		
	}

}
