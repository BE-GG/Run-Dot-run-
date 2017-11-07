import javax.imageio.ImageIO; 
import javax.swing.*; 
import java.awt.*; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.swing.border.*;


public class MainMenuPanel extends JPanel{ 
  
  BufferedImage background = null;
  ImageIcon img;
  JPanel help;
  JPanel buttons;
  JPanel empty;
  JSlider volume;
  JButton play;
  JButton credit;
  JButton quit;
  
  public MainMenuPanel() { 
    
    help = new JPanel();
    help.setLayout( new BoxLayout( help, BoxLayout.X_AXIS));
  
    volume = new JSlider(JSlider.HORIZONTAL, 0, 100, 20);
    volume.setPaintTicks(true);  
    volume.setForeground(Color.BLACK);
    
    img = new ImageIcon( getClass().getResource("volume.png"));
    Image temp = img.getImage();
    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
    img = new ImageIcon(temp);   
    JLabel volumeImage = new JLabel(img);
    
    img = new ImageIcon( getClass().getResource("questionmark.png"));
    temp = img.getImage();
    temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
    img = new ImageIcon(temp);   
    JButton questionmark = new JButton(img);
    
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
    
    this.setPreferredSize(new Dimension(1000, 700)); 
    this.setLayout( new FlowLayout());
    this.add(help);
    this.add(empty);
    this.add( buttons);
    
    try {
      background = ImageIO.read( new File("backgroundImage.png"));
    } catch (IOException e){
       e.printStackTrace();
    }
    
  } 
  
  protected void paintComponent(Graphics g) { 
    super.paintComponent(g);
    g.drawImage( background, 0, 0, getWidth(), getHeight(), this);
  } 
  
  
  
  public static void main( String[] args )
  {
    JFrame mainMenuFrame = new JFrame();
    mainMenuFrame.add( new MainMenuPanel() );
    mainMenuFrame.setSize(1000, 700); 
    mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    mainMenuFrame.setResizable(false); 
    mainMenuFrame.setLocationRelativeTo(null); 
    mainMenuFrame.setVisible(true);
    mainMenuFrame.pack();
  }     
} 
