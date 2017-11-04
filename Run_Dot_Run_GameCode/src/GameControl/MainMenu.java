package GameControl;
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
	private JPanel mainPanel;
	
	public MainMenu(){
		mainPanel = new JPanel();
		initializeFrame();

	}
	
	public void initializeFrame() {
		
		mainMenuFrame = new JFrame();
		//mainMenuPanel = new MainMenuPanel();
		//mainMenuFrame.add(mainMenuPanel);
		game = new GameManager(1);
		//mainMenuFrame.add(game);
		//mainPanel.add(game);
		//mainPanel.add(new PausePanel());
		mainMenuFrame.add(game);
		
		mainMenuFrame.setSize(1200, 800);
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
