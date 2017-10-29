import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import runDotRun.Objects.Dot;
import runDotRun.Objects.GameObject;
import runDotRun.Objects.LetterBox;
import runDotRun.Objects.ObjectId;

public class GameManager extends Canvas implements Runnable, KeyListener{
	
	private LevelImageLoader imageLoader;
	private Handeler handeler;
	private boolean running = false;
	private Thread thread;
	private BufferedImage levelImage;
	private Camera camera;
	private int width;
	private int height;
	
	public GameManager(){
		setPreferredSize(new Dimension(1200,800));
		camera = new Camera( 0, 0);
		handeler = new Handeler();
		handeler.addObject(new Dot(96, 96, ObjectId.Dot));
		addKeyListener(this);
		imageLoader = new LevelImageLoader(1);
		setFocusable(true);
		levelImage = imageLoader.getRandomImage();
		loadLevel(levelImage);
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		width = getWidth();
		height = getHeight();
	}
	
	@Override
	public void run() {
		//////This part of the code has not been written by us 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			draw();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		//////This part of the code has not been written by us 
	}
	
	private void update() { //tick
		handeler.update();
		for(int i = 0; i < handeler.objects.size(); ++i) {
			GameObject temp = handeler.objects.get(i);
			
			if(temp.getId() == ObjectId.Dot) {
				camera.update(temp, width);
			}
		}
	}
	
	private void draw() {
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2D = (Graphics2D) g;
		//////////////////////////////////////
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2D.translate(camera.getPosX(), camera.getPosY());
		
		handeler.draw(g);
		
		g2D.translate(-camera.getPosX(), -camera.getPosY());
		////////////////////////////////////////
		g.dispose();
		buffer.show();
	}
	
	public void loadLevel(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < width; ++j) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255)
					handeler.addObject(new LetterBox(i*32, j*32, ObjectId.LetterBox));
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handeler.objects.size(); ++i) {
			GameObject temp = handeler.objects.get(i);
			if(temp.getId() == ObjectId.Dot) {
		        if ( key == KeyEvent.VK_UP) {
		        	temp.setVelY(-4);
		        	temp.setJumping(true);
		        }
		        
		        if ( key == KeyEvent.VK_DOWN)
		        	temp.setVelY(4);
		        
		        if ( key == KeyEvent.VK_LEFT)
		        	temp.setVelX(-4);
		        	
		        if ( key == KeyEvent.VK_RIGHT)
		        	temp.setVelX(4);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handeler.objects.size(); ++i) {
			GameObject temp = handeler.objects.get(i);
			
			if(temp.getId() == ObjectId.Dot) {
		        if ( key == KeyEvent.VK_UP)
		        	temp.setVelY(0);
		        
		        if ( key == KeyEvent.VK_DOWN)
		        	temp.setVelY(0);
		        
		        if ( key == KeyEvent.VK_LEFT)
		        	temp.setVelX(0);
		        	
		        if ( key == KeyEvent.VK_RIGHT)
		        	temp.setVelX(0);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
