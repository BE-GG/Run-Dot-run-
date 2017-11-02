package runDotRun.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Spike extends GameObject{
	
	private Image spikeImage;

	public Spike(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		try {
			image = ImageIO.read(getClass().getResource("/Spike.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spikeImage = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		//image.getScaledInstance(32, 32, Image.SCALE_SMOOTH)
		g.drawImage(spikeImage, (int)getPosX(), (int)getPosY()+3, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}


}
