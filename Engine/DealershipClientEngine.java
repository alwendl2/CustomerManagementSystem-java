
package Engine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ErrorReporting.ConsoleLogs;

/**
 * This class will control the Fields for the software if
 * the user is using the software to call for potential 
 * clients. If not see FieldManager for handling leads.
 * 
 * 
 * 
 */

public class DealershipClientEngine {
	
	private static boolean POTENTIAL_CLIENT; //Whether or not the user is calling potential clients
	
	private static String DATE_ADDED;
	private static String DEALERSHIP_NAME;
	private static String GM_NAME;
	private static String GM_PHONE;
	private static String GM_EMAIL;
	private static String DEALERSHIP_ADDRESS;
	
	private static int CURRENT_LEAD_ID;
	private static int NUM_TIMES_CALLED;
	private static int NUM_TIMES_ANSWERED;
	private static int NUM_APPTS_SET;
	
	public static String PT_ROW_DATA[][];
	public static String PT_COLUMN_DATA[] = {"ID","Dealership","GM","Email","Phone"}; 
	
	public static void createTableCells(){
		
	}
	
	/**
	 * This method will populate the table on the dashboard with the 
	 * values stored in the mysql database
	 * 
	 * @param pageNumber, indicates which page number of leads the client is on
	 * @throws SQLException
	 */
	public static void populateTable(int pageNumber) throws SQLException{
		ConsoleLogs.consoleReporting("DealershipClientEngine","populateTable()", "Adds leads into DASHBOARD_LEADS_TABLES");
		SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_ALL(SQLManager.CONNECTION_STATEMENT, "leads");
		FieldManager.gatherTableRowCount(SQLManager.CURRENT_RESULT_SET);
		int a = 0; //temp value to represent rows
		int b = 0; //temp value to represent columns
		SQLManager.CURRENT_RESULT_SET.absolute(50*(FieldManager.currentPage-1)+1);
		for (int x=0;x<50;x++) {
			SQLManager.CURRENT_RESULT_SET.next();
			PT_ROW_DATA[a][b] = "" + SQLManager.CURRENT_RESULT_SET.getInt("ID");
			b++; //will move on to the next column 
			PT_ROW_DATA[a][b] = SQLManager.CURRENT_RESULT_SET.getString("dealership_name");
			b++;
			PT_ROW_DATA[a][b] = SQLManager.CURRENT_RESULT_SET.getString("gm_name");
			b++;
			PT_ROW_DATA[a][b] = SQLManager.CURRENT_RESULT_SET.getString("gm_email");
			b++;
			PT_ROW_DATA[a][b] = SQLManager.CURRENT_RESULT_SET.getString("gm_phone");			
			b=0; //sets the column back to column zero
			a++; //moves to the next row
			SQLManager.CURRENT_RESULT_SET.next();
		}
		SQLManager.closeResultSet();
			
		ConsoleLogs.consoleReportingSuccess();
	}
	
	/**
	 * This method will populate the fields using the current lead ID
	 * Used when needed to pull certain information from a client
	 * 
	 * @throws SQLException
	 */
	public static void retrieveClientInfo() throws SQLException {
		ConsoleLogs.consoleReporting("DealershipClientEngine","retrieveClientInfo", "Retrieves the Clients info from the database and sets to Field Manager");
		SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_SPECIFIC(SQLManager.CONNECTION_STATEMENT, "leads", CURRENT_LEAD_ID);
		SQLManager.CURRENT_RESULT_SET.first();
		
		setDealershipName(SQLManager.CURRENT_RESULT_SET.getString("dealership_name"));
		setGM_Name(SQLManager.CURRENT_RESULT_SET.getString("gm_name"));
		setGM_Email(SQLManager.CURRENT_RESULT_SET.getString("gm_email"));
		setGM_Phone(SQLManager.CURRENT_RESULT_SET.getString("phone"));
		setDealershipAddress(SQLManager.CURRENT_RESULT_SET.getString("address"));
		setNumTimesCalled(SQLManager.CURRENT_RESULT_SET.getInt("num_times_called"));
		setNumTimesAnswered(SQLManager.CURRENT_RESULT_SET.getInt("num_times_answered"));
		setNumApptSet(SQLManager.CURRENT_RESULT_SET.getInt("num_appts"));
		
		SQLManager.closeResultSet();
		ConsoleLogs.consoleReportingSuccess();
	}
	
	/* Setters */
	
	public static void setDealershipName(String name) {
		DEALERSHIP_NAME = name;
	}
	
	public static void setGM_Name(String name) {
		GM_NAME = name;
	}
	
	public static void setGM_Email(String email) {
		GM_EMAIL = email;
	}
	
	public static void setGM_Phone(String phone) {
		GM_PHONE = phone;
	}
	
	public static void setDealershipAddress(String address) {
		DEALERSHIP_ADDRESS = address;
	}
	
	public static void setNumTimesCalled(int number) {
		NUM_TIMES_CALLED = number;
	}
	
	public static void setNumTimesAnswered(int number) {
		NUM_APPTS_SET = number;
	}
	
	public static void setNumApptSet(int number) {
		NUM_APPTS_SET = number;
	}
	
	/* GETTERS */

	public static String getDateAdded() {
		return DATE_ADDED;
	}
	
	public static String getDealershipName() {
		return DEALERSHIP_NAME;
	}
	
	public static String getGM_Name() {
		return GM_NAME;
	}
	
	public static String getGM_Phone() {
		return GM_PHONE;
	}
	
	public static String getDealershipAddress() {
		return DEALERSHIP_ADDRESS;
	}
	
	public static String getGM_Email() {
		return GM_EMAIL;
	}
	
	public static int getNumTimesCalled() {
		return NUM_TIMES_CALLED;
	}
	
	public static int getNumTimesAnswered() {
		return NUM_TIMES_ANSWERED;
	}
	
	public static int getNumApptsSet() {
		return NUM_APPTS_SET;
	}
	
	
	/**
	 * This method is called to check if the software is in potential client mode
	 * 
	 * @return boolean value of potential client
	 */
	public static boolean isPotentialClient() {
		return POTENTIAL_CLIENT;
	}
	

	
	/**
	 * This method will determine whether or not the software is in potential client mode
	 * 
	 * @param id, the id of the the delearship as it is on dealership_names table
	 */
	public static void checkPotentialClientDatabase(String name){
		SQLManager.CONNECTION = SQLManager.createConnection("karol", "a","dealerships");
		Statement st = SQLManager.createStatement(SQLManager.CONNECTION);
		ResultSet rs = SQLManager.createResultSetDealershipID(st,name);
		POTENTIAL_CLIENT = false;
		int safetyLock = 0; //this int will make sure the method wont get caught in an infinite loop
		int temp; //this int will hold the value of lead_type
		try {
			rs.next();
			temp = rs.getInt("lead_type");
		} catch (SQLException e) {
			System.out.println("Error retrieving lead_type");
			temp = -1;
			e.printStackTrace();
		}
		if (temp == -1) {
			safetyLock++;
			if (safetyLock < 5) {
				checkPotentialClientDatabase(name); //will call the method again up to 5 times to retry
			}
		}
		else if (temp == 1) {
			POTENTIAL_CLIENT = true; //this leads selected are potential clients
		}
		SQLManager.closeConnection(SQLManager.CONNECTION);
		SQLManager.kill(rs, st);
	}

	
}
