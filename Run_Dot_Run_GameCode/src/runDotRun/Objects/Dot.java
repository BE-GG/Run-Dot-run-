package runDotRun.Objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.util.LinkedList;

import GameControl.CollisionDetection;

//import GameControl.Handeler;

public class Dot extends GameObject{
	
	private final int RADIUS = 32;
	private final int GRAVITY = 5;
	private final int MAX_VEL = 10;
	private int initialX;
	private int initialY;
	private CollisionDetection collision;
	//private Handeler handeler;

	public Dot(float posX, float posY, int width, int height, ObjectId id) {
		super(posX, posY, width, height, id);
		initialX = (int) posX;
		initialY = (int) posY;
		collision = new CollisionDetection();
		//collision = new CollisionDetection(null);
		//this.handeler = handeler;
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		posX += velX;
		posY += velY;
		
		if(falling || jumping) {
			posY += GRAVITY;
			
			if(velY >= MAX_VEL)
				velY = MAX_VEL;
		}
		
		collision.collision(objects);
		//detectCollision(objects);
		
	}
	
	public int getRadius() {
		return RADIUS;
	}
	
	public void detectCollision(LinkedList<GameObject> objects){
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp = objects.get(i);
			
			if(temp.getId() == ObjectId.LetterBox) {
				if(getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), 32, 32)) {
					velY = 0;
					posY = temp.getPosY() - RADIUS;
					setFalling(false);
					setJumping(false);
				}
				else
					setFalling(true);
				if(getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), 32, 32)) {
					velY = 0;
					posY = temp.getPosY() + RADIUS;
				}
				if(getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), 32, 32)) {
					velX = 0;
					posX = temp.getPosX() - RADIUS;
				}
				if(getBoundaryL().intersects(temp.getPosX(), temp.getPosY(), 32, 32)) {
					velX = 0;
					posX = temp.getPosX() + RADIUS;
				}
			}
			if(temp.getId() == ObjectId.Spike) {
				if(getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					setPosX(initialX);
					setPosY(initialY);
				}
				
			}
		}
	}
	
	/*public boolean dotIntersectsWithLetter(Rectangle letter) {
		int rectXPos = (int) letter.getX();
		int rectYPos = (int) letter.getY();
		int rectWidth = (int) letter.getWidth();
		int rectHeight = (int) letter.getHeight();
		for(int i = rectXPos; i < rectXPos + 32; ++i) {
			for(int j = rectYPos; j < rectYPos + 32; ++j) {
					if(contains(i ,j)) {
						System.out.println("contains" + "	x= " + i + "	j = " + j);
						return true;
					}
			}
		}
		return false;
		
	}*/
	
	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public int getRADIUS() {
		return RADIUS;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval((int)posX, (int)posY, RADIUS, RADIUS);
		
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.CYAN);
		g2d.draw(getBoundaryTop());
		g2d.draw(getBoundaryR());
		g2d.draw(getBoundaryL());
		g2d.draw(getBoundaryBottom());*/
		
	}
	
	//Checks whether a certain point is inside Dot's boundaries
	/*public boolean contains(int x, int y) {
		//System.out.println("	x= " + x + "	j = " + y);
		return (Math.sqrt( Math.pow((getPosX() - x), 2) + Math.pow((getPosY() - y),2)) <= RADIUS);
	}*/

	public Arc2D getBoundaryBottom() {
		return (new Arc2D.Double((double)posX, (double)posY, (double)RADIUS, (double)RADIUS, 225.0, 90.0, Arc2D.CHORD));
	}
	public Arc2D getBoundaryTop() {
		return (new Arc2D.Double((double)posX, (double)posY, (double)RADIUS, (double)RADIUS, 45.0, 90.0, Arc2D.CHORD));
	}
	public Arc2D getBoundaryR() {
		return (new Arc2D.Double((double)posX, (double)posY, (double)RADIUS, (double)RADIUS, -45.0, 90.0, Arc2D.CHORD));
	}
	public Arc2D getBoundaryL() {
		return (new Arc2D.Double((double)posX, (double)posY, (double)RADIUS, (double)RADIUS, 135.0, 90.0, Arc2D.CHORD));
	}
	

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	
}