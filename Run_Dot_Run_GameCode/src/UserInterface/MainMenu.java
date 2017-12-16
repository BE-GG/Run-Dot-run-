package UserInterface;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import GameControl.GameManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu{
  
  private JFrame mainMenuFrame;
  private MainMenuPanel mainMenuPanel;
  private LevelsPanel levelsPanel;
  private CreditsPanel creditsPanel;
  private HowToPlay howToPlay;
  private GameManager game;
  
  private CardLayout cardLayout;
  private JPanel cardpanel;
  private Timer checkGameOver;
  private JButton pause;
	private Image buttonImage1;
	private Image buttonImage2;
	private Image img;
	private Image img2;
  //private JPanel mainPanel;
  
  public MainMenu() throws BadLocationException{
    //mainPanel = new JPanel();
    
    // initializes the frame's properties
    mainMenuFrame = new JFrame();
    mainMenuFrame.setSize(1200, 800);
    mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainMenuFrame.setLayout(new FlowLayout());
    mainMenuFrame.setResizable(false);
    mainMenuFrame.setLocationRelativeTo(null);
    
    // creates instances of GUI stuff and layout for transition
    cardLayout = new CardLayout();
    cardpanel = new JPanel( cardLayout);
    mainMenuPanel = new MainMenuPanel();
    levelsPanel = new LevelsPanel();
    creditsPanel = new CreditsPanel();
    howToPlay = new HowToPlay();
    game = new GameManager();
    JLayeredPane lp = new JLayeredPane();
    //PausePanel pause = new PausePanel();
    pause = new JButton("PAUSE");
	try {
	    img = ImageIO.read(getClass().getResource("/PauseButton.png"));
	    buttonImage1 = img.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
	    pause.setIcon(new ImageIcon(buttonImage1));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
	try {
	    img2 = ImageIO.read(getClass().getResource("/PauseButtonHovering.png"));
	    buttonImage2 = img2.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
	    //button.setIcon(new ImageIcon(buttonImage2));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
	pause.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	    	pause.setIcon(new ImageIcon(buttonImage2));
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	    	pause.setIcon(new ImageIcon(buttonImage1));
	    }
	});
    
    //djsfkljsdklfjsdklj
    lp.setPreferredSize(new Dimension(1200, 800));
    lp.add(game, Integer.valueOf(1));
    lp.add(pause, Integer.valueOf(2));
    game.setBounds( 0, 0,  
            1200, 800); 
    pause.setBounds( 1020, 20,  150, 60);
    pause.setOpaque(false);
    //lp.setOpaque(false);
    
    // adds instances to cardpanel
    //mainMenuPanel.setBounds(200, 300, 300, 300);
    cardpanel.add(mainMenuPanel, "1");
    cardpanel.add(levelsPanel, "2");
    //cardpanel.add(game, "3");
    cardpanel.add(lp, "3");
    cardpanel.add(howToPlay, "4");
    cardpanel.add(creditsPanel, "5");
    //cardpanel.add(pause, "4");
    //cardpanel.setOpaque(false);
   
    // adds cardpanel to frame
    mainMenuFrame.add(cardpanel);
    mainMenuFrame.pack();
    mainMenuFrame.setVisible(true); 
    
    // shows main menu screen first
    cardLayout.show( cardpanel, "1");
    
    /////////////////////////////////////////
    checkGameOver = new Timer(50, new gameOverActionListener());
    /////////////////////////////////////////
    
    // adds ActionListeners to all buttons
    mainMenuPanel.getButton("play").addActionListener( new MyActionListener() );
    mainMenuPanel.getButton("credit").addActionListener( new MyActionListener() );
    mainMenuPanel.getButton("quit").addActionListener( new MyActionListener() );
    mainMenuPanel.getButton("questionmark").addActionListener( new MyActionListener() );
    
    levelsPanel.getButton("back").addActionListener( new MyActionListener() );
    levelsPanel.getButton("button1").addActionListener( new MyActionListener() );
    levelsPanel.getButton("button2").addActionListener( new MyActionListener() );
    levelsPanel.getButton("button3").addActionListener( new MyActionListener() );
    levelsPanel.getButton("button4").addActionListener( new MyActionListener() );
    levelsPanel.getButton("button5").addActionListener( new MyActionListener() );
    
    howToPlay.getButton().addActionListener( new MyActionListener() );
    creditsPanel.getButton().addActionListener( new MyActionListener() );
    pause.addActionListener(new MyActionListener());
    
    checkGameOver.start();
    mainMenuFrame.setVisible(true);
  }
  
  // MyActionListener for listening buttons( All ActionListeners are here since we used CardLayout to organize)
  class gameOverActionListener implements ActionListener
  {
    public void actionPerformed( ActionEvent e)
    {
    	  if(game.getGameOver()) {
    		  cardLayout.show( cardpanel, "1");
    		  game.setGameOver(false);
    	  }
    		  
    }
  }
  //////////////////////////////////////////////////
  class MyActionListener implements ActionListener
  {
    public void actionPerformed( ActionEvent e)
    {
      CardLayout cardLayout = (CardLayout)(cardpanel.getLayout());
      JComponent pressedButton = (JComponent) e.getSource();
      
      
      if( pressedButton == levelsPanel.getButton("button1"))
      {   
        cardLayout.show( cardpanel, "3");
        game.start(1);      
      } 
      else if(pressedButton == levelsPanel.getButton("button2"))
     {   
       cardLayout.show( cardpanel, "3");
       game.start(2);      
     } 
      else if(pressedButton == levelsPanel.getButton("button3"))
     {   
       cardLayout.show( cardpanel, "3");
       game.start(3);      
     } 
      else if(pressedButton == levelsPanel.getButton("button4"))
     {   
       cardLayout.show( cardpanel, "3");
       game.start(4);      
     } 
      else if(pressedButton == levelsPanel.getButton("button5"))
     {   
       cardLayout.show( cardpanel, "3");
       game.start(5);      
     } 
      else if( pressedButton == levelsPanel.getButton("back"))
      { 
        cardLayout.show( cardpanel, "1");
      }
      else if( pressedButton == howToPlay.getButton() || pressedButton == creditsPanel.getButton()) 
      {
    	  	cardLayout.show( cardpanel, "1");
      }
      else if ( pressedButton == mainMenuPanel.getButton("play"))
      {
    	    levelsPanel.reEvaluateLevels();
        cardLayout.show( cardpanel, "2");
      }
      else if ( pressedButton == mainMenuPanel.getButton("credit"))
      {
        cardLayout.show( cardpanel, "5");
      }
      else if ( pressedButton == mainMenuPanel.getButton("questionmark"))
      {
        cardLayout.show( cardpanel, "4");
      }
      else if( pressedButton == mainMenuPanel.getButton("quit")){
        mainMenuFrame.dispose();
      }
      else if( pressedButton == pause){
    	  game.setPause(!(game.isPause()));
    	  System.out.println(game.isPause());
      }
    	  
    }
  }
  
  
//game.start(); // Start the thread after making the window visible
  
  
  public static void main(String[] args) throws BadLocationException {
    new MainMenu();
  }
}
