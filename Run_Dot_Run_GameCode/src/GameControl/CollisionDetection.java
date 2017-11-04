package GameControl;

import java.util.LinkedList;
import java.util.Timer;

import runDotRun.Objects.Dot;
import runDotRun.Objects.FadingLetterBox;
import runDotRun.Objects.GameObject;
import runDotRun.Objects.ObjectId;

public class CollisionDetection {
	
	Dot dot;
	
	public CollisionDetection() {
	}
	
	private void findDot(LinkedList<GameObject> objects) {
		for(int i = 0; i < objects.size(); ++i) {
			if(objects.get(i).getId() == ObjectId.Dot) {
				dot = (Dot) objects.get(i);
			}
		}
		
	}
	
	public void collision(LinkedList<GameObject> objects){
	
		findDot(objects);
		
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp = objects.get(i);
			
			if(temp.getId() == ObjectId.LetterBox) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
					dot.setVelY(0);
					dot.setPosY(temp.getPosY() - dot.getRadius());///;
					dot.setFalling(false);
					dot.setJumping(false);
				}
				else
					dot.setFalling(true);
				if(dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
					dot.setVelY(0);
					dot.setPosY(temp.getPosY() + dot.getRadius());
				}
				if(dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
					dot.setVelX(0);
					dot.setPosX(temp.getPosX() - dot.getRadius());
				}
				if(dot.getBoundaryL().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
					dot.setVelX(0);
					dot.setPosX(temp.getPosX() + dot.getRadius());
				}
			}
			if(temp.getId() == ObjectId.Spike) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					dot.setPosX(dot.getInitialX());
					dot.setPosY(dot.getInitialY());
				}
				
			}
			if(temp.getId() == ObjectId.Eraser) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					dot.setPosX(dot.getInitialX());
					dot.setPosY(dot.getInitialY());
				}
				
			}
			if(temp.getId() == ObjectId.FadingLetterBox) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
						dot.setVelY(0);
						dot.setPosY(temp.getPosY() - dot.getRadius());///;
						dot.setFalling(false);
						dot.setJumping(false);
					}
					else
						dot.setFalling(true);
					if(dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
						dot.setVelY(0);
						dot.setPosY(temp.getPosY() + dot.getRadius());
					}
					if(dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
						dot.setVelX(0);
						dot.setPosX(temp.getPosX() - dot.getRadius());
					}
					if(dot.getBoundaryL().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight())) {
						dot.setVelX(0);
						dot.setPosX(temp.getPosX() + dot.getRadius());
					}
					temp.setVelY(8);
				}
					
			}
				
		}
	}
}

