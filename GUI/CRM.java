package GUI;

import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CRM{
	
	public static JFrame frame;
	
	public CRM(){
		GUIManager.createJFrame();
		frame = GUIManager.getJFrame();
	}
}
