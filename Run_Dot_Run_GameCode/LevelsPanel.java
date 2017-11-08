package GameControl;
import javax.imageio.ImageIO; 
import javax.swing.*; 
import java.awt.*; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.swing.border.*;


public class LevelsPanel extends JPanel{ 
  
  ImageIcon imgLocked;
  ImageIcon imgUnlocked;
  ImageIcon img;
  JButton button1;
  JButton button2;
  JButton button3;
  JButton button4;
  JLabel levels;
  JLabel num;
  JPanel top;
  JPanel middle;
  JPanel bottom;
  JPanel levelNum;
  JButton back;
  
  public LevelsPanel(){
    
    top = new JPanel();
    top.setPreferredSize(new Dimension(1000, 100)); 
    top.setBackground(Color.PINK);
    top.setLayout( new BoxLayout( top, BoxLayout.X_AXIS));
    
    middle = new JPanel();
    middle.setPreferredSize(new Dimension(1000, 200)); 
    middle.setBackground(Color.PINK);
    levels = new JLabel("LEVELS");
    levels.setFont(new Font("CALIBRI", Font.BOLD | Font.ITALIC, 70));
    
    bottom = new JPanel();
    bottom.setPreferredSize(new Dimension(1000, 200));
    bottom.setBackground(Color.PINK);
    
    levelNum = new JPanel();
    levelNum.setPreferredSize(new Dimension(1000, 200));
    levelNum.setBackground(Color.PINK);
    JLabel num1 = new JLabel("Level 1");
    num1.setFont(new Font("CALIBRI", Font.BOLD, 30));
    JLabel num2 = new JLabel("Level 2");
    num2.setFont(new Font("CALIBRI", Font.BOLD, 30));
    JLabel num3 = new JLabel("Level 3");
    num3.setFont(new Font("CALIBRI", Font.BOLD, 30));
    JLabel num4 = new JLabel("Level 4");
    num4.setFont(new Font("CALIBRI", Font.BOLD, 30));
    
    
    //Unlocked level
    img = new ImageIcon( getClass().getResource("unlocked.png"));
    Image temp = img.getImage();
    temp = temp.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
    imgUnlocked = new ImageIcon(temp);   
    button1 = new JButton(imgUnlocked);
    button1.setBorderPainted( false );
    
    //Locked level
    img = new ImageIcon( getClass().getResource("locked.png"));
    temp = img.getImage();
    temp = temp.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
    imgLocked = new ImageIcon(temp);   
    button2 = new JButton(imgLocked);
    button2.setBorderPainted( false );
    button2.setEnabled(false);
    button3 = new JButton(imgLocked);
    button3.setBorderPainted( false );
    button3.setEnabled(false);
    button4 = new JButton(imgLocked);
    button4.setBorderPainted( false );
    button4.setEnabled(false);
    
    //Back button
    img = new ImageIcon( getClass().getResource("back.png"));
    temp = img.getImage();
    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
    img = new ImageIcon(temp);   
    back = new JButton(img);
    back.setBorderPainted( false );
    
       
    //Add components to panel
    top.add(Box.createRigidArea( new Dimension(30, 0)));
    top.add(back);
    middle.add( levels);
    bottom.add( button1);
    bottom.add( button2);
    bottom.add( button3);
    bottom.add( button4);
    
    levelNum.add(num1);
    levelNum.add(Box.createRigidArea( new Dimension(70, 0)));
    levelNum.add(num2);
    levelNum.add(Box.createRigidArea( new Dimension(70, 0)));
    levelNum.add(num3);
    levelNum.add(Box.createRigidArea( new Dimension(70, 0)));
    levelNum.add(num4);
    
    this.setPreferredSize(new Dimension(1000, 700)); 
    this.setLayout( new FlowLayout());
    this.setBackground(Color.PINK);
    this.add(top);
    this.add(middle);
    this.add(bottom);
    this.add(levelNum);
    
  }
  
  //Make a copy of level components
  /*public JButton copyComponent( String lock){
    
    JButton temp = null;  
    if ( lock.equals("locked")){
      temp = new JButton(imgLocked);  
    }
    else if ( lock.equals("unlocked")){
      temp = new JButton(imgUnlocked); 
    } 
    return temp;
  }*/
  
  //Change to lock or unlock
  public void changeStatus( JButton button){
    
    if ( !button.isEnabled()){
      button = new JButton(imgUnlocked); 
      button.setEnabled(true);
    }
    else if ( button.isEnabled()){
      button = new JButton(imgLocked);
      button.setEnabled(false);      
    } 
  }
  
  public JButton getButton( String name) 
  { 
     if ( name.equals("back") ) 
     { 
       return back;  
     } 
     else if ( name.equals("button1") ) 
     { 
       return button1; 
     }   
     else if ( name.equals("button2") ) 
     { 
       return button2; 
     }   
     else if ( name.equals("button3") ) 
     { 
       return button3; 
     }   
     else if ( name.equals("button4") ) 
     { 
       return button4; 
     }      
     return null; 
  } 
  
  /*public static void main( String[] args )
  {
    JFrame mainMenuFrame = new JFrame();
    mainMenuFrame.add( new LevelsPanel() );
    mainMenuFrame.setSize(1000, 700); 
    mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    mainMenuFrame.setResizable(false); 
    mainMenuFrame.setLocationRelativeTo(null); 
    mainMenuFrame.setVisible(true);
    mainMenuFrame.pack();
  }*/ 
}
