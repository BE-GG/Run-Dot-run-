package runDotRun.Objects;

import java.awt.Graphics;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected float posX, posY;
	protected float velX, velY;
	protected ObjectId id;
	
	public GameObject(float posX, float posY, ObjectId id) {
		this.posX = posX;
		this.posY = posY;
		this.id = id;
		
		velX = 0;
		velY = 0;
	}
	
	public abstract void update(LinkedList<GameObject> objects);
	public abstract void draw(Graphics g);
	
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosX() {
		return posX;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getPosY() {
		return posY;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelX() {
		return velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public float getVelY() {
		return velY;
	}
	
	public ObjectId getId() {
		return id;
	}
	
}
