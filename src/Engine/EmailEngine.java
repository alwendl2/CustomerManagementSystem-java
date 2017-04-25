
package Engine;

public class EmailEngine {

	public static String[][] emailRow;
	public static String[] emailColumn = {"ID", "From", "Subject", "Date"};
	
	private static int numberOfEmails;
	
	public static void retrieveTestEmails(){
		numberOfEmails = 15;
		emailRow = new String[numberOfEmails][emailColumn.length];
		for(int a = 0; a < emailRow.length;a++){
			for(int x = 0; x< emailColumn.length; x++){
				emailRow[a][x] = "TEST";
			}
		}
	}
	
	
}
