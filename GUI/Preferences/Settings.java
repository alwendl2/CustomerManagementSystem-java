package GUI.Preferences;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI.GUIManager;

public class Settings extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	private double S_WIDTH_RATIO = 0.4;
	private double S_HEIGHT_RATIO = 0.6;
	private int S_WIDTH;
	private int S_HEIGHT;
	private Dimension S_SIZE;
	
	private JPanel SETTINGS_HOLDER;
	private JTabbedPane SETTINGS_TABBED_PANE;
	private JButton[] BOTTOM_BUTTONS = new JButton[3];
	private JButton SUBMIT_BUTTON;
	private JButton CANCEL_BUTTON;
	private JButton DEFAULT_BUTTON;
	private JPanel TOP_CONTAINER;
	private JPanel BOTTOM_CONTAINER;
	
	
	public Settings(){
		setAllSizes();
		createContainers();
		finalizeFrame();
	}
	
	public void setAllSizes(){
		S_WIDTH = (int) (GUIManager.getScreenWidth() * S_WIDTH_RATIO);
		S_HEIGHT = (int) (GUIManager.getScreenHeight() * S_HEIGHT_RATIO);
		S_SIZE = new Dimension(S_WIDTH,S_HEIGHT);
		this.setSize(S_SIZE);
	}
	
	private void createContainers(){
		TOP_CONTAINER = new JPanel();
		TOP_CONTAINER.setSize(S_WIDTH,(int) (S_HEIGHT * 0.9));
		TOP_CONTAINER.setMaximumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.setMinimumSize(TOP_CONTAINER.getSize());
		populateTopContainer();
		
		BOTTOM_CONTAINER = new JPanel();
		BOTTOM_CONTAINER.setSize(S_WIDTH,(int) (S_HEIGHT * 0.1));
		BOTTOM_CONTAINER.setMaximumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.setMinimumSize(BOTTOM_CONTAINER.getSize());
		populateBottomContainer();
		
		putTogetherPanels();
	}
	
	private void populateTopContainer(){
		TOP_CONTAINER.setLayout(new BorderLayout());
		SETTINGS_TABBED_PANE = new JTabbedPane();
		SETTINGS_TABBED_PANE.addTab("General", makeNewTabPanel());
		SETTINGS_TABBED_PANE.addTab("Network", new NetworkPanel());
		System.out.println("Gets passed");
		SETTINGS_TABBED_PANE.addTab("SQL", new SQLPanel());
		TOP_CONTAINER.add(SETTINGS_TABBED_PANE,BorderLayout.CENTER);
	}
	private void populateBottomContainer(){
		BOTTOM_CONTAINER.setLayout(new FlowLayout(FlowLayout.RIGHT));
		createButtons();
		for(int a = 0; a < BOTTOM_BUTTONS.length;a++){
			BOTTOM_CONTAINER.add(BOTTOM_BUTTONS[a]);
		}
	}
	
	private void putTogetherPanels(){
		BoxLayout box = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.add(TOP_CONTAINER);
		this.add(BOTTOM_CONTAINER);
	}
	private void finalizeFrame(){
		this.setLocationRelativeTo(null);
		this.setTitle("Settings");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private JComponent makeNewTabPanel(){
		SETTINGS_HOLDER = new JPanel();
		return SETTINGS_HOLDER;
	}
	private void createButtons(){
		SUBMIT_BUTTON = new JButton("Submit");
		CANCEL_BUTTON = new JButton("Cancel");
		DEFAULT_BUTTON = new JButton("Reset Defaults");
		BOTTOM_BUTTONS[0] = DEFAULT_BUTTON;
		BOTTOM_BUTTONS[1] = CANCEL_BUTTON;
		BOTTOM_BUTTONS[2] = SUBMIT_BUTTON;
		for(int a = 0; a < BOTTOM_BUTTONS.length; a++){
			BOTTOM_BUTTONS[a].addActionListener(this);
		}
	}
	
	private void defaultSettings(){
		
	}
	
	private void closeSettings(){
		this.dispose();
	}
	private void submitSettings(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == BOTTOM_BUTTONS[0]){
			defaultSettings();
		}
		if(e.getSource() == BOTTOM_BUTTONS[1]){
			closeSettings();
		}
		if(e.getSource() == BOTTOM_BUTTONS[2]){
			submitSettings();
		}
	}

}
