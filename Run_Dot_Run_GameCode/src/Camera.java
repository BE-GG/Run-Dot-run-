import runDotRun.Objects.GameObject;

public class Camera {
	
	private double posX, posY;


	public Camera(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void update(GameObject dot, int windowWidth) {
		posX = - dot.getPosX() + windowWidth/2;
	}
	
	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
}