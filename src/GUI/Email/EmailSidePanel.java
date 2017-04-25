package GUI.Email;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.GUIManager;
import GUI.DealershipNotifications.SidePanelNotifications;
import GUI.Portfolio.PortfolioMain;
import GUI.Portfolio.PortfolioPhoto;
import GUI.Portfolio.PortfolioSidePanel;

public class EmailSidePanel extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	
	private int WIDTH;
	private int HEIGHT;
	private Dimension SIZE;
	
	private PortfolioPhoto EMAIL_PHOTO;
	private JPanel EMAIL_MENU_BUTTONS;
	private JPanel EMAIL_NOTIFICATIONS;
	private JButton[] BUTTONS = new JButton[3];
	private GridBagConstraints GBC;
	
	public EmailSidePanel(){
		setPanelSize();
		createContainers();
		putPanelTogether();
	}
	
	private void setPanelSize(){
		WIDTH = (int) (GUIManager.getScreenWidth() * 0.2);
		HEIGHT = GUIManager.getScreenHeight();
		SIZE = new Dimension(WIDTH,HEIGHT);
		this.setSize(SIZE);
		this.setMaximumSize(SIZE);
		this.setMinimumSize(SIZE);
	}
	
	private void createContainers(){
		EMAIL_PHOTO = new PortfolioPhoto();
		EMAIL_PHOTO.setSize(WIDTH,HEIGHT/3);
		EMAIL_PHOTO.setMaximumSize(EMAIL_PHOTO.getSize());
		EMAIL_PHOTO.setMinimumSize(EMAIL_PHOTO.getSize());
		
		EMAIL_MENU_BUTTONS = new JPanel(new GridBagLayout());
		EMAIL_MENU_BUTTONS.setSize(WIDTH,HEIGHT/3);
		EMAIL_MENU_BUTTONS.setMaximumSize(EMAIL_MENU_BUTTONS.getSize());
		EMAIL_MENU_BUTTONS.setMinimumSize(EMAIL_MENU_BUTTONS.getSize());
		populateMiddlePanel();
		
		EMAIL_NOTIFICATIONS = new JPanel(new BorderLayout());
		EMAIL_NOTIFICATIONS.setSize(WIDTH,HEIGHT/3);
		EMAIL_NOTIFICATIONS.setMaximumSize(EMAIL_NOTIFICATIONS.getSize());
		EMAIL_NOTIFICATIONS.setMinimumSize(EMAIL_NOTIFICATIONS.getSize());
		populateBottomPanel();
	}
	
	private void populateMiddlePanel(){
		GBC = new GridBagConstraints();
		for (int a = 0; a<BUTTONS.length; a++) {
			BUTTONS[a] = new JButton();
			BUTTONS[a].addActionListener(this);
		}
		BUTTONS[0].setText("Portfolio");
		BUTTONS[1].setText("Dashboard");
		BUTTONS[2].setText("Logout");
		
		for (int b = 0; b < BUTTONS.length; b++) {
			setGBC(b);
			EMAIL_MENU_BUTTONS.add(BUTTONS[b], GBC);
		}	
	}
	
	
	private void populateBottomPanel(){
		EMAIL_NOTIFICATIONS.add(new SidePanelNotifications(EMAIL_NOTIFICATIONS), BorderLayout.CENTER);
	}
	
	
	private void putPanelTogether(){
		BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.add(EMAIL_PHOTO);
		this.add(EMAIL_MENU_BUTTONS);
		this.add(EMAIL_NOTIFICATIONS);
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
		if (e.getSource() == BUTTONS[0]) {
			GUIManager.switchPanels(new PortfolioSidePanel(), new PortfolioMain());
		}
		if (e.getSource() == BUTTONS[1]) {
			GUIManager.switchPanels(GUIManager.DASHBOARD_PANEL, GUIManager.SIDE_PANEL);
		}
		if (e.getSource() == BUTTONS[2]) {
			GUIManager.logout();
		}
		
	}

}
