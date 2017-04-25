package GUI.ClientProfile;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ClientProfileInfo extends JPanel {

	private static final long serialVersionUID = 1L;
	private int CPI_WIDTH;
	private int CPI_HEIGHT;
	private Dimension CPI_SIZE;
	
	private JPanel RIGHT_PANEL;
	private JPanel LEFT_PANEL;
	
	public ClientProfileInfo(){
		setPanelSize();
		createContainers();
		putPanelsTogether();
	}
	
	private void setPanelSize(){
		CPI_WIDTH = ClientProfile.getCP_WIDTH();
		CPI_HEIGHT = ClientProfile.getCP_HEIGHT();
		CPI_SIZE = new Dimension(CPI_WIDTH,CPI_HEIGHT);
		this.setSize(CPI_SIZE);
		this.setMaximumSize(CPI_SIZE);
		this.setMinimumSize(CPI_SIZE);
	}
	
	private void createContainers(){
		RIGHT_PANEL = new JPanel();
		RIGHT_PANEL.setSize(CPI_WIDTH/2,CPI_HEIGHT);
		RIGHT_PANEL.setMaximumSize(RIGHT_PANEL.getSize());
		RIGHT_PANEL.setMinimumSize(RIGHT_PANEL.getSize());
		RIGHT_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		LEFT_PANEL = new JPanel();
		LEFT_PANEL.setSize(CPI_WIDTH/2,CPI_HEIGHT);
		LEFT_PANEL.setMaximumSize(LEFT_PANEL.getSize());
		LEFT_PANEL.setMinimumSize(LEFT_PANEL.getSize());
		LEFT_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void putPanelsTogether(){
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(LEFT_PANEL);
		this.add(RIGHT_PANEL);
	}
}
