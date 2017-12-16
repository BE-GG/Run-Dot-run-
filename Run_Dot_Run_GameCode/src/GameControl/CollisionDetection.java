package GameControl;

import java.util.LinkedList;
import java.util.Timer;

import runDotRun.Objects.Dot;
import runDotRun.Objects.FadingLetterBox;
import runDotRun.Objects.GameObject;
import runDotRun.Objects.ObjectId;
import runDotRun.Objects.Time;

public class CollisionDetection {
	
	private Dot dot;
	private Time time;
	private boolean gameWon;
	
	public CollisionDetection() {
		gameWon = false;
	}
	
	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	private void findDot(LinkedList<GameObject> objects) {
		for(int i = 0; i < objects.size(); ++i) {
			if(objects.get(i).getId() == ObjectId.Dot) {
				dot = (Dot) objects.get(i);
			}
		}
	}
	
	private void findTime(LinkedList<GameObject> objects) {
		for(int i = 0; i < objects.size(); ++i) {
			if(objects.get(i).getId() == ObjectId.Time) {
				time = (Time) objects.get(i);
			}
		}
		
	}
	
	public void collision(LinkedList<GameObject> objects){
	
		findDot(objects);
		findTime(objects);
		
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
					if(dot.getLives() > 0)
						dot.setLives(dot.getLives()-1);
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
					if(dot.getLives() > 0)
						dot.setLives(dot.getLives()-1);
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
					//objects.remove(temp);
				}
					
			}
			if(temp.getId() == ObjectId.FinishPoint) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					dot.setPosX(temp.getPosX());
					dot.setPosY(temp.getPosY());
					gameWon = true;
				}
					
			}
			if(temp.getId() == ObjectId.ExtraLife) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					if(dot.getLives() < 3) {
						dot.setLives(dot.getLives()+1);
						objects.remove(temp);
					}
				}
				
			}
			if(temp.getId() == ObjectId.TimeBonus) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					if(time.getTotalTime() < 120) {
						if (time.getTotalTime() + 10 < 120) {
							time.setTotalTime(time.getTotalTime() + 10);
							objects.remove(temp);
						}
						else {
							time.setTotalTime(120);
							objects.remove(temp);
						}
					}
				}
				
			}
			if(temp.getId() == ObjectId.TimePunishmentClock) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					if (time.getTotalTime() - 10 > 0) {
						time.setTotalTime(time.getTotalTime() - 10);
						objects.remove(temp);
					}
					else {
						time.setTotalTime(0);
						objects.remove(temp);
					}
				}
				
			}
			if(temp.getId() == ObjectId.CheckPoint) {
				if(dot.getBoundaryBottom().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) || 
						dot.getBoundaryTop().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()) ||
						dot.getBoundaryR().intersects(temp.getPosX(), temp.getPosY(), temp.getWidth(), temp.getHeight()))
				{
					dot.setInitialX((int)temp.getPosX());
					dot.setInitialY((int)temp.getPosY());
					objects.remove(temp);
				}
				
			}
				
		}
	}
}

