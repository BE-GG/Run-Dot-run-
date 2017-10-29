import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu{
	
	private JFrame mainMenuFrame;
	private MainMenuPanel mainMenuPanel;
	private GameManager game;
	
	public MainMenu(){
		initializeFrame();

	}
	
	public void initializeFrame() {
		mainMenuFrame = new JFrame();
		mainMenuPanel = new MainMenuPanel();
		mainMenuFrame.add(mainMenuPanel);
		game = new GameManager();
		mainMenuFrame.add(game);
		
		mainMenuFrame.setSize(1200, 800);
		//mainMenuFrame.setLayout( null);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setResizable(false);
		mainMenuFrame.setLocationRelativeTo(null);
		mainMenuFrame.setVisible(true);
		
		game.start(); // Start the thread after making the window visible
	}

	public static void main(String[] args) {
		new MainMenu();
	}
}
