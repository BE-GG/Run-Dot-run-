package GameControl;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu{
  
  private JFrame mainMenuFrame;
  private MainMenuPanel mainMenuPanel;
  private LevelsPanel levelsPanel;
  private GameManager game;
  
  private CardLayout cardLayout;
  private JPanel cardpanel;
  //private JPanel mainPanel;
  
  public MainMenu(){
    //mainPanel = new JPanel();
    
    // initializes the frame's properties
    mainMenuFrame = new JFrame();
    mainMenuFrame.setSize(1000, 700);
    mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainMenuFrame.setLayout(new FlowLayout());
    mainMenuFrame.setResizable(false);
    mainMenuFrame.setLocationRelativeTo(null);
    mainMenuFrame.setVisible(true);
    
    // creates instances of GUI stuff and layout for transition
    cardLayout = new CardLayout();
    cardpanel = new JPanel( cardLayout);
    mainMenuPanel = new MainMenuPanel();
    levelsPanel = new LevelsPanel();
    game = new GameManager(1);
    
    // adds instances to cardpanel
    cardpanel.add(mainMenuPanel, "1");
    cardpanel.add(levelsPanel, "2");
    cardpanel.add(game, "3");
   
    // adds cardpanel to frame
    mainMenuFrame.add(cardpanel);
    mainMenuFrame.pack();
    mainMenuFrame.setVisible(true); 
    
    // shows main menu screen first
    cardLayout.show( cardpanel, "1");
    
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
    
  }
  
  // MyActionListener for listening buttons( All ActionListeners are here since we used CardLayout to organize)
  class MyActionListener implements ActionListener
  {
    public void actionPerformed( ActionEvent e)
    {
      CardLayout cardLayout = (CardLayout)(cardpanel.getLayout());
      JComponent pressedButton = (JComponent) e.getSource();
      
      if( pressedButton == levelsPanel.getButton("button1") || pressedButton == levelsPanel.getButton("button2") 
           || pressedButton == levelsPanel.getButton("button3") || pressedButton == levelsPanel.getButton("button4"))
      {   
        cardLayout.show( cardpanel, "3");
        game.start();      
      } 
      else if( pressedButton == levelsPanel.getButton("back"))
      { 
        cardLayout.show( cardpanel, "1");
      }
      else if ( pressedButton == mainMenuPanel.getButton("play"))
      {
        cardLayout.show( cardpanel, "2");
      }
      else if( pressedButton == mainMenuPanel.getButton("quit")){
        mainMenuFrame.dispose();
      }
    }
    
  }
  
  
//game.start(); // Start the thread after making the window visible
  
  
  public static void main(String[] args) {
    new MainMenu();
  }
}

