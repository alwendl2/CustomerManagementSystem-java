package GUI.Preferences;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Engine.ConfigManager;

public class NetworkPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JLabel IP_ADDRESS_LABEL; //JLabel for the IP Address input 
	private JLabel PORT_LABEL; //JLabel for the port number
	private JLabel USERNAME_LABEL; //JLabel for the username number
	private JLabel PASSWORD_LABEL; //JLabel for the password number
	private JTextField IP_ADDRESS_INPUT; //JTextField that will allow user to input ip adress
	private JTextField PORT_INPUT; //JTextField that will allow the user to input the port number
	private JTextField USERNAME_INPUT; //JTextField that will allow the user to input the username for the server
	private JTextField PASSWORD_INPUT; //JTextField that will allow the user to input the password for the server
	private JButton APPLY_BUTTON; //JButton that will save the settings into a conf.txt
	private JPanel[] CONTAINERS = new JPanel[5];
	
	/* ----- DEFUALT CONSTRUCTOR ----- */
	public NetworkPanel(){
		initComponents();
		createContainers();
	}
	
	private void initComponents(){ //Initializes all components in the Netowrk Panel
		IP_ADDRESS_LABEL = new JLabel("IP Address: ");
		PORT_LABEL = new JLabel("Port Number: ");
		USERNAME_LABEL = new JLabel("Server Username: ");
		PASSWORD_LABEL = new JLabel("Server Password: ");
		IP_ADDRESS_INPUT = new JTextField(12);
		PORT_INPUT = new JTextField(12);
		USERNAME_INPUT = new JTextField(12);
		PASSWORD_INPUT = new JTextField(12);
		APPLY_BUTTON = new JButton("Apply");
	}
	
	private void createContainers(){ //Takes all initiated components and puts them into their Containers
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int a = 0;a<CONTAINERS.length;a++){
			CONTAINERS[a] = new JPanel();
			if(a<=3)
				CONTAINERS[a].setLayout(new FlowLayout(FlowLayout.LEFT));
			else
				CONTAINERS[a].setLayout(new FlowLayout(FlowLayout.RIGHT));
		}
		CONTAINERS[0].add(IP_ADDRESS_LABEL);
		CONTAINERS[0].add(IP_ADDRESS_INPUT);
		CONTAINERS[1].add(PORT_LABEL);
		CONTAINERS[1].add(PORT_INPUT);
		CONTAINERS[2].add(USERNAME_LABEL);
		CONTAINERS[2].add(USERNAME_INPUT);
		CONTAINERS[3].add(PASSWORD_LABEL);
		CONTAINERS[3].add(PASSWORD_INPUT);
		CONTAINERS[4].add(APPLY_BUTTON);
		for(int a = 0; a<CONTAINERS.length;a++){
			this.add(CONTAINERS[a]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == APPLY_BUTTON){
			ConfigManager.updateConfigFile();
		}
		
	}

}
