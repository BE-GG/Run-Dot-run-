package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.text.*;



public class HowToPlay extends JPanel implements ActionListener {
	
	BufferedImage background = null;
	ImageIcon img = null;
	private Timer animator;
	private ImageIcon imageArray[];
	private int delay = 70, totalFrames = 43, currentFrame = 0;
	private JTextArea textArea;
	private JTextPane pane;
	private JButton back;
	  
	public HowToPlay() throws BadLocationException {
		
		//Back button
	    img = new ImageIcon( getClass().getResource("/back.png"));
	    Image temp = img.getImage();
	    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
	    img = new ImageIcon(temp);   
	    back = new JButton(img);
	    back.setBorderPainted( false );
	    back.setBounds(20, 20, 50, 50);
	    
	    //Text area
		pane = new JTextPane();
	
		addText(pane, "MISSION: ", Color.RED);
		addText(pane, "REACH THE END OF THE SENTENCE!", Color.BLACK);
		addText(pane, "\nHOW TO PLAY: ", Color.RED);
		addText(pane, "JUMP OVER THE LETTERS" 
						+"\nBY PRESSING THE SPACE BAR. DO NOT FALL TO THE GROUND", Color.BLACK);
		addText(pane, "\n\n\nWARNING!", Color.RED);
		addText(pane, "\nDO NOT FALL TO THE GROUND UNLESS YOU SEE"  
						+"\nTHE END OF THE SENTENCE"  
						+"\nBE CAREFUL ABOUT THE OBSTACLES", Color.BLACK);
		
		pane.setOpaque(false); 
		pane.setFont(new Font("CALIBRI", Font.BOLD, 25));
		pane.setBounds(230, 200, 1000, 700);
		pane.setOpaque(false);
		
		//Set background
		imageArray = new ImageIcon[totalFrames];
		  for(int i = 0; i < totalFrames; i++)
			  imageArray[i] = new ImageIcon(getClass().getResource("/frame" + i + ".png"));
		animator = new Timer(delay, this);
		animator.start();
				
		this.setPreferredSize(new Dimension(1200, 800)); 
	    this.setLayout(null);
	    this.add(pane);
	    this.add(back);    
	}
		
	
	 protected void paintComponent(Graphics g) { 
		    super.paintComponent(g);
		    g.drawImage( background, 0, 0, getWidth(), getHeight(), this);
		    
		    if(currentFrame >= totalFrames)
		    	currentFrame = 0;
		    
		    imageArray[currentFrame].paintIcon(this, g, 0, 0);
		    currentFrame++;
		  }

	
	 @Override
	 public void actionPerformed(ActionEvent e) {
	 	repaint();
	 	
	 } 
	 
	 //Add styled documents to text area
	 public void addText( JTextPane pane, String text, Color color) throws BadLocationException {
		 StyledDocument doc = pane.getStyledDocument();
		 
		 MutableAttributeSet style = pane.addStyle("Color Style", null);
		 StyleConstants.setForeground(style, color);
	        
		 try {
	            doc.insertString(doc.getLength(), text, style);
	        } 
	        catch (BadLocationException e) {
	            e.printStackTrace();
	        }       	 
	 }
	 
	 //Get button
	 public JButton getButton() {
		 return back; 
	 }
	 
    

}
