
package AccountSystem;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import Engine.SQLManager;
import ErrorReporting.ConsoleLogs;

public class Session {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cs342";
	
	private static int EMPLOYEE_ID;
	public static String EMPLOYEE_NAME_FIRST;
	public static String EMPLOYEE_NAME_LAST;
	private static String EMPLOYEE_EMAIL;
	private static String USERNAME;
	private	Timestamp START_TIME;
	public static int SESSION_NUM_CALLS;
	public static int SESSION_TALK_TIME;
	public static int SESSION_NUM_APPTS;
	public static int TOTAL_APPTS_SET;
	
	
	private Connection SESSION_CONNECTION;
	private static Statement SESSION_STATEMENT;
	private ResultSet SESSION_RESULT_SET;
	
	
	/* ----- CONSTRUCTOR ----- */
	public Session(){
		System.out.println("Ready to create Session!");
	}
	
	public void startSession(int EmployeeID){
		setStartTime();
		startConnection();
		populateFields(EmployeeID);
	}
	
	
	public void endSession(){
		writeIntoDB();
		resetFields();
		closeConnection();
	}
	
	private void setStartTime(){
		Date date = new Date();
		START_TIME = new Timestamp(date.getTime());
	}
	
	private void startConnection(){
		SESSION_CONNECTION = SQLManager.createConnection("karol", "a", DB_URL);
		SESSION_STATEMENT = SQLManager.createStatement(SESSION_CONNECTION);
	}
	
	private void closeResultSet(){
		try {
			SESSION_RESULT_SET.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void closeConnection(){
		try {
			SESSION_CONNECTION.close();
			SESSION_STATEMENT.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void populateFields(int EmployeeID){
		ConsoleLogs.consoleReporting("Session.java","populateFields()","Populates the fields for the session");

		SESSION_RESULT_SET = SQLManager.createResultSet_SPECIFIC(SESSION_STATEMENT, "accounts", EmployeeID);
		try {
			SESSION_RESULT_SET.next();
			EMPLOYEE_ID = EmployeeID;
			EMPLOYEE_NAME_FIRST = SESSION_RESULT_SET.getString("first_name");
			EMPLOYEE_NAME_LAST = SESSION_RESULT_SET.getString("last_name");
			EMPLOYEE_EMAIL = SESSION_RESULT_SET.getString("email");
			USERNAME = SESSION_RESULT_SET.getString("username");
			TOTAL_APPTS_SET = SESSION_RESULT_SET.getInt("total_appts");
			SESSION_NUM_CALLS = 0;
			SESSION_TALK_TIME = 0;
			SESSION_NUM_APPTS = 0;
			//PASSWORD = FieldManager.CURRENT_RESULT_SET.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeResultSet();
		ConsoleLogs.consoleReportingSuccess();
	}
	
	private void writeIntoDB(){
		//try {
			//if(!SESSION_CONNECTION.isClosed()){
			//	String full_name = EMPLOYEE_NAME_FIRST + " " + EMPLOYEE_NAME_LAST;
			//	System.out.println(START_TIME);
			////	String query = "INSERT INTO sessions (employee_id, employee_name,date_start,timeout) VALUES ("
				//				+ EMPLOYEE_ID + ",'" + full_name + "','" + START_TIME +"', 0);";
			//	SESSION_STATEMENT.executeUpdate(query);
		//	}
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	
	

	private void resetFields(){
		EMPLOYEE_ID = -1;
		EMPLOYEE_NAME_FIRST = "";
		EMPLOYEE_NAME_LAST = "";
		EMPLOYEE_EMAIL = "";
		USERNAME = "";
		SESSION_NUM_CALLS = 0;
		SESSION_TALK_TIME = 0;
		SESSION_NUM_APPTS = 0;
		START_TIME = null;
	}
	
	public static void incrementSessionCalls(){
		SESSION_NUM_CALLS++;
	}
	public static void incrementSessionAppts(){
		SESSION_NUM_APPTS++;
	}
	public static void incrementTotalAppts(){
		TOTAL_APPTS_SET++;
		//try {
			//SESSION_STATEMENT.executeUpdate("UPDATE accounts SET total_appts='" + TOTAL_APPTS_SET + "' WHERE id='" + EMPLOYEE_ID + "';");
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	
	
	/* --- Getters --- */
	public static String getEmail(){
		return EMPLOYEE_EMAIL;
	}
	
	public static String getUsername(){
		return USERNAME;
	}
	public static int getSESSION_NUM_CALLS(){
		return SESSION_NUM_CALLS;
	}
	public static int getSESSION_NUM_APPTS(){
		return SESSION_NUM_APPTS;
	}
	public static int getSESSION_TALK_TIME(){
		return SESSION_TALK_TIME;
	}
	public static int getTOTAL_APPTS_SET(){
		return TOTAL_APPTS_SET;
	}
	public static String getEMPLOYEE_FULL_NAME(){
		return EMPLOYEE_NAME_FIRST + " " + EMPLOYEE_NAME_LAST;
	}
	
	
	
}
