package runDotRun.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected float posX, posY;
	protected float velX, velY;
	protected int height, width;
	protected boolean jumping, falling;
	protected BufferedImage image;
	protected ObjectId id;
	public GameObject(float posX, float posY, int width, int height, ObjectId id) {
		this.posX = posX;
		this.posY = posY;
		this.id = id;
		this.jumping = false;
		this.falling = true;
		this.height = height;
		this.width = width;
		
		velX = 0;
		velY = 0;
	}
	
	public abstract void update(LinkedList<GameObject> objects);
	public abstract void draw(Graphics g);
	public abstract Rectangle getBounds();
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
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
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean getFalling() {
		return falling;
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public boolean getjumping() {
		return jumping;
	}
}
