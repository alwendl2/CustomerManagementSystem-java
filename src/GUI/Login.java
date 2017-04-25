
package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import AccountSystem.AccountManager;
import Engine.SoftwareManager;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton loginbutton;
	JPanel loginpanel;
	JPanel CRM;
	JTextField txuser;
	JTextField pass;
	JLabel username;
	JLabel password;
	JLabel CRM_LABEL;

	public Login() {
		super("Login Autentification");

		
		loginbutton = new JButton("Login");
		loginpanel = new JPanel();
		txuser = new JTextField(15);
		pass = new JPasswordField(15);
		username = new JLabel("Username - ");
		password = new JLabel("Password - ");
		CRM = new JPanel();
		CRM_LABEL = new JLabel("CRM");

		GridBagLayout f1 = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		
		CRM_LABEL.setFont(new Font("Serif", Font.PLAIN,150));
		//CRM.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		CRM.add(CRM_LABEL,JLabel.CENTER);
		
		
		
		loginpanel.setLayout(f1);

		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		loginpanel.add(username, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		loginpanel.add(password, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		loginpanel.add(txuser, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		loginpanel.add(pass, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		loginpanel.add(loginbutton, gbc);

		
		//loginpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		loginbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(AccountManager.checkLogin(txuser.getText(), pass.getText())){
						dispose();
						SoftwareManager.SESSION.startSession(AccountManager.getAccId(txuser.getText()));
						GUIManager.createJFrame();
					}
					else
					JOptionPane.showMessageDialog(null,
							"Invalid Username/Password");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Cannot connect with server");
					e1.printStackTrace();
				}
			}
		});
		
		pass.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(AccountManager.checkLogin(txuser.getText(), pass.getText())){
						dispose();
						SoftwareManager.SESSION.startSession(AccountManager.getAccId(txuser.getText()));
						GUIManager.createJFrame();
					}
					else
					JOptionPane.showMessageDialog(null,
							"Invalid Username/Password");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Cannot connect with server");
					e1.printStackTrace();
				}
			}
			
		});
		
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		getContentPane().add(CRM);
		getContentPane().add(loginpanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
