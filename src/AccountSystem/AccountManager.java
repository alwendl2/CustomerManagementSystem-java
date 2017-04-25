
package AccountSystem;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Engine.SQLManager;

public class AccountManager {


	public static boolean checkLogin(String username, String password)
			throws SQLException {
		return SQLManager.checkAcc(username, password);
	}

	public static int getAccId(String username) {
		ResultSet RS = SQLManager.createResultSet_ALL(SQLManager
				.createStatement(SQLManager.createConnection("root",
						null, "cs342")), "accounts");
		try {
			while (RS.next()) {
				if (RS.getString("username").equals(username)) {
					int id = RS.getInt("id");
					RS.getStatement().getConnection().close();
					RS.close();
					return id;
				}
			}
			JOptionPane.showMessageDialog(null, "Account Not Found!", "ERROR",
					0);
			RS.getStatement().getConnection().close();
			RS.close();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Account Not Found!", "ERROR",0);	
		}
		
		try {
			RS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	
	
	
	
}
