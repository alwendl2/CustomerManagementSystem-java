package GUI.Tabs;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Engine.FieldManager;

public class ClientHistory extends JPanel {


	private static final long serialVersionUID = 1L;
	
	private JLabel[] LABELS = new JLabel[5];
	
	public ClientHistory(){
		createComponents();
		addComponents();
	}
	
	private void createComponents(){
		for(int x = 0; x<LABELS.length;x++){
        	LABELS[x] = new JLabel();
        	LABELS[x].setFont(new Font(LABELS[x].getFont().getFontName(), Font.PLAIN,24));
        	LABELS[x].setHorizontalAlignment(JLabel.CENTER);
        	LABELS[x].setVerticalAlignment(JLabel.CENTER);
		}
        LABELS[0].setText("Number Times Called: " + FieldManager.getNUM_TIMES_CALLED());
        LABELS[1].setText("Number Times Answered: " + FieldManager.getNUM_TIMES_ANSWERED());
        LABELS[2].setText("Number Appointments Set: " + FieldManager.getNUM_APPTS_SET());
        LABELS[3].setText("Last Appointment Set: " + FieldManager.getLAST_APPT_SET());
        LABELS[4].setText("Do Not Call: NO");
        if(FieldManager.DNC_FEED == 1)
        LABELS[4].setText("Do Not Call: YES");
	}
	
	private void addComponents(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int a = 0;a<LABELS.length;a++){
			this.add(LABELS[a]);
		}
	}
	
	
}
