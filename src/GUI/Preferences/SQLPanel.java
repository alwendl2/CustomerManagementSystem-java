package GUI.Preferences;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SQLPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel DATABASE_LABEL;
	private JTextField DATABASE_INPUT;
	private JButton IMPORT_BUTTON;
	private JButton APPLY_BUTTON;
	private JPanel[] CONTAINERS = new JPanel[3];
	
	public SQLPanel(){
		initComponents();
		createContainers();
		putTogetherPanel();
	}
	
	private void initComponents(){
		DATABASE_LABEL = new JLabel("Database Name: ");
		DATABASE_INPUT = new JTextField(12);
		IMPORT_BUTTON = new JButton("Import Leads");
		APPLY_BUTTON = new JButton("Apply");
	}
	
	private void createContainers(){
		for(int a = 0; a < CONTAINERS.length;a++){
			CONTAINERS[a] = new JPanel();
			if(a <= CONTAINERS.length - 1)
				CONTAINERS[a].setLayout(new FlowLayout(FlowLayout.LEFT));
			else
				CONTAINERS[a].setLayout(new FlowLayout(FlowLayout.RIGHT));
		}
		CONTAINERS[0].add(DATABASE_LABEL);
		CONTAINERS[0].add(DATABASE_INPUT);
		CONTAINERS[1].add(IMPORT_BUTTON);
		CONTAINERS[2].add(APPLY_BUTTON);
	}
	
	private void putTogetherPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int a = 0; a < CONTAINERS.length; a++){
			this.add(CONTAINERS[a]);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
