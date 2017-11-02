package runDotRun.Objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Eraser extends GameObject{
	
	Image eraserImage;

	public Eraser(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Eraser.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVelY(0);
		eraserImage = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
	}

	public void update(LinkedList<GameObject> objects) {
		for(int i = 0; i < objects.size(); ++i) {
			if(objects.get(i).getId() == ObjectId.Dot) {
				if(getPosX() - objects.get(i).getPosX() < 100)
					setVelY(8);
			}
		}
		posY += velY;
	}


	public void draw(Graphics g) {
		if(getVelY() == 8)
			g.drawImage(eraserImage, (int)getPosX(), (int)getPosY()+3, null);
	}

	public Rectangle getBounds() {

		return null;
	}


}
