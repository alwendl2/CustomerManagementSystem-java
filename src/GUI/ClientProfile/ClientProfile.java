
package GUI.ClientProfile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.GUIManager;

public class ClientProfile extends JFrame implements ActionListener{
	
	private double CP_WIDTH_RATIO = 0.4;
	private double CP_HEIGHT_RATIO = 0.6;
	private static int CP_WIDTH;
	private static int CP_HEIGHT;
	private static Dimension CP_SIZE;
	
	private JPanel TOP_CONTAINER;
	private JPanel BOTTOM_CONTAINER;
	

	private static final long serialVersionUID = 1L;
	
	public ClientProfile(){
		setSizes();
		createContainers();
		putTogetherPanels();
		finalizeFrame();
	}
	
	private void setSizes(){
		CP_WIDTH = (int) (GUIManager.getScreenWidth() * CP_WIDTH_RATIO);
		CP_HEIGHT = (int) (GUIManager.getScreenHeight()* CP_HEIGHT_RATIO);
		CP_SIZE = new Dimension(CP_WIDTH,CP_HEIGHT);
		this.setSize(CP_SIZE);
	}
	private void createContainers(){
		TOP_CONTAINER = new JPanel(new BorderLayout());
		TOP_CONTAINER.setSize(CP_WIDTH,CP_HEIGHT/2);
		TOP_CONTAINER.setMaximumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.setMinimumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.add(new ClientProfileInfo(),BorderLayout.CENTER);
		
		BOTTOM_CONTAINER = new JPanel(new BorderLayout());
		BOTTOM_CONTAINER.setSize(CP_WIDTH,CP_HEIGHT/2);
		BOTTOM_CONTAINER.setMaximumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.setMinimumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.add(new ClientProfileNotes(),BorderLayout.CENTER);
		
	}
	private void putTogetherPanels(){
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.add(TOP_CONTAINER);
		this.add(BOTTOM_CONTAINER);
	}
	private void finalizeFrame(){
		this.setTitle("Client Profile");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static int getCP_WIDTH(){
		return CP_WIDTH;
	}
	
	public static int getCP_HEIGHT(){
		return CP_HEIGHT;
	}
	public static Dimension getCP_SIZE(){
		return CP_SIZE;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
