package GUI.DealershipNotifications;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.GUIManager;

public class DealerNotifications extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private double DN_WIDTH_RATIO = 0.5;
	private double DN_HEIGHT_RATIO = 0.6;
	private static int DN_WIDTH;
	private static int DN_HEIGHT;
	private static Dimension DN_SIZE;
	
	public static JPanel TOP_CONTAINER;
	public static JPanel BOTTOM_CONTAINER;
	
	public DealerNotifications(){
		setSizes();
		createContainers();
		putPanelsTogether();
		finalizeFrame();
	}
	
	private void setSizes(){
		DN_WIDTH = (int) (GUIManager.getScreenWidth() * DN_WIDTH_RATIO);
		DN_HEIGHT = (int) (GUIManager.getScreenHeight() * DN_HEIGHT_RATIO);
		DN_SIZE = new Dimension(DN_WIDTH, DN_HEIGHT);
		this.setSize(DN_SIZE);
	}
	
	private void createContainers(){
		TOP_CONTAINER = new JPanel(new BorderLayout());
		TOP_CONTAINER.setSize(DN_WIDTH, (int) (DN_HEIGHT * 0.8));
		TOP_CONTAINER.setMaximumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.setMinimumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.add(new NotificationsTablePanel(),BorderLayout.CENTER);
	
		BOTTOM_CONTAINER = new JPanel(new BorderLayout());
		BOTTOM_CONTAINER.setSize(DN_WIDTH,(int) (DN_HEIGHT*0.2));
		BOTTOM_CONTAINER.setMaximumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.setMinimumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.add(new NotificationsButtonPanel(),BorderLayout.CENTER);
		
		
	}
	
	private void putPanelsTogether(){
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.add(TOP_CONTAINER);
		this.add(BOTTOM_CONTAINER);
	}
	
	private void finalizeFrame(){
		this.setTitle("Dealer Notifications");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void updateNotifications(){
		TOP_CONTAINER.removeAll();
		TOP_CONTAINER.add(new NotificationsTablePanel(),BorderLayout.CENTER);
		TOP_CONTAINER.revalidate();
		TOP_CONTAINER.repaint();
	}
	
	public static int getDN_Width(){
		return DN_WIDTH;
	}
	
	public static int getDN_Height(){
		return DN_HEIGHT;
	}
	

}
