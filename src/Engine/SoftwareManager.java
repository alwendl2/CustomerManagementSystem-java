package Engine;


import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import AccountSystem.Session;
import GUI.GUIManager;
import GUI.Login;

public class SoftwareManager implements Runnable {

	
	public static Session SESSION;
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Login login = new Login();
		SESSION = new Session();
		java.lang.Runtime.getRuntime().gc();
	}
	
	public static void main(String args[]){
		(new Thread(new SoftwareManager())).start();
		
	}
	

}
