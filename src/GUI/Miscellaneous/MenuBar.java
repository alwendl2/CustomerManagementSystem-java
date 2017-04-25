package GUI.Miscellaneous;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GUI.ClientProfile.ClientProfile;
import GUI.DealershipNotifications.DealerNotifications;
import GUI.Preferences.Settings;

public class MenuBar extends JMenuBar implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	
	private JMenu FILE_MENU;
	private JMenu ABOUT_MENU;
	private JMenu EDIT_MENU;
	private JMenuItem PREFERENCES_MENU_ITEM;
	private JMenuItem DEALER_NOTIFICATIONS;
	private JMenuItem CLIENT_LOOKUP;
	
	
	
	public MenuBar(){
		createMenus();
		putTogetherMenuBar();
	
	}
	
	private void createMenus(){
		FILE_MENU = new JMenu("File");
		addFileMenuComponents();
		
		EDIT_MENU = new JMenu("Edit");
		
		ABOUT_MENU = new JMenu("About");
		
	}
	
	private void addFileMenuComponents(){
		PREFERENCES_MENU_ITEM = new JMenuItem("Preferences");
		PREFERENCES_MENU_ITEM.addActionListener(this);
		DEALER_NOTIFICATIONS = new JMenuItem("Dealer Notifications");
		DEALER_NOTIFICATIONS.addActionListener(this);
		CLIENT_LOOKUP = new JMenuItem("Client Lookup");
		CLIENT_LOOKUP.addActionListener(this);
		
		
		
		FILE_MENU.add(PREFERENCES_MENU_ITEM);
		FILE_MENU.add(CLIENT_LOOKUP);
		FILE_MENU.add(DEALER_NOTIFICATIONS);
	}
	
	private void putTogetherMenuBar(){
		this.add(FILE_MENU);
		this.add(EDIT_MENU);
		this.add(ABOUT_MENU);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == PREFERENCES_MENU_ITEM){
			Settings x = new Settings();
		}
		if(e.getSource() == CLIENT_LOOKUP){
			ClientProfile cp = new ClientProfile();
		}
		if(e.getSource() == DEALER_NOTIFICATIONS){
			DealerNotifications not = new DealerNotifications();
		}
		
	}

}
