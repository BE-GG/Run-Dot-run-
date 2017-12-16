package UserInterface;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import runDotRun.Objects.Dot;
import runDotRun.Objects.ObjectId;


public class MainMenuPanel extends JPanel implements ActionListener{ 
  
  BufferedImage background = null;
  ImageIcon img;
  JPanel help;
  JPanel buttons;
  JPanel empty;
  JSlider volume;
  JButton play;
  JButton credit;
  JButton quit;
  JButton questionmark;
  private Timer animator;
  private ImageIcon imageArray[];
  private int delay = 70, totalFrames = 43, currentFrame = 0;
  private Clip clip;
  
  public MainMenuPanel() { 
    
	  imageArray = new ImageIcon[totalFrames];
	  for(int i = 0; i < totalFrames; i++)
		  imageArray[i] = new ImageIcon(getClass().getResource("/frame" + i + ".png"));
	  animator = new Timer(delay, this);
	  
    help = new JPanel();
    help.setLayout( new BoxLayout( help, BoxLayout.X_AXIS));
  
    volume = new JSlider(JSlider.HORIZONTAL, 0, 100, 20);
    volume.setPaintTicks(true);  
    volume.setForeground(Color.BLACK);
    
    ////////////////////////////////////////////////
	File soundFile = new File("Sound/jumping4.wav");
	try {
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		try {
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (UnsupportedAudioFileException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
	
	
	volume.addChangeListener(new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
			//System.out.println((float)slider.getValue()/20f);
			System.out.println(gainControl.getMaximum());
			System.out.println(gainControl.getMinimum());
			
		}
	});
	
	//clip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
    
    /////////////////////////////////////////////////
    
    img = new ImageIcon( getClass().getResource("/volume.png"));
    Image temp = img.getImage();
    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
    img = new ImageIcon(temp);   
    JLabel volumeImage = new JLabel(img);
    
    img = new ImageIcon( getClass().getResource("/questionmark.png"));
    temp = img.getImage();
    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
    img = new ImageIcon(temp);   
    questionmark = new JButton(img);
    
    help.add(Box.createRigidArea( new Dimension(30, 0)));
    help.add(volumeImage);
    help.add(volume);
    help.add(Box.createRigidArea( new Dimension(600, 0)));
    help.add(questionmark);
    help.add(Box.createRigidArea( new Dimension(30, 0)));  
    
    /*volume.addChangeListener( new ChangeListener(){
      public void stateChanged( ChangeEvent event){
      }
    }
    );*/
    
    empty = new JPanel();
    buttons = new JPanel();
    play = new JButton("PLAY GAME");
    play.setFont(new Font("CALIBRI", Font.BOLD | Font.ITALIC, 35));
    play.setBorder(new LineBorder(Color.BLACK, 3));
    play.setOpaque(true);
    credit = new JButton("CREDIT");
    credit.setFont(new Font("CALIBRI", Font.BOLD | Font.ITALIC, 25));
    credit.setBorder(new LineBorder(Color.BLACK, 3));
    credit.setOpaque(true);
    quit = new JButton("QUIT");
    quit.setFont(new Font("CALIBRI", Font.BOLD | Font.ITALIC, 20));
    quit.setBorder(new LineBorder(Color.BLACK, 3));
    quit.setOpaque(true);
    
    
    help.setOpaque(false);
    empty.setOpaque(false);
    buttons.setOpaque(false); 
    
    buttons.setLayout( new BoxLayout( buttons, BoxLayout.Y_AXIS));   
    buttons.setAlignmentY( JComponent.CENTER_ALIGNMENT);
    buttons.add(Box.createVerticalGlue());
    buttons.setLayout( new FlowLayout()); 
    buttons.add( play);
    buttons.add( credit);
    buttons.add( quit);
    
    help.setPreferredSize(new Dimension(1000, 100)); 
    empty.setPreferredSize(new Dimension(1000, 200)); 
    buttons.setPreferredSize(new Dimension(300, 230)); 
    play.setPreferredSize( new Dimension(300, 70));
    credit.setPreferredSize( new Dimension(200, 70));
    quit.setPreferredSize( new Dimension(100, 70));
    
    
    this.setPreferredSize(new Dimension(1200, 800)); 
    this.setLayout(null);
    this.add(help);
    help.setBounds(100, 0, 1000, 100);
    this.add(empty);
    empty.setBounds(100, 0, 1000, 200);
    this.add( buttons);
    buttons.setBounds(450, 200, 300, 300);
    
    try {
      background = ImageIO.read(getClass().getResourceAsStream("/backgroundImage.png"));
    } catch (IOException e){
       e.printStackTrace();
    }
    
    animator.start();
    
  } 
  
  public JButton getButton( String name) 
  { 
     if ( name.equals("play") ) 
     { 
       return play;  
     } 
     else if ( name.equals("credit") ) 
     { 
       return credit; 
     }   
     else if ( name.equals("quit") ) 
     { 
       return quit; 
     } 
     else if ( name.equals("questionmark") ) 
     { 
       return questionmark; 
     } 
     return null; 
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
  
  /*public static void main( String[] args )
  {
    JFrame mainMenuFrame = new JFrame();
    mainMenuFrame.add( new MainMenuPanel() );
    mainMenuFrame.setSize(1000, 700); 
    mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    mainMenuFrame.setResizable(false); 
    mainMenuFrame.setLocationRelativeTo(null); 
    mainMenuFrame.setVisible(true);
    mainMenuFrame.pack();
  }*/   
} 