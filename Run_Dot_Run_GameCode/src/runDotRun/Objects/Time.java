package runDotRun.Objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Time extends GameObject{
	
	private int totalTime;
	private int minutes;
	private int seconds;
	private int minutesUnits;
	private int minutesTens;
	private int secondsUnits;
	private int secondsTens;

	public Time(float posX, float posY, int width, int height, ObjectId id, int totalTime) {
		super(posX, posY, width, height, id);
		this.totalTime = totalTime;
		setMinutes(getTotalTime() / 60); 
		setSeconds(getTotalTime() % 60);
		setMinutesTens(getMinutes()/10);
		setMinutesUnits(getMinutes()%10);
		setSecondsTens(getSeconds()/10);
		setSecondsUnits(getSeconds()%10);
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutesUnits() {
		return minutesUnits;
	}

	public void setMinutesUnits(int minutesUnits) {
		this.minutesUnits = minutesUnits;
	}

	public int getMinutesTens() {
		return minutesTens;
	}

	public void setMinutesTens(int minutesTens) {
		this.minutesTens = minutesTens;
	}

	public int getSecondsUnits() {
		return secondsUnits;
	}

	public void setSecondsUnits(int secondsUnits) {
		this.secondsUnits = secondsUnits;
	}

	public int getSecondsTens() {
		return secondsTens;
	}

	public void setSecondsTens(int secondsTens) {
		this.secondsTens = secondsTens;
	}
	
	public void tickTime() {
		setTotalTime(getTotalTime()-1);
		setMinutes(getTotalTime() / 60); 
		setSeconds(getTotalTime() % 60);
		setMinutesTens(getMinutes()/10);
		setMinutesUnits(getMinutes()%10);
		setSecondsTens(getSeconds()/10);
		setSecondsUnits(getSeconds()%10);
	}
	
	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(503, 50, 180, 70, 50, 50);
		g.setColor(Color.BLACK);
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.drawRoundRect(503, 50, 180, 70, 50, 50);
		((Graphics2D) g).setStroke(new BasicStroke(1));
        String time = "" + getMinutesTens() + getMinutesUnits() + " : " + getSecondsTens() + getSecondsUnits();
        g.setColor(new Color(255,0,0)); 
        g.setFont(new Font("Courrier New", 0, 50)); //Showcard Gothic
        //g.drawString(time, 520, 105);
        g.drawString("" + getMinutesTens(), 520, 105);
        g.drawString("" + getMinutesUnits(), 550, 105);
        g.drawString(":", 590, 100);
        g.drawString("" + getSecondsTens(), 610, 105);
        g.drawString("" + getSecondsUnits(), 640, 105);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
