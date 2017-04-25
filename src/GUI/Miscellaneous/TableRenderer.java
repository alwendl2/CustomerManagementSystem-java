package GUI.Miscellaneous;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Engine.FieldManager;

public class TableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object obj,boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, obj,isSelected, hasFocus, row, column);
			for(int x=0;x<50;x++){
				if(row==x){
					if(FieldManager.leadColor[x].equals("-3")){
						cell.setBackground(new Color(255,0,0,150));
					}else if(Integer.parseInt(FieldManager.leadColor[x])>0){
						cell.setBackground(new Color(65,105,225,150));
					}else if(FieldManager.leadColor[x].equals("-2")){
						cell.setBackground(new Color(255,215,0,175));
					}else if(Integer.parseInt(FieldManager.leadColor[x])==0){
						cell.setBackground(new Color(112,128,144,175));
					}else{
						cell.setBackground(Color.CYAN);
					}
				}
			}
		return cell;
	}
}




