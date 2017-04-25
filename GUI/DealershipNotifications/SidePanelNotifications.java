package GUI.DealershipNotifications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import Engine.DNEngine;

public class SidePanelNotifications extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private int SPN_WIDTH;
	private int SPN_HEIGHT;
	private Dimension SPN_SIZE;
	//private final static int INTERVAL = 100000;

	private JTable NOTIFICATIONS;
	private JScrollPane NOTIFICATIONS_SCROLL;
	
	public SidePanelNotifications(JPanel panel){
		setPanelSize(panel);
		createComponents();
		addComponents();
	}
	
	private void setPanelSize(JPanel panel){
		SPN_WIDTH = panel.getWidth();
		SPN_HEIGHT = panel.getHeight();
		SPN_SIZE = new Dimension(SPN_WIDTH,SPN_HEIGHT);
		this.setSize(SPN_SIZE);
		this.setMaximumSize(SPN_SIZE);
		this.setMinimumSize(SPN_SIZE);
	}
	
	private void createComponents(){

		//Timer timer = new Timer(INTERVAL, this); 
		//timer.start();
		
		DNEngine.gatherNotifications();
		NOTIFICATIONS = new JTable(DNEngine.sidePanelNotificationsRow,DNEngine.sidePanelNotificationsColumn);
		NOTIFICATIONS.setRowHeight(30);
		NOTIFICATIONS_SCROLL = new JScrollPane(NOTIFICATIONS);
	}
	
	private void addComponents(){
		this.setLayout(new BorderLayout());
		this.add(NOTIFICATIONS_SCROLL, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.removeAll();
		DNEngine.gatherNotifications();
		createComponents();
		addComponents();
		this.revalidate();
		this.repaint();
	}
	

}
