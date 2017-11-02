package GameControl;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LevelImageLoader {
	
	private final int IMAGE_NUM = 5;
	private BufferedImage[] images;
	private int level;
	
	public LevelImageLoader(int level){
		this.level = level;
		images = new BufferedImage[IMAGE_NUM];
		setImages(this.level);
	}
	
	public void setImages(int level) {
		//The images array will be filled according to the level
		if(level == 1) { 
			try {
				images[0] = ImageIO.read(getClass().getResource("/Hello.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				images[1] = ImageIO.read(getClass().getResource("/Level1Image1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(level == 2) {
			
		}
		else if(level == 3) {
			
		}
		else if(level == 4) {
			
		}
		else {
			
		}
	}
	
	public BufferedImage getRandomImage() {
		//return random image from array
		//return images[(int )(Math.random() * 4)];
		return images[1];
	}
}
