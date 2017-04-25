package GUI.ClientProfile;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Engine.FieldManager;

public class ClientProfileNotes extends JPanel{
	
	private int CPN_WIDTH;
	private int CPN_HEIGHT;
	private Dimension CPN_SIZE;
	
	private JTable CLIENT_NOTES;
	private JScrollPane CLIENT_NOTES_SCROLL;
	
	public ClientProfileNotes(){
		setPanelSize();
		createTable();
	}
	public void setPanelSize(){
		CPN_WIDTH = ClientProfile.getCP_WIDTH() - 35;
		CPN_HEIGHT = (ClientProfile.getCP_HEIGHT()/2) - 35;
		CPN_SIZE = new Dimension(CPN_WIDTH,CPN_HEIGHT);
		this.setSize(CPN_SIZE);
		this.setMaximumSize(CPN_SIZE);
		this.setMinimumSize(CPN_SIZE);
	}
	
	private void createTable(){
		this.setLayout(new BorderLayout());
		CLIENT_NOTES = new JTable(FieldManager.notesRow,FieldManager.notesColumn);
		CLIENT_NOTES.setShowGrid(true);
		CLIENT_NOTES.setRowHeight(30);
		CLIENT_NOTES_SCROLL = new JScrollPane(CLIENT_NOTES);
		this.add(CLIENT_NOTES_SCROLL, BorderLayout.CENTER);
	}

}
