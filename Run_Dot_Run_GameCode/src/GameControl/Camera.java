package GameControl;
import runDotRun.Objects.GameObject;

public class Camera {
	
	private double posX, posY;


	public Camera(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void update(GameObject dot, int windowWidth, int dotInitialPosX) {
		if(dot.getPosX() >= windowWidth/2) {
			posX = - dot.getPosX() + windowWidth/2;
			//posY = - dot.getPosY() + windowHeight/2;
		}
		else if(dot.getPosX() == dotInitialPosX) {// && dot.getPosY() == dotInitialPosY) {
			posX = - dot.getPosX() + dotInitialPosX;
			//posY = - dot.getPosY() + dotInitialPosY;
		}
		
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
