
package Engine;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

import ErrorReporting.ConsoleLogs;

public class SQLManager {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cs342";
	public static Connection CONNECTION;
	public static Statement CONNECTION_STATEMENT;
	public static ResultSet CURRENT_RESULT_SET;
	
	
	
	public static void DatabaseConnector(String database) {
		SQLManager.CONNECTION = SQLManager.createConnection("karol", "a",database);
		SQLManager.CONNECTION_STATEMENT = SQLManager.createStatement(SQLManager.CONNECTION);
	}

	/**
	 * 
	 * 
	 * @param username, username to establish connection
	 * @param password, password to establish connection
	 * @param database, database to connect to
	 * @return returns the established connection
	 */
	public static Connection createConnection(String username, String password,String database) {
		try {
			return DriverManager.getConnection(DB_URL, username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR CREATING CONNECTION--SQLMANAGER CLASS--createConnection METHOD");
			return null;
		}

	}
	//creates a statement with the given connection object
	public static Statement createStatement(Connection x){
		try {
			return x.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("THERE WAS AN ERROR CREATING A STATEMENT--SQLMANAGER CLASS--createStatement METHOD");
			return null;
		}
	}
	//creates a result set using all values in the mySQL table
	public static ResultSet createResultSet_ALL(Statement x, String table){
	
		try {
			return x.executeQuery("SELECT * FROM "+table+";");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("THERE WAS AN ERROR CREATING THE RESULT SET--CLASS SQLMANAGER--createResultSet_ALL METHOD");
			return null;
		}
	}
	//creates a specific result set using an ID parameter 
	public static ResultSet createResultSet_SPECIFIC(Statement x, String table, int ID){
		try {
			return x.executeQuery("SELECT * FROM "+table+" WHERE id = "
						+ ID + ";");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("THERE WAS AN ERROR CREATING THE RESULT SET--SQL MANAGER CLASS--createResultSet_SPECIFIC METHOD");
			return null;
		}
	}
	
	/**
	 * Will create a result set that includes all the info for the database
	 * 
	 * @param x Connection Statement
	 * @param databaseName the name of the database used to find the row in the table
	 * @return 
	 */
	public static ResultSet createResultSetDealershipID(Statement x, String databaseName) {
		ConsoleLogs.consoleReporting("SQLManager", "createResultSetDealershipID", "Creates a result set that includes all the info for the database ");
		try {
			return x.executeQuery("SELECT * FROM dealership_names WHERE database_name = '" + databaseName + "';");
		} catch (SQLException e) {
			System.out.println("There was an error creating the Result Set");
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet createResultSet_NOTES(Statement x, String table, int ID){
		try {
			return x.executeQuery("SELECT * FROM "+table+" WHERE lead_id = "
						+ ID + ";");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("THERE WAS AN ERROR CREATING THE RESULT SET--SQL MANAGER CLASS--createResultSet_SPECIFIC METHOD");
			return null;
		}
	}
	
	/**
	 * Closes connection to the server
	 * 
	 * @param x Connection to the mysql server
	 */
	public static void closeConnection(Connection x){
		try {
			x.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("THERE WAS A PROBLEM CLOSING THE CONNECTION--SQLMANAGER CLASS--closeConnection METHOD");
		}
	}
	
	
	public static void kill(ResultSet rs, Statement st){
		try {
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeResultSet(){
		try {
			CURRENT_RESULT_SET.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean checkAcc(String username, String password) throws SQLException{
		ResultSet RS= createResultSet_ALL(createStatement(createConnection("karol","a",DB_URL)),"accounts");
		while(RS.next()){
				if(RS.getString("username").equals(username)&&RS.getString("password").equals(password)){
					RS.getStatement().getConnection().close();
					RS.close();
					return true;
				}
		}
		RS.getStatement().getConnection().close();
		RS.getStatement().close();
		RS.close();
		return false;
	}
	
	
	

}
