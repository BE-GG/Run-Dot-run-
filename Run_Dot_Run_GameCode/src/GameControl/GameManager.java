package GameControl;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Paint;
import java.awt.PopupMenu;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import runDotRun.Objects.Dot;
import runDotRun.Objects.Eraser;
import runDotRun.Objects.FadingLetterBox;
import runDotRun.Objects.GameObject;
import runDotRun.Objects.LetterBox;
import runDotRun.Objects.ObjectId;
import runDotRun.Objects.Spike;

public class GameManager extends Canvas implements Runnable, KeyListener{
	
	private LevelImageLoader imageLoader;
	private LinkedList<GameObject> objects;
	private boolean running = false;
	private Thread thread;
	private BufferedImage levelImage;
	private Camera camera;
	private int width;
	private int height;
	private int dotInitialPosX;
	private int dotInitialPosY;
	private int remainingTime;
	private int minutes;
	private int seconds;
	private BufferedImage backgroundImage;
	private int clockTick = 0;
	private boolean isGameOver;
	private CollisionDetection detectCollision;
	private Dot dot;
	private int level;
	private BufferedImage lives;
	private Image miniLives;
	
	public GameManager(int level){
		setPreferredSize(new Dimension(1200,800));
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lives = ImageIO.read(getClass().getResource("/life.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		miniLives = lives.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		this.level = level;
		objects = new LinkedList<GameObject>();
		detectCollision = new CollisionDetection();
		remainingTime = 120;
		camera = new Camera( 0, 0);
		addKeyListener(this);
		imageLoader = new LevelImageLoader(level);
		setFocusable(true);
		levelImage = imageLoader.getRandomImage();
		loadLevel(levelImage);
		isGameOver = false;
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
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		//////This part of the code has not been written by us 
	}
	
	private void updateTimeRemaining() {
		if(clockTick == 40) {
			remainingTime--;
			minutes = remainingTime / 60;
			seconds = remainingTime % 60;
			clockTick = 0;
		}
	}
	
	public int getTimeRemaining() {
		return remainingTime;
	}
	
	private void update() { //tick
		checkGameOver();
		if(!isGameOver) {
			updateTimeRemaining();
		}
		clockTick++;
		//System.out.println(dot.getPosX());
		//detectCollision.collision(objects);
		//handeler.update();
		checkOutOfScreen();
		
		
		//System.out.println(dot.getPosX()%1200);
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp = objects.get(i);
			
			if(temp.getId() == ObjectId.Dot) {
				camera.update(temp, width, dotInitialPosX);
			}
		}
		
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp;
			temp = objects.get(i);
			
			temp.update(objects);
		}
	}
	
	private void draw() {
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2D = (Graphics2D) g;
		//////////////////////////////////////
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2D.translate(camera.getPosX(), camera.getPosY());
		
		paint(g);
		//handeler.draw(g);
		
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp;
			temp = objects.get(i);
			temp.draw(g);
		}
		
		
		g2D.translate(-camera.getPosX(), -camera.getPosY());
		
        if(miniLives != null) {
        	int x = 32;
	    	for(int i = 0; i < x*dot.getLives(); i += x)
	    		g.drawImage(miniLives, i,40, this);
        }
		
		g.setColor(Color.WHITE);
		g.fillRoundRect(503, 50, 160, 70, 50, 50);
		g.setColor(Color.BLACK);
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.drawRoundRect(503, 50, 160, 70, 50, 50);
		((Graphics2D) g).setStroke(new BasicStroke(1));
        String time = "" + minutes + " : " + seconds;
        g.setColor(new Color(255,0,0));
        g.setFont(new Font("Showcard Gothic", 0, 50));
        g.drawString(time, width/2 - 80, 105);
		////////////////////////////////////////
		g.dispose();
		buffer.show();
	}
	
    public void paint(Graphics g) {
        super.paint(g);
        if (backgroundImage != null) {
        	int x = backgroundImage.getWidth();
        	for(int i = 0; i < x*10; i += x)
        		g.drawImage(backgroundImage, i,0, this);
        }
    }
	
	public void loadLevel(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if(red == 255 && green == 0 && blue == 0) {
					dot = new Dot(i*32, j*32, 32, 32, ObjectId.Dot);
					addGameObject(dot);
					dotInitialPosX = i*32;
					dotInitialPosY = j*32;
				}
				if(red == 255 && green == 255 && blue == 255)
					addGameObject(new LetterBox(i*32, j*32, 32, 32, ObjectId.LetterBox));
				if(red == 0 && green == 38 && blue == 255)
					addGameObject(new Spike(i*32, j*32, 32, 32, ObjectId.Spike));
				if(red == 0 && green == 255 && blue == 0)
				{
					addGameObject(new Eraser(i*32, j*32, 64, 64, ObjectId.Eraser));
				}
				if(red == 255 && green == 255 && blue == 0)
				{
					addGameObject(new FadingLetterBox(i*32, j*32, 32, 32, ObjectId.FadingLetterBox));
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp = objects.get(i);
			if(temp.getId() == ObjectId.Dot) {
		        if ( !temp.getjumping() && key == KeyEvent.VK_SPACE) {
			        temp.setVelY(-10);
			        temp.setJumping(true);
		        }
		        
		        /*if ( key == KeyEvent.VK_DOWN)
		        	temp.setVelY(4);*/
		        
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
		
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp = objects.get(i);
			
			if(temp.getId() == ObjectId.Dot) {
		        if ( key == KeyEvent.VK_SPACE) {
		        	//temp.setJumping(false);
		        	temp.setVelY(0);
		        }
		        
		        /*if ( key == KeyEvent.VK_DOWN)
		        	temp.setVelY(0);*/
		        
		        if ( key == KeyEvent.VK_LEFT)
		        	temp.setVelX(0);
		        	
		        if ( key == KeyEvent.VK_RIGHT)
		        	temp.setVelX(0);
			}
		}
		
	}
	
	public void checkOutOfScreen() {
		for(int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			if(temp.getId() == ObjectId.Dot) {
				if(temp.getPosY() > height) {
					temp.setPosX(dotInitialPosX);
					temp.setPosY(dotInitialPosY);
					if(dot.getLives() > 0)
						dot.setLives(dot.getLives()-1);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void checkGameOver() {
		if(remainingTime <= 0 || (dot.getLives() == 0))
		{
			isGameOver = true;
			running = false;
			//running = true;
		}
	}
	
	public boolean getGameOver() {
		return isGameOver;
	}
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public void addGameObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeGameObject(GameObject object) {
		objects.remove(object);
	}

}
