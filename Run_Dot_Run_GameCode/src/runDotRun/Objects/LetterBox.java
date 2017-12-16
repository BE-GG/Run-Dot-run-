package runDotRun.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class LetterBox extends GameObject{
	

	public LetterBox(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)posX, (int)posY, getWidth(), getHeight());
		//g.drawImage(extraLifeImage, (int)getPosX(), (int)getPosY()+3, null);
		g.setColor(Color.WHITE);
		g.drawRect((int)posX, (int)posY, getWidth(), getHeight());
		
	}
	
	public Rectangle getBounds() {
		return (new Rectangle((int)posX, (int)posY, getWidth(), getHeight()));
	}


}
