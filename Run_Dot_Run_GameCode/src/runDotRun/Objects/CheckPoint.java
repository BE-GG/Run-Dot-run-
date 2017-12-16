package runDotRun.Objects;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;


public class CheckPoint extends GameObject{

	private Timer animation;
	private Image imageArray[];
	private Image checkPoint;
	private int totalFrames = 7;
	private int currentFrame = 0;
	
	public CheckPoint(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		imageArray = new Image[totalFrames*2+2];
		//animation = new Timer(50, new MyActionListener());
		for(int i = 0; i < totalFrames; i++) {
			try {
				imageArray[i] = ImageIO.read(getClass().getResource("/CheckPoint" + (i+1) + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			imageArray[7] = ImageIO.read(getClass().getResource("/CheckPoint" + 7 + ".png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 7; i > 0; i--) {
			try {
				imageArray[15-i] = ImageIO.read(getClass().getResource("/CheckPoint" + (i) + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			imageArray[15] = ImageIO.read(getClass().getResource("/CheckPoint" + 1 + ".png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		animation = new Timer(50, new MyActionListener());
		animation.start();
		setVelY(0);
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(checkPoint, (int)getPosX(), (int)getPosY()-1, null);
	}
	
	class MyActionListener implements ActionListener
	{
	    public void actionPerformed( ActionEvent e)
	    {
	        if(currentFrame >= totalFrames*2+2)
	        	currentFrame = 0;
	        
	        checkPoint = imageArray[currentFrame];
	        currentFrame++;
	    }
	  }

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
