package GUI.DealershipNotifications;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Engine.DNEngine;


public class NotificationsTablePanel extends JPanel implements ListSelectionListener{

	private static final long serialVersionUID = 1L;
	
	private int DTP_WIDTH;
	private int DTP_HEIGHT;
	private Dimension DTP_SIZE;
	
	private JTable DN_TABLE;
	private JScrollPane DN_TABLE_SCROLL;
	DefaultTableModel MODEL;

	
	public NotificationsTablePanel(){
		setPanelSize();
		createComponents();
	}
	
	private void setPanelSize(){
		DTP_WIDTH = DealerNotifications.getDN_Width();
		DTP_HEIGHT = (int) (DealerNotifications.getDN_Height() * 0.8);
		DTP_SIZE = new Dimension(DTP_WIDTH,DTP_HEIGHT);
		this.setSize(DTP_SIZE);
		this.setMaximumSize(DTP_SIZE);
		this.setMinimumSize(DTP_SIZE);
	}
	
	private void createComponents(){
		DNEngine.populateDNTable();
		this.setLayout(new BorderLayout());
		MODEL = new DefaultTableModel(DNEngine.notificationsRow,DNEngine.notificationsColumn);
		DN_TABLE = new JTable(MODEL);
		DN_TABLE.setShowGrid(true);
		DN_TABLE.setRowHeight(40);
		DN_TABLE.getColumn("ID").setWidth(45);
		DN_TABLE.getColumn("ID").setMaxWidth(45);
		DN_TABLE.getColumn("ID").setMinWidth(45);
		DN_TABLE.getColumn("Date Added").setWidth(200);
		DN_TABLE.getColumn("Date Added").setMaxWidth(200);
		DN_TABLE.getColumn("Date Added").setMinWidth(200);
		DN_TABLE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DN_TABLE.getSelectionModel().addListSelectionListener(this);//Sets the ListSelectionListener to update the table with current client
		DN_TABLE_SCROLL = new JScrollPane(DN_TABLE);
		this.add(DN_TABLE_SCROLL, BorderLayout.CENTER);
		
	}
	
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (DN_TABLE.getSelectedRow() != -1) {
			System.out.println("Row Selected: "+ DN_TABLE.getSelectedRow());
			Object temp = DN_TABLE.getValueAt(DN_TABLE.getSelectedRow(), 0);
			String temp2 = (String) temp;
			int foo = Integer.parseInt(temp2);
			DNEngine.setNoteID(foo);
        }
		
	}

}

