import java.awt.Graphics;
import java.util.LinkedList;

import runDotRun.Objects.GameObject;

public class Handeler {
	
	private LinkedList<GameObject> objects;
	private GameObject temp;
	
	public Handeler() {
		objects = new LinkedList<GameObject>();
	}
	
	public void update() {
		for(int i = 0; i < objects.size(); ++i) {
			temp = objects.get(i);
			
			temp.update(objects);
		}
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < objects.size(); ++i) {
			temp = objects.get(i);
			
			temp.draw(g);
		}
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
}
