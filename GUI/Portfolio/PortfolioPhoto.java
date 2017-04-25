package GUI.Portfolio;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PortfolioPhoto extends JPanel{


	private static final long serialVersionUID = 1L;
	private Image BLANK_PHOTO;
	
	public PortfolioPhoto(){
		BLANK_PHOTO = new ImageIcon("blank-profile-hi.png").getImage();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(BLANK_PHOTO, 0, 0, getWidth(), getHeight(), this);
    }

}
