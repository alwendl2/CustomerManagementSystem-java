
package ErrorReporting;

public class ConsoleLogs {

	public static void consoleReporting(String className, String method, String purpose){ //Constructs a console report --- CLASS,METHOD,PURPOSE
		System.out.println("Class: " + className +", Method: " + method + ", Purpose: " + purpose);
	}
	
	public static void consoleReportingSuccess(){ //Constructs a console report --- SUCCESS REPORT
		System.out.println("Success");
	}
}
