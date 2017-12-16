package runDotRun.Objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class TimePunishmentClock extends GameObject{

	private Image punishmentClock;
	
	public TimePunishmentClock(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/DecreaseTime.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVelY(0);
		punishmentClock = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(punishmentClock, (int)getPosX(), (int)getPosY()+3, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
