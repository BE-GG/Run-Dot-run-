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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import runDotRun.Objects.CheckPoint;
import runDotRun.Objects.Dot;
import runDotRun.Objects.Eraser;
import runDotRun.Objects.ExtraLife;
import runDotRun.Objects.FadingLetterBox;
import runDotRun.Objects.FinishPoint;
import runDotRun.Objects.GameObject;
import runDotRun.Objects.LetterBox;
import runDotRun.Objects.ObjectId;
import runDotRun.Objects.Spike;
import runDotRun.Objects.Time;
import runDotRun.Objects.TimeBonus;
import runDotRun.Objects.TimePunishmentClock;

/*
 * 1)Level'lara soz bul
 * 2)credits and settings
 * 3)Main screen animasyon?
 * 4)
 * 6)music
 * 7)Curvy Letters!?!?
 */

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
	private boolean gameWon;
	private Dot dot;
	private int level;
	private BufferedImage lives;
	private Image miniLives;
	private int finishPointX, finishPointY;
	private JButton pauseGame;
	private boolean firstCreation;
	private Time time;
	//private CollisionDetection collision;
	private String savedDataFile;
	private Clip clip;
	private int UnlockedLevel;
	private boolean pause;
	
	public GameManager(){
		setPreferredSize(new Dimension(1200,800));
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			lives = ImageIO.read(getClass().getResource("/life.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		miniLives = lives.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		gameWon = false;
		//objects = new LinkedList<GameObject>();
		//detectCollision = new CollisionDetection();
		camera = new Camera( 0, 0);
		addKeyListener(this);
		savedDataFile = "" + getClass().getResource("SavedData.txt");
		setFocusable(true);

		isGameOver = false;
		pauseGame = new JButton("Pause");
		setFocusable(true);
		requestFocusInWindow();
		//time = new Time(0, 0, 0, 0, ObjectId.Time, 120); // 2 minutes
		//addGameObject(time);
		//collision = new CollisionDetection();
	}
	
	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public synchronized void start(int level){
		if(running)
			return;
		
		objects = new LinkedList<GameObject>();
		time = new Time(0, 0, 0, 0, ObjectId.Time, 120); // 2 minutes
		addGameObject(time);
		//////////////////////////////////////////////
		  FileReader file;
			try {
				file = new FileReader("savedLevelData/SavedLevel.txt");
				BufferedReader reader = new BufferedReader(file);
				
				String level_s = "";
				try {
					String line = reader.readLine();
					while(line != null)
					{
						level_s += line;
						line = reader.readLine();
					}
					UnlockedLevel = Integer.parseInt(level_s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//////////////////////////////////////////////
		
		//////////////////////////////////////////////
		File soundFile = new File("Sound/jumping4.wav");
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				clip = AudioSystem.getClip();
				clip.open(audioIn);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//////////////////////////////////////////////
		
		
		if(level == 1)
		{
			try {
				backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(level == 2)
		{
			try {
				backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(level == 3)
		{
			try {
				backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(level == 4)
		{
			try {
				backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(level == 5)
		{
			try {
				backgroundImage = ImageIO.read(getClass().getResource("/backgroundImageGamePlay1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.level = level;
		imageLoader = new LevelImageLoader(level);
		levelImage = imageLoader.getRandomImage();
		loadLevel(levelImage);
		pause = false;
		running = true;
		thread = new Thread(this);
		thread.start();
		width = getWidth();
		height = getHeight();
	}
	
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
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
		System.out.println("here1");
		
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				if(!isPause())
					update();
				updates++;
				delta--;
			}
			if(!isPause())
				draw();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
			//System.out.println("here2");
		}
	}
		//////This part of the code has not been written by us 
	
	
	private void updateTimeRemaining() {
		if(clockTick == 40) {
			clockTick = 0;
			//System.out.println("Time is ticking?");
			time.tickTime();
		}
	}
	
	public int getTimeRemaining() {
		return remainingTime;
	}
	
	private void update() { 
		checkGameOver();
		if(!isGameOver) {
			updateTimeRemaining();
		}
		clockTick++;
		
		if(!dot.getjumping())
			clip.stop();
		
		//System.out.println(dot.getPosX());
		//collision.collision(objects);
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
		g2D.translate(camera.getPosX(), camera.getPosY());
		
		paint(g);
		//handeler.draw(g);
		
		for(int i = 0; i < objects.size(); ++i) {
			GameObject temp;
			temp = objects.get(i);
			if(temp.getId() != ObjectId.Time)
				temp.draw(g); // Bir kere yapsa yeter !!!!!!!!!!!!!!!!!!!!!!!!!
		}
		
		
		g2D.translate(-camera.getPosX(), -camera.getPosY());
		
        if(miniLives != null) {
        	int x = 32;
	    	for(int i = 0; i < x*dot.getLives(); i += x)
	    		g.drawImage(miniLives, i,40, this); // Bir kere yapsa yeter !!!!!!!!!!!!!!!!!!!!!!!!!
        }
		
        time.draw(g);
		////////////////////////////////////////
		g.dispose();
		buffer.show();
	}
	
    public void paint(Graphics g) { // Bir kere yapsa yeter !!!!!!!!!!!!!!!!!!!!!!!!!
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
				if(red == 255 && green == 0 && blue == 0){
					dot = new Dot(i*32, j*32, 32, 32, ObjectId.Dot);
					addGameObject(dot);
					dotInitialPosX = dot.getInitialX();
					dotInitialPosY = dot.getInitialY();
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
				if(red == 76 && green == 255 && blue == 0)
				{
					addGameObject(new FinishPoint(i*32, j*32, 32, 32, ObjectId.FinishPoint));
					finishPointX = i*32;
					finishPointY = j*32;
				}
				if(red == 255 && green == 0 && blue == 255)
				{
					addGameObject(new ExtraLife(i*32, j*32, 32, 32, ObjectId.ExtraLife));
				}
				if(red == 0 && green == 255 && blue == 255)
				{
					addGameObject(new TimeBonus(i*32, j*32, 32, 32, ObjectId.TimeBonus));
				}
				if(red == 100 && green == 0 && blue == 100)
				{
					addGameObject(new TimePunishmentClock(i*32, j*32, 32, 32, ObjectId.TimePunishmentClock));
				}
				if(red == 100 && green == 200 && blue == 100)
				{
					addGameObject(new CheckPoint(i*32, j*32, 32, 32, ObjectId.CheckPoint));
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
			        temp.setVelY(-8);
			        temp.setJumping(true);
			        clip.setFramePosition(0); // rewind to the beginning
			        clip.start();
			        //clip.loop(Clip.LOOP_CONTINUOUSLY);
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
					temp.setPosX(dot.getInitialX());
					temp.setPosY(dot.getInitialY());
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
		if(time.getTotalTime() <= 0 || (dot.getLives() == 0) || (dot.getPosX() == finishPointX && dot.getPosY() == finishPointY))
		{
			isGameOver = true;
			gameWon = dot.isReachedFinishPoint();
	        //String text = "meow";
			if(gameWon && this.level != 4 && this.level != 5 && (this.level >= UnlockedLevel)) {
		        BufferedWriter output = null;
		        try {
		            //File file = new File("SavedLevel.txt");
		            output = new BufferedWriter(new FileWriter("savedLevelData/SavedLevel.txt"));
		            output.write("" + (this.level+1));
		        } catch ( IOException e ) {
		            e.printStackTrace();
		        } finally {
		          if ( output != null ) {
		            try {
						output.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		          }
		        }
			}
			running = false;
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

	public boolean isRunning() {
		return running;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
