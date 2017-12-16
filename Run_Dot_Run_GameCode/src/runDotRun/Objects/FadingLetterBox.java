package runDotRun.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class FadingLetterBox extends LetterBox{
	
	
	public FadingLetterBox(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)posX, (int)posY, 32, 32);
		g.setColor(Color.WHITE);
		g.drawRect((int)posX, (int)posY, getWidth(), getHeight());
	}
	
	public void update(LinkedList<GameObject> objects) {
		posY += velY;
	}
}
