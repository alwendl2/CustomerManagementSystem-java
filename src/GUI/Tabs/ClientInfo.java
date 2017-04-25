package GUI.Tabs;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Engine.FieldManager;

public class ClientInfo extends JPanel{

	

	private static final long serialVersionUID = 1L;
	private JLabel[] LABELS = new JLabel[8];
	private JPanel LEFT_PANEL;
	private JPanel RIGHT_PANEL;
	private GridBagConstraints GBC;
	
	
	public ClientInfo(){
		createContainers();
		createComponents();
		addComponents();
	}
	
	private void createContainers(){
		LEFT_PANEL = new JPanel();
		LEFT_PANEL.setSize(this.getWidth()/2,this.getHeight());
		LEFT_PANEL.setMaximumSize(LEFT_PANEL.getSize());
		LEFT_PANEL.setMinimumSize(LEFT_PANEL.getSize());
		LEFT_PANEL.setLayout(new BoxLayout(LEFT_PANEL,BoxLayout.Y_AXIS));

		
		RIGHT_PANEL = new JPanel();
		RIGHT_PANEL.setSize(this.getWidth()/2,this.getHeight());
		RIGHT_PANEL.setMaximumSize(RIGHT_PANEL.getSize());
		RIGHT_PANEL.setMinimumSize(RIGHT_PANEL.getSize());
		RIGHT_PANEL.setLayout(new BoxLayout(RIGHT_PANEL,BoxLayout.Y_AXIS));
			
		
	}
	
	private void createComponents(){
		GBC = new GridBagConstraints();
		for(int x = 0; x<5;x++){
			LABELS[x] = new JLabel();
			LABELS[x].setFont(new Font(LABELS[x].getFont().getFontName(), Font.PLAIN,24));
		}
		LABELS[0].setText(FieldManager.InfoPaneAssist[0]+FieldManager.getLEAD_NAME_FIRST());
		LABELS[1].setText(FieldManager.InfoPaneAssist[1]+FieldManager.getLEAD_NAME_LAST());
		LABELS[2].setText(FieldManager.InfoPaneAssist[2]+FieldManager.getLEAD_MAINPHONE());
		LABELS[3].setText(FieldManager.InfoPaneAssist[3]+FieldManager.getLEAD_CELLPHONE());
		LABELS[4].setText(FieldManager.InfoPaneAssist[4]+FieldManager.getLEAD_WORKPHONE());
		//LABELS[5].setText(FieldManager.InfoPaneAssist[5]+FieldManager.getMAKE());
		//LABELS[6].setText(FieldManager.InfoPaneAssist[6]+FieldManager.getMODEL());
	    //LABELS[7].setText(FieldManager.InfoPaneAssist[7]+FieldManager.getYEAR());
	}
	
	
	private void addComponents(){
		this.setLayout(new GridBagLayout());
		LEFT_PANEL.setLayout(new BoxLayout(LEFT_PANEL,BoxLayout.Y_AXIS));
		for(int x = 0; x<5;x++){
			LEFT_PANEL.add(LABELS[x]);
		}
		RIGHT_PANEL.setLayout(new BoxLayout(RIGHT_PANEL,BoxLayout.Y_AXIS));
		//for(int x = 5; x<8;x++){
			//RIGHT_PANEL.add(LABELS[x]);
		//}
		
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.fill = GridBagConstraints.BOTH;
		this.add(LEFT_PANEL, GBC);
		GBC.gridx = 1;
		this.add(RIGHT_PANEL, GBC);
		
		
	}
}
