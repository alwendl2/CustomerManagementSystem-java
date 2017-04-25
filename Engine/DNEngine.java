
package Engine;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



public class DNEngine {
	
	private static ResultSet DEALER_RESULTSET;
	
	public static int NOTIFICATIONS_NUMBER;
	
	public static String[][] notificationsRow;
	public static String[] notificationsColumn = {"ID","Date Added", "Notification"};
	
	public static String[][] sidePanelNotificationsRow;
	public static String[] sidePanelNotificationsColumn = {"GM Message"};
	
	public static int NOTE_ID;
	
	
	
	public static void populateDNTable(){
	
		DEALER_RESULTSET = SQLManager.createResultSet_ALL(FieldManager.getCONNECTION_STATEMENT(), "notifications");
		NOTIFICATIONS_NUMBER = FieldManager.gatherTableRowCount(DEALER_RESULTSET);
		notificationsRow = new String[NOTIFICATIONS_NUMBER][notificationsColumn.length];
		if(NOTIFICATIONS_NUMBER!=0){
			System.out.println("IN POPULATE NOTES TABLE");
			int a = 0;
			int b = 0;
			try {
				DEALER_RESULTSET.beforeFirst();
				while(DEALER_RESULTSET.next()){
					notificationsRow[a][b] = DEALER_RESULTSET.getInt("id") + "";
					b++;
					//System.out.println("here");
					Timestamp x = DEALER_RESULTSET.getTimestamp("date_added");
					Date date = new Date(x.getTime());
					notificationsRow[a][b] = "" + FieldManager.format.format(date);
					//System.out.println(notificationsRow[a][b]);
					b++;
					notificationsRow[a][b] = DEALER_RESULTSET.getString("notification");
					//System.out.println(notificationsRow[a][b]);
					b=0;
					a++;
				}
				DEALER_RESULTSET.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void gatherNotifications(){
		if(FieldManager.getCURRENT_DEALER_DB() != null){
			//System.out.println("hrere");
			DEALER_RESULTSET = SQLManager.createResultSet_ALL(FieldManager.getCONNECTION_STATEMENT(), "notifications");
			NOTIFICATIONS_NUMBER = FieldManager.gatherTableRowCount(DEALER_RESULTSET);
			sidePanelNotificationsRow = new String[NOTIFICATIONS_NUMBER][1];
			if(NOTIFICATIONS_NUMBER!=0){
				int a = 0;
				try {
					DEALER_RESULTSET.beforeFirst();
					while(DEALER_RESULTSET.next()){
						sidePanelNotificationsRow[a][0] = DEALER_RESULTSET.getString("notification");
						a++;
					}
				DEALER_RESULTSET.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else{
			sidePanelNotificationsRow = new String[1][1];
			sidePanelNotificationsRow[0][0] = "No DB Selected!";
		}
	}
	
	public static void addNotification(String x){
		try {
			FieldManager.getCONNECTION_STATEMENT().executeUpdate("INSERT INTO notifications (notification) VALUES ('"+x+"');");// add more columns needed
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeNotification(int noteID){
		System.out.println("removing notifications....");
		try {
			FieldManager.getCONNECTION_STATEMENT().executeUpdate("DELETE FROM notifications WHERE id = " +noteID +" LIMIT 1;");// add more columns needed
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void editNotification(String x,int noteID){
		try {
			FieldManager.getCONNECTION_STATEMENT().executeUpdate("UPDATE notifications SET note="+x+" where id="+noteID);// add more columns needed
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setNoteID(int x){
		NOTE_ID = x;
	}
	public static int getNoteID(){
		return NOTE_ID;
	}
	
	
	
	
	
}
