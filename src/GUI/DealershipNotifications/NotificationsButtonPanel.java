package GUI.DealershipNotifications;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Engine.DNEngine;

public class NotificationsButtonPanel extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	
	private int NBP_WIDTH;
	private int NBP_HEIGHT;
	private Dimension NBP_SIZE;
	
	private JButton ADD_BUTTON;
	private JButton EDIT_BUTTON;
	private JButton[] BUTTON = new JButton[2];
	
	public NotificationsButtonPanel(){
		setPanelSize();
		createButtons();
		addButtons();
	}
	
	private void setPanelSize(){
		NBP_WIDTH = DealerNotifications.getDN_Width();
		NBP_HEIGHT = (int) (DealerNotifications.getDN_Height() * 0.2);
		NBP_SIZE = new Dimension(NBP_WIDTH,NBP_HEIGHT);
		this.setSize(NBP_SIZE);
		this.setMaximumSize(NBP_SIZE);
		this.setMinimumSize(NBP_SIZE);
	}
	
	private void createButtons(){
		ADD_BUTTON = new JButton("Add");
		BUTTON[0] = ADD_BUTTON;
		EDIT_BUTTON = new JButton("Remove Notification");
		BUTTON[1] = EDIT_BUTTON;
	}
	private void addButtons(){
		for(int a = 0; a<BUTTON.length;a++){
			this.add(BUTTON[a]);
			BUTTON[a].addActionListener(this);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == BUTTON[0]){
			String x = JOptionPane.showInputDialog("Add Note");
			DNEngine.addNotification(x);
			DealerNotifications.updateNotifications();
		}
		if(e.getSource() == BUTTON[1]){
			if(JOptionPane.showConfirmDialog(null, "Are you sure?", "Remove Lead",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				DNEngine.removeNotification(DNEngine.NOTE_ID);
				DealerNotifications.updateNotifications();
			}
		}
		
	}

}
