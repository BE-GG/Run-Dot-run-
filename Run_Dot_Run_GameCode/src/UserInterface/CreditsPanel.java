package UserInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;



public class CreditsPanel extends JPanel {

	BufferedImage background = null;
	ImageIcon img = null;
	JTextArea text;
	JButton back;
	JLabel credit;
	Font font;
	  
	public CreditsPanel() {
				
		//Back button
	    img = new ImageIcon( getClass().getResource("/back.png"));
	    Image temp = img.getImage();
	    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
	    img = new ImageIcon(temp);   
	    back = new JButton(img);
	    back.setBorderPainted( false );
	    back.setBounds(20, 20, 50, 50);
	    
	    
	    //Credit 
	    credit = new JLabel("<html><u>CREDITS</u></html>");
	    font = new Font("CALIBRI", Font.BOLD | Font.ITALIC, 50);
	    credit.setFont(font);
	    credit.setBounds(480, 100, 300, 100);
	    
	    //Text area
		text = new JTextArea();
		
		text.setText("\n\nBeyza Tuğçe BİLGİÇ"
				+"\n\nGökalp KÖKSAL"
				+"\n\nGökçe ÖZKAN"
				+"\n\nEmine Ayşe SUNAR");
		
		text.setBorder(new LineBorder(Color.BLACK, 3));
		text.setOpaque(true);
		text.setEditable(false);
		font = new Font("CALIBRI", Font.BOLD | Font.ITALIC, 25);
        text.setFont(font);
        text.setForeground(Color.MAGENTA);
        text.setBackground(Color.LIGHT_GRAY);
		text.setBounds(450, 250, 300, 400);
		
		this.setPreferredSize(new Dimension(1200, 800)); 
	    this.setLayout( null);
	    this.setBackground(Color.PINK);
	    this.add(text);
	    this.add(back);
	    this.add(credit);
		
	}
	
	 //Get button
	 public JButton getButton() {
		 return back; 
	 }
	 
}