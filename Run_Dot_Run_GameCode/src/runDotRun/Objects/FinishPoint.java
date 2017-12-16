package runDotRun.Objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.LinkedList;

public class FinishPoint extends GameObject{
	
	private int radius;
	
	public FinishPoint(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		radius = 32;
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0));
		g.setColor(Color.BLACK);
		g.drawOval((int)posX, (int)posY, radius, radius);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
