package GUI;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Engine.SoftwareManager;
import GUI.Miscellaneous.MenuBar;


public class GUIManager {
	
	/* ----- FIELDS ------ */
	public static int SCREEN_SIZE_WIDTH; //Width of the whole screen
	public static int SCREEN_SIZE_HEIGHT; //Height of the whole screen
	public static Dimension SCREEN_SIZE; //Dimension of the whole screen
	
	public static GridBagConstraints GBC; //GridBagConstraints for the GridBagLayout
	public static GridBagLayout GRID_BAG_LAYOUT; //GridBag Layout for the GUI;
	
	public static JFrame MAIN_FRAME; //The main JFrame for the GUI
	public static SidePanel SIDE_PANEL;
	public static DashBoardMain DASHBOARD_PANEL;

	/* ---- Create Methods ------*/
	/* These methods will create the components of the GUI */

	
	public static void createJFrame(){ //Creates the JFrame that will hold all components
		MAIN_FRAME = new JFrame("CRM");
		BoxLayout box = new BoxLayout(MAIN_FRAME.getContentPane(),BoxLayout.X_AXIS);
		MAIN_FRAME.setLayout(box);
		
		setScreenSizeALL();
		MAIN_FRAME.setSize(SCREEN_SIZE);
		
		createInitialPanels();
		addPanels(SIDE_PANEL, DASHBOARD_PANEL);
		MAIN_FRAME.setJMenuBar(new MenuBar());
		finalizeFrame();
		GUIManager.SIDE_PANEL.invokeListener();
		
	}
	
	/**
	 * Removes the current panels and switches them
	 * 
	 * @param side, side panel to replace 
	 * @param main, main panel to replace
	 */
	public static void switchPanels(JPanel side, JPanel main){
		MAIN_FRAME.getContentPane().removeAll();
		addPanels(side,main);
	}
	
	private static void addPanels(JPanel side, JPanel main){
		MAIN_FRAME.add(side);
		MAIN_FRAME.add(main);
		MAIN_FRAME.validate();
		MAIN_FRAME.repaint();
		
	}
	
	private static void createInitialPanels(){
		SIDE_PANEL = new SidePanel();
		DASHBOARD_PANEL = new DashBoardMain();
	}
	
	public static void createGridBagLayout() {
		GRID_BAG_LAYOUT = new GridBagLayout();
	}
	
	public static void createGBC(){
		GBC = new GridBagConstraints();
	}
	
	private static void finalizeFrame() {
		MAIN_FRAME.setResizable(false);
		MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MAIN_FRAME.setVisible(true);
	}
	
	//Setters
	public static void setScreenSizeALL() { //Will set the screen size variables to fill the screen -- AUTOMATIC
		SCREEN_SIZE_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); //Sets the width of the screen
		SCREEN_SIZE_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 35; //Sets the height of the screen
		SCREEN_SIZE = new Dimension(SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT); //Sets the screen size dimension
	}
	
	public static void logout() {
		SoftwareManager.SESSION.endSession();
		MAIN_FRAME.dispose();
		Login login = new Login();
	}
	
	
	//Getters
	public static int getScreenWidth(){
		return SCREEN_SIZE_WIDTH;
	}
	public static int getScreenHeight(){
		return SCREEN_SIZE_HEIGHT;
	}
	public static Dimension getScreenSize(){
		return SCREEN_SIZE;
	}
	public static GridBagLayout getGridBagLayout(){
		return GRID_BAG_LAYOUT;
	}
	public static GridBagConstraints getGBC(){
		return GBC;
	}
	public static JFrame getJFrame(){
		createJFrame();
		return MAIN_FRAME;
	}

}
