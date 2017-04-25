package GUI.Portfolio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import GUI.GUIManager;
import GUI.Email.EmailMainPanel;
import GUI.Email.EmailSidePanel;

public class PortfolioSidePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int PSP_WIDTH;
	private int PSP_HEIGHT;
	private Dimension PSP_SIZE;
	
	private JPanel PORTFOLIO_PHOTO;
	private JPanel PORTFOLIO_MENU_BUTTONS;
	private JPanel PORTFOLIO_NOTIFICATIONS;
	private JButton DASHBOARD_BUTTON;
	private JButton EMAIL_BUTTON;
	private JButton LOGOUT_BUTTON;
	private JTextArea NOTIFICATIONS;
	private JButton[] PSP_BUTTONS = new JButton[3];
	private GridBagConstraints GBC;

	
	public PortfolioSidePanel(){
		setPanelSize();
		createContainers();
		putPanelsTogether();
	}
	
	private void setPanelSize(){
		PSP_WIDTH = (int) (GUIManager.getScreenWidth() * 0.2);
		PSP_HEIGHT = GUIManager.getScreenHeight();
		PSP_SIZE = new Dimension(PSP_WIDTH,PSP_HEIGHT);
		this.setSize(PSP_SIZE);
		this.setMaximumSize(PSP_SIZE);
		this.setMinimumSize(PSP_SIZE);
	}
	
	private void createContainers(){
		PORTFOLIO_PHOTO = new PortfolioPhoto();
		PORTFOLIO_PHOTO.setSize(PSP_WIDTH, PSP_HEIGHT/3);
		PORTFOLIO_PHOTO.setMaximumSize(PORTFOLIO_PHOTO.getSize());
		PORTFOLIO_PHOTO.setMinimumSize(PORTFOLIO_PHOTO.getSize());
		
		PORTFOLIO_MENU_BUTTONS = new JPanel();
		PORTFOLIO_MENU_BUTTONS.setSize(PSP_WIDTH,PSP_HEIGHT/3);
		PORTFOLIO_MENU_BUTTONS.setMaximumSize(PORTFOLIO_MENU_BUTTONS.getSize());
		PORTFOLIO_MENU_BUTTONS.setMinimumSize(PORTFOLIO_MENU_BUTTONS.getSize());
		populateMiddlePanel();
		
		PORTFOLIO_NOTIFICATIONS = new JPanel();
		PORTFOLIO_NOTIFICATIONS.setSize(PSP_WIDTH,PSP_HEIGHT/3);
		PORTFOLIO_NOTIFICATIONS.setMaximumSize(PORTFOLIO_NOTIFICATIONS.getSize());
		PORTFOLIO_NOTIFICATIONS.setMinimumSize(PORTFOLIO_NOTIFICATIONS.getSize());
		populateBottomPanel();
	}
	
	private void populateMiddlePanel(){
		PORTFOLIO_MENU_BUTTONS.setLayout(new GridBagLayout());
		DASHBOARD_BUTTON = new JButton("Dashboard");
		PSP_BUTTONS[0] = DASHBOARD_BUTTON;
		EMAIL_BUTTON = new JButton("Email");
		PSP_BUTTONS[1] = EMAIL_BUTTON;
		LOGOUT_BUTTON = new JButton("Logout");
		PSP_BUTTONS[2] = LOGOUT_BUTTON;
		GBC = new GridBagConstraints();
		
		for(int a = 0; a < PSP_BUTTONS.length; a++ ){
			PSP_BUTTONS[a].addActionListener(this);
			setGBC(a);
			PORTFOLIO_MENU_BUTTONS.add(PSP_BUTTONS[a], GBC);
		}
	}
	
	private void populateBottomPanel(){
		PORTFOLIO_NOTIFICATIONS.setLayout(new BorderLayout());
		NOTIFICATIONS = new JTextArea();
		PORTFOLIO_NOTIFICATIONS.add(NOTIFICATIONS, BorderLayout.CENTER);
	}
	
	private void putPanelsTogether(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(PORTFOLIO_PHOTO);
		this.add(PORTFOLIO_MENU_BUTTONS);
		this.add(PORTFOLIO_NOTIFICATIONS);
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
		if(e.getSource() == PSP_BUTTONS[0]){
			GUIManager.switchPanels(GUIManager.SIDE_PANEL, GUIManager.DASHBOARD_PANEL);
		}
		if(e.getSource() == PSP_BUTTONS[1]){
			//GUIManager.switchPanels(new EmailSidePanel(), new EmailMainPanel());
			JOptionPane.showMessageDialog(null, "Under Construction");
		}
		
		if(e.getSource() == PSP_BUTTONS[2]){
			GUIManager.logout();
		}
		
	}
}
