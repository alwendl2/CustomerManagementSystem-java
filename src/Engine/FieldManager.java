/*
 * Decompiled with CFR 0_102.
 */
package Engine;

import AccountSystem.Session;
import Engine.SQLManager;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class FieldManager {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cs342";
    private static String LEAD_NAME_FIRST;
    private static String LEAD_NAME_LAST;
    private static String LEAD_MAINPHONE;
    private static String LEAD_CELLPHONE;
    private static String LEAD_WORKPHONE;
    private static String LEAD_CITY;
    private static String LEAD_STATE;
    private static String MAKE;
    private static String MODEL;
    private static String VEHICLE_YEAR;
    public static String[] InfoPaneAssist;
    public static String CURRENT_DEALER_DB;
    public static String LAST_APPT_SET;
    public static int NUM_TIMES_CALLED;
    public static int NUM_TIMES_ANSWERED;
    public static int NUMBER_OF_ROWS;
    public static int NUM_APPTS_SET;
    public static int CURRENT_LEAD_ID;
    public static int DNC_FEED;
    public static String APPOINTMENT_SET_BY;
    public static String APPOINTMENT_CONFIRMED_BY;
    public static String APPOINTMENT_NOTE;
    public static Timestamp APPOINTMENT_SET_FOR_DATE;
    public static int APPOINTMENT_EXT_NUMBER;
    public static int totalRowCount;
    public static String[][] rowData;
    public static String[] columnData;
    public static String LEAD_NOTES;
    public static String[] leadColor;
    public static int currentPage;
    public static String[][] notesRow;
    public static String[] notesColumn;
    public static final SimpleDateFormat format2;
    public static final SimpleDateFormat format;
    public static String[][] apptRow;
    public static String[] apptColumn;

    static {
        InfoPaneAssist = new String[]{"First Name: ", "Last Name: ", "Main Phone: ", "Cell Phone: ", "Work Phone: "/*, "Make: ", "Model: ", "Year: "*/};
        columnData = new String[]{"ID", "First", "Last", "City", "State", /* "Year", "Make", "Model",*/ "Home Phone", "Cell Phone"};
        leadColor = new String[51];
        currentPage = 1;
        notesRow = new String[3][3];
        notesColumn = new String[]{"Date", "Employee", "Notes"};
        format2 = new SimpleDateFormat("MMM-d-yyyy");
        format = new SimpleDateFormat("EEE h:mm a - MMM d yyyy");
        apptColumn = new String[]{"Lead ID", "Lead Name", "Lead phone", "Set By", "Appt. Note", "Date Set", "Date"};
    }

    public static void populateTable(int pageNumber) throws SQLException {
        FieldManager.consoleReporting("populateTable()", "Adds leads into DASHBOARD_LEADS_TABLES");
        SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_ALL(SQLManager.CONNECTION_STATEMENT, "leads");
        int a = 0;
        int b = 0;
        SQLManager.CURRENT_RESULT_SET.absolute(50 * (currentPage - 1) + 1);
        for (int x = 0; x < 50; ++x) {
            String firstName;
            String make;
            String lastName;
            String model;
            String state;
            String homephone;
            String year;
            String city;
            String cellphone;
            int id = SQLManager.CURRENT_RESULT_SET.getInt("id");
            FieldManager.rowData[a][b] = "" + id;
            FieldManager.rowData[a][++b] = firstName = SQLManager.CURRENT_RESULT_SET.getString("first_name");
            FieldManager.rowData[a][++b] = lastName = SQLManager.CURRENT_RESULT_SET.getString("last_name");
            FieldManager.rowData[a][++b] = city = SQLManager.CURRENT_RESULT_SET.getString("city");
            FieldManager.rowData[a][++b] = state = SQLManager.CURRENT_RESULT_SET.getString("state");
            
           // FieldManager.rowData[a][++b] = year = SQLManager.CURRENT_RESULT_SET.getString("year");
            //FieldManager.rowData[a][++b] = make = SQLManager.CURRENT_RESULT_SET.getString("make");
            //FieldManager.rowData[a][++b] = model = SQLManager.CURRENT_RESULT_SET.getString("model");
            
            FieldManager.rowData[a][++b] = homephone = SQLManager.CURRENT_RESULT_SET.getString("home_phone");
            FieldManager.rowData[a][++b] = cellphone = SQLManager.CURRENT_RESULT_SET.getString("cell_phone");
            b = 0;
            int timesCalled = SQLManager.CURRENT_RESULT_SET.getInt("num_times_answered");
            FieldManager.leadColor[a] = String.valueOf(timesCalled);
            if (SQLManager.CURRENT_RESULT_SET.getInt("dnc_feed") == 1) {
                FieldManager.leadColor[a] = "-3";
            } else if (SQLManager.CURRENT_RESULT_SET.getInt("num_appts_set") != 0) {
                FieldManager.leadColor[a] = "-2";
            }
            ++a;
            SQLManager.CURRENT_RESULT_SET.next();
        }
        SQLManager.closeResultSet();
        FieldManager.consoleReportingSuccess();
    }

    public static void setDNC() {
        SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_ALL(SQLManager.CONNECTION_STATEMENT, "leads");
        try {
            FieldManager.setDNCFeed(1);
            FieldManager.getCONNECTION_STATEMENT().executeUpdate("UPDATE leads SET dnc_feed = 1 WHERE id='" + FieldManager.getCURRENT_LEAD_ID() + "';");
        }
        catch (SQLException e) {
            System.out.println("Something went wrong while incrementing the number of times called!");
            e.printStackTrace();
        }
        SQLManager.closeResultSet();
        FieldManager.consoleReportingSuccess();
    }

    public static void setTotalLeads() {
        SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_ALL(SQLManager.CONNECTION_STATEMENT, "leads");
        totalRowCount = FieldManager.gatherTableRowCount(SQLManager.CURRENT_RESULT_SET);
        SQLManager.closeResultSet();
    }

    public static void setAppointmentArray() {
        SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_ALL(SQLManager.CONNECTION_STATEMENT, "appointments");
        NUMBER_OF_ROWS = FieldManager.gatherTableRowCount(SQLManager.CURRENT_RESULT_SET);
        System.out.println("\n\n number of rows " + FieldManager.getNUMBER_OF_ROWS() + "\n\n");
        apptRow = new String[FieldManager.getNUMBER_OF_ROWS()][apptColumn.length];
        if (FieldManager.getNUMBER_OF_ROWS() != 0) {
            try {
                FieldManager.getCURRENT_RESULT_SET().last();
                int a = 0;
                int b = 0;
                do {
                    FieldManager.apptRow[a][b] = "" + FieldManager.getCURRENT_RESULT_SET().getInt("lead_id");
                    FieldManager.apptRow[a][++b] = FieldManager.getCURRENT_RESULT_SET().getString("lead_name");
                    FieldManager.apptRow[a][++b] = FieldManager.getCURRENT_RESULT_SET().getString("lead_phone");
                    FieldManager.apptRow[a][++b] = FieldManager.getCURRENT_RESULT_SET().getString("set_by");
                    FieldManager.apptRow[a][++b] = FieldManager.getCURRENT_RESULT_SET().getString("appt_note");
                    java.sql.Date dateSet = new java.sql.Date(4, 25, 2017);
                    FieldManager.apptRow[a][++b] = format2.format(dateSet);
                    Timestamp x = FieldManager.getCURRENT_RESULT_SET().getTimestamp("appt_date");
                    java.sql.Date date = new java.sql.Date(x.getTime());
                    FieldManager.apptRow[a][++b] = format.format(date);
                    b = 0;
                    ++a;
                } while (FieldManager.getCURRENT_RESULT_SET().previous());
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Something went wrong with populating the table");
            }
        }
        SQLManager.closeResultSet();
    }

    public static String[] getDealershipDatabases() {
        try {
            SQLManager.CONNECTION = SQLManager.createConnection("karol", "a", "DB_URL");
            Statement st = SQLManager.createStatement(SQLManager.CONNECTION);
            ResultSet rs = SQLManager.createResultSet_ALL(st, "client_names");
            ArrayList<String> temp = new ArrayList<String>();
            while (rs.next()) {
                temp.add(rs.getString("client_name"));
            }
            SQLManager.closeConnection(SQLManager.CONNECTION);
            String[] x = temp.toArray(new String[temp.size()]);
            SQLManager.kill(rs, st);
            return x;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error retrieving dealership names");
            return null;
        }
    }

    public static String[] getDealershipNames() {
        try {
            SQLManager.CONNECTION = SQLManager.createConnection("karol", "a", DB_URL);
            Statement st = SQLManager.createStatement(SQLManager.CONNECTION);
            ResultSet rs = SQLManager.createResultSet_ALL(st, "client_names");
            ArrayList<String> temp = new ArrayList<String>();
            while (rs.next()) {
                temp.add(rs.getString("client_name"));
            }
            SQLManager.closeConnection(SQLManager.CONNECTION);
            String[] x = temp.toArray(new String[temp.size()]);
            SQLManager.kill(rs, st);
            return x;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error retrieving dealership names");
            return null;
        }
    }

    public static void retrieveClientInfo() throws SQLException {
        FieldManager.consoleReporting("retrieveClientInfo", "Retrieves the Clients info from the database and sets to Field Manager");
        SQLManager.CURRENT_RESULT_SET = SQLManager.createResultSet_SPECIFIC(SQLManager.CONNECTION_STATEMENT, "leads", CURRENT_LEAD_ID);
        SQLManager.CURRENT_RESULT_SET.first();
        FieldManager.setLEAD_NAME_FIRST(SQLManager.CURRENT_RESULT_SET.getString("first_name"));
        FieldManager.setLEAD_NAME_LAST(SQLManager.CURRENT_RESULT_SET.getString("last_name"));
        FieldManager.setLEAD_MAINPHONE(SQLManager.CURRENT_RESULT_SET.getString("home_phone"));
        FieldManager.setLEAD_CELLPHONE(SQLManager.CURRENT_RESULT_SET.getString("cell_phone"));
        FieldManager.setLEAD_CITY(SQLManager.CURRENT_RESULT_SET.getString("city"));
        FieldManager.setLEAD_STATE(SQLManager.CURRENT_RESULT_SET.getString("state"));
        FieldManager.setMAKE(SQLManager.CURRENT_RESULT_SET.getString("make"));
        FieldManager.setMODEL(SQLManager.CURRENT_RESULT_SET.getString("model"));
        FieldManager.setVEHICLE_YEAR(SQLManager.CURRENT_RESULT_SET.getString("year"));
        FieldManager.setNUM_TIMES_CALLED(SQLManager.CURRENT_RESULT_SET.getInt("num_times_called"));
        FieldManager.setNUM_TIMES_ANSWERED(SQLManager.CURRENT_RESULT_SET.getInt("num_times_answered"));
        FieldManager.setCURRENT_LEAD_ID(SQLManager.CURRENT_RESULT_SET.getInt("id"));
        FieldManager.setNUM_APPTS_SET(SQLManager.CURRENT_RESULT_SET.getInt("num_appts_set"));
        //FieldManager.setLAST_APPT_SET((String)SQLManager.CURRENT_RESULT_SET.getTimestamp("last_appt_set"));
        FieldManager.setDNCFeed(SQLManager.CURRENT_RESULT_SET.getInt("dnc_feed"));
        System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", CURRENT_LEAD_ID, LEAD_NAME_FIRST, LEAD_NAME_LAST, LEAD_CITY, LEAD_STATE, MAKE, MODEL, LEAD_MAINPHONE, LEAD_CELLPHONE);
        SQLManager.closeResultSet();
        FieldManager.consoleReportingSuccess();
    }

    public static void displayClientInfo() {
        FieldManager.consoleReporting("displayClientInfo", "Attempting to display lead information");
        try {
            FieldManager.retrieveClientInfo();
            FieldManager.populateNotesTable();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong with updating the client info!");
            e.printStackTrace();
        }
        FieldManager.consoleReportingSuccess();
    }

    public static void incrementNumTimesCalled() {
        FieldManager.consoleReporting("incrementNumTimesCalled()", "Increments the number of times called by 1");
        try {
            FieldManager.setNUM_TIMES_CALLED(NUM_TIMES_CALLED + 1);
            FieldManager.getCONNECTION_STATEMENT().executeUpdate("UPDATE leads SET num_times_called='" + NUM_TIMES_CALLED + "' WHERE id='" + FieldManager.getCURRENT_LEAD_ID() + "';");
        }
        catch (SQLException e) {
            System.out.println("Something went wrong while incrementing the number of times called!");
            e.printStackTrace();
        }
        FieldManager.consoleReportingSuccess();
    }

    public static void incrementNumTimesAnswered() {
        FieldManager.consoleReporting("incrementNumTimesAnswered()", "Increments the number of times answered by 1");
        try {
            FieldManager.setNUM_TIMES_ANSWERED(NUM_TIMES_ANSWERED + 1);
            FieldManager.getCONNECTION_STATEMENT().executeUpdate("UPDATE leads SET num_times_answered='" + NUM_TIMES_ANSWERED + "' WHERE id='" + FieldManager.getCURRENT_LEAD_ID() + "';");
        }
        catch (SQLException e) {
            System.out.println("Something went wrong while incrementing the number of the times answered!");
            e.printStackTrace();
        }
        FieldManager.consoleReportingSuccess();
    }

    public static int gatherTableRowCount(ResultSet result) {
        FieldManager.consoleReporting("gatherTableRowCount()", "Attempting to gather the amount of rows are in the result set and store them in NUMBER_OF_ROWS");
        int temp = 0;
        try {
            while (result.next()) {
                ++temp;
            }
        }
        catch (SQLException e) {
            System.out.println("Something went wrong while getting the number of rows!");
            e.printStackTrace();
        }
        FieldManager.consoleReportingSuccess();
        return temp;
    }

    public static void populateNotesTable() {
        FieldManager.consoleReporting("populateNotesTable", "Retrieves the notes for the lead from the datase and sets notesRow[][] to the results");
        FieldManager.setCURRENT_RESULT_SET(SQLManager.createResultSet_NOTES(FieldManager.getCONNECTION_STATEMENT(), "notes", FieldManager.getCURRENT_LEAD_ID()));
        NUMBER_OF_ROWS = FieldManager.gatherTableRowCount(SQLManager.CURRENT_RESULT_SET);
        System.out.println("\n\n number of rows " + NUMBER_OF_ROWS + "\n\n");
        notesRow = new String[FieldManager.getNUMBER_OF_ROWS()][3];
        if (FieldManager.getNUMBER_OF_ROWS() != 0) {
            try {
                FieldManager.getCURRENT_RESULT_SET().last();
                int a = 0;
                int b = 0;
                do {
                    String note;
                    String EmployeeName;
                    Timestamp x = FieldManager.getCURRENT_RESULT_SET().getTimestamp("date");
                    java.sql.Date date = new java.sql.Date(x.getTime());
                    FieldManager.notesRow[a][b] = format.format(date);
                    System.out.println(date);
                    FieldManager.notesRow[a][++b] = EmployeeName = FieldManager.getCURRENT_RESULT_SET().getString("employee_name");
                    System.out.println(EmployeeName);
                    FieldManager.notesRow[a][++b] = note = FieldManager.getCURRENT_RESULT_SET().getString("note_content");
                    b = 0;
                    System.out.println(String.valueOf(note) + "\n");
                    ++a;
                } while (FieldManager.getCURRENT_RESULT_SET().previous());
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Something went wrong with populating the table");
            }
        }
        SQLManager.closeResultSet();
        FieldManager.consoleReportingSuccess();
    }

    public static void addNote(String note) {
        FieldManager.consoleReporting("addNote", "Adds a note to the lead");
        try {
            String full_name = String.valueOf(Session.EMPLOYEE_NAME_FIRST) + " " + Session.EMPLOYEE_NAME_LAST;
            SQLManager.CONNECTION_STATEMENT.executeUpdate("INSERT INTO notes (lead_id,employee_name,note_content) VALUES (" + FieldManager.getCURRENT_LEAD_ID() + ",'" + full_name + "', '" + note + "');");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        FieldManager.consoleReportingSuccess();
    }

    public static void addAppointment(String note, Timestamp temp, String ampm) {
        FieldManager.consoleReporting("addAppointment", "Adds the lead to the appointment table and increments number of times appointments were set");
        try {
            FieldManager.setNUM_APPTS_SET(NUM_APPTS_SET + 1);
            FieldManager.getCONNECTION_STATEMENT().executeUpdate("UPDATE leads SET num_appts_set='" + NUM_APPTS_SET + "' WHERE id='" + FieldManager.getCURRENT_LEAD_ID() + "';");
            FieldManager.getCONNECTION_STATEMENT().executeUpdate("INSERT INTO appointments (lead_id,employee_id,lead_name,lead_phone,set_by,confirmed_by,appt_note,appt_date,ampm) VALUES (" + FieldManager.getCURRENT_LEAD_ID() + ",5, '" + FieldManager.getLEAD_NAME_FIRST() + " " + FieldManager.getLEAD_NAME_LAST() + "','" + FieldManager.getLEAD_MAINPHONE() + "', '" + Session.getEMPLOYEE_FULL_NAME() + "','" + FieldManager.getAPPOINTMENT_CONFIRMED_BY() + "','" + note + "','" + temp + "','" + ampm + "');");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was an error setting the appointment! Please try again!");
            e.printStackTrace();
        }
        FieldManager.consoleReportingSuccess();
    }

    private static void consoleReporting(String method, String purpose) {
        System.out.println("Class: FieldManager, Method: " + method + ", Purpose: " + purpose);
    }

    private static void consoleReportingSuccess() {
        System.out.println("Success");
    }

    public static void setLEAD_NOTES(String x) {
        LEAD_NOTES = x;
    }

    public static void setLEAD_NAME_FIRST(String x) {
        LEAD_NAME_FIRST = x;
    }

    public static void setLEAD_NAME_LAST(String x) {
        LEAD_NAME_LAST = x;
    }

    public static void setLEAD_MAINPHONE(String x) {
        LEAD_MAINPHONE = x;
    }

    public static void setLEAD_CELLPHONE(String x) {
        LEAD_CELLPHONE = x;
    }

    public static void setLEAD_WORKPHONE(String x) {
        LEAD_WORKPHONE = x;
    }

    public static void setLEAD_CITY(String x) {
        LEAD_CITY = x;
    }

    public static void setLEAD_STATE(String x) {
        LEAD_STATE = x;
    }

    public static void setMAKE(String x) {
        MAKE = x;
    }

    public static void setMODEL(String x) {
        MODEL = x;
    }

    public static void setVEHICLE_YEAR(String x) {
        VEHICLE_YEAR = x;
    }

    public static void setCURRENT_DEALER_DB(String x) {
        CURRENT_DEALER_DB = x;
    }

    public static void setNUM_TIMES_CALLED(int x) {
        NUM_TIMES_CALLED = x;
    }

    public static void setNUM_TIMES_ANSWERED(int x) {
        NUM_TIMES_ANSWERED = x;
    }

    public static void setNUMBER_OF_ROWS(int x) {
        NUMBER_OF_ROWS = x;
    }

    public static void setCONNECTION(Connection x) {
        SQLManager.CONNECTION = x;
    }

    public static void setCONNECTION_STATEMENT(Statement x) {
        SQLManager.CONNECTION_STATEMENT = x;
    }

    public static void setCURRENT_RESULT_SET(ResultSet x) {
        SQLManager.CURRENT_RESULT_SET = x;
    }

    public static void setCURRENT_LEAD_ID(int x) {
        CURRENT_LEAD_ID = x;
    }

    public static void setNUM_APPTS_SET(int x) {
        NUM_APPTS_SET = x;
    }

    public static void setAPPOINTMENT_SET_BY(String x) {
        APPOINTMENT_SET_BY = x;
    }

    public static void setAPPOINTMENT_CONFIRMED_BY(String x) {
        APPOINTMENT_CONFIRMED_BY = x;
    }

    public static void setAPPOINTMENT_NOTE(String x) {
        APPOINTMENT_NOTE = x;
    }

    public static void setAPPOINTMENT_SET_FOR_DATE(Timestamp x) {
        APPOINTMENT_SET_FOR_DATE = x;
    }

    public static void setAPPOINTMENT_EXT_NUMBER(int x) {
        APPOINTMENT_EXT_NUMBER = x;
    }

    public static void setDNCFeed(int x) {
        DNC_FEED = x;
    }

    public static void setLAST_APPT_SET(String x) {
        LAST_APPT_SET = x;
    }

    public static String getLEAD_NAME_FIRST() {
        return LEAD_NAME_FIRST;
    }

    public static String getLEAD_NAME_LAST() {
        return LEAD_NAME_LAST;
    }

    public static String getLEAD_MAINPHONE() {
        return LEAD_MAINPHONE;
    }

    public static String getLEAD_CELLPHONE() {
        return LEAD_CELLPHONE;
    }

    public static String getLEAD_WORKPHONE() {
        return LEAD_WORKPHONE;
    }

    public static String getMAKE() {
        return MAKE;
    }

    public static String getMODEL() {
        return MODEL;
    }

    public static String getYEAR() {
        return VEHICLE_YEAR;
    }

    public static int getNUM_TIMES_CALLED() {
        return NUM_TIMES_CALLED;
    }

    public static int getNUM_TIMES_ANSWERED() {
        return NUM_TIMES_ANSWERED;
    }

    public static int getNUMBER_OF_ROWS() {
        return NUMBER_OF_ROWS;
    }

    public static Connection getCONNECTION() {
        return SQLManager.CONNECTION;
    }

    public static Statement getCONNECTION_STATEMENT() {
        return SQLManager.CONNECTION_STATEMENT;
    }

    public static ResultSet getCURRENT_RESULT_SET() {
        return SQLManager.CURRENT_RESULT_SET;
    }

    public static String getLEAD_CITY() {
        return LEAD_CITY;
    }

    public static String getLEAD_STATE() {
        return LEAD_STATE;
    }

    public static String getLEAD_NOTES() {
        return LEAD_NOTES;
    }

    public static String getAPPOINTMENT_SET_BY() {
        return APPOINTMENT_SET_BY;
    }

    public static String getAPPOINTMENT_CONFIRMED_BY() {
        return APPOINTMENT_CONFIRMED_BY;
    }

    public static String getAPPOINTMENT_NOTE() {
        return APPOINTMENT_NOTE;
    }

    public static Timestamp getAPPOINTMENT_SET_FOR_DATE() {
        return APPOINTMENT_SET_FOR_DATE;
    }

    public static int getAPPOINTMENT_EXT_NUMBER() {
        return APPOINTMENT_EXT_NUMBER;
    }

    public static int getCURRENT_LEAD_ID() {
        return CURRENT_LEAD_ID;
    }

    public static String getCURRENT_DEALER_DB() {
        return CURRENT_DEALER_DB;
    }

    public static int getNUM_APPTS_SET() {
        return NUM_APPTS_SET;
    }

    public static String getLAST_APPT_SET() {
        return LAST_APPT_SET;
    }
}

