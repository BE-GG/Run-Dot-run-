package runDotRun.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class LetterBox extends GameObject{

	public LetterBox(float posX, float posY, ObjectId id) {
		super(posX, posY, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)posX, (int)posY, 32, 32);
		
	}

}
