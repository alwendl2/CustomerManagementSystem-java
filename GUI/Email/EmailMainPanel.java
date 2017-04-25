package GUI.Email;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Engine.EmailEngine;
import GUI.GUIManager;

public class EmailMainPanel extends JPanel implements ListSelectionListener{

	private int WIDTH; //Width of the Dashboard
	private int HEIGHT; //Height of the Dashbaord
	private Dimension SIZE;
	
	private JPanel TOP_CONTAINER;
	private JPanel BOTTOM_CONTAINER;
	
	private JTable EMAILS;
	private JScrollPane EMAILS_LIST;
	private JList MAIL_TYPE;
	private JTextArea EMAIL_VIEW;
	
	public EmailMainPanel(){
		setPanelSize();
		createContainers();
		putTogetherPanel();
	}
	
	private void setPanelSize(){
		WIDTH = (int) (GUIManager.getScreenWidth() * 0.8);
		HEIGHT = GUIManager.getScreenHeight();
		SIZE = new Dimension(WIDTH, HEIGHT);
		this.setSize(SIZE);
		this.setMaximumSize(SIZE);
		this.setMinimumSize(SIZE);
	}
	
	private void createContainers(){
		TOP_CONTAINER = new JPanel(new BorderLayout());
		TOP_CONTAINER.setSize(WIDTH, HEIGHT / 2);
		TOP_CONTAINER.setMaximumSize(TOP_CONTAINER.getSize());
		TOP_CONTAINER.setMinimumSize(TOP_CONTAINER.getSize());
		createTopComponents();
		
		BOTTOM_CONTAINER = new JPanel(new BorderLayout());
		BOTTOM_CONTAINER.setSize(WIDTH, HEIGHT / 2);
		BOTTOM_CONTAINER.setMaximumSize(BOTTOM_CONTAINER.getSize());
		BOTTOM_CONTAINER.setMinimumSize(BOTTOM_CONTAINER.getSize());
		createBottomComponents();
	}
	
	private void createTopComponents(){
		EMAIL_VIEW = new JTextArea(10,10);
		EMAIL_VIEW.setEditable(false);
		TOP_CONTAINER.add(EMAIL_VIEW, BorderLayout.CENTER);
	}
	
	private void createBottomComponents(){
		EmailEngine.retrieveTestEmails();
		EMAILS = new JTable(EmailEngine.emailRow, EmailEngine.emailColumn);
		EMAILS.setRowHeight(30);
		EMAILS_LIST = new JScrollPane(EMAILS);
		BOTTOM_CONTAINER.add(EMAILS_LIST,BorderLayout.CENTER);	
	}
	
	private void putTogetherPanel(){
		this.setLayout(new BorderLayout());
		JPanel tempPanel = new JPanel();
		BoxLayout tempBoxLayout = new BoxLayout(tempPanel, BoxLayout.Y_AXIS);
		tempPanel.add(TOP_CONTAINER);
		tempPanel.add(BOTTOM_CONTAINER);
		this.add(tempPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
