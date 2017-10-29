package runDotRun.Objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Dot extends GameObject{
	
	private final int RADIUS = 32;
	private final int GRAVITY = 0;

	public Dot(float posX, float posY, ObjectId id) {
		super(posX, posY, id);
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		posX += velX;
		posY += velY;
		
		if(falling || jumping) {
			posY += GRAVITY;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval((int)posX, (int)posY, RADIUS, RADIUS);
	}

}
