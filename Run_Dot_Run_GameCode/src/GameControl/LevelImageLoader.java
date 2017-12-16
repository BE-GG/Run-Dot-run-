package GameControl;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LevelImageLoader {
	
	private final int IMAGE_NUM = 3;
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
			for(int i = 0; i < IMAGE_NUM; i++) {
				try {
					images[i] = ImageIO.read(getClass().getResource("/Level" + level + "Image" + (i+1) + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(level == 2) {
			for(int i = 0; i < IMAGE_NUM; i++) {
				try {
					images[i] = ImageIO.read(getClass().getResource("/Level" + level + "Image" + (i+1) + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(level == 3) {
			for(int i = 0; i < IMAGE_NUM; i++) {
				try {
					images[i] = ImageIO.read(getClass().getResource("/Level" + level + "Image" + (i+1) + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(level == 4) {
			for(int i = 0; i < IMAGE_NUM; i++) {
				try {
					images[i] = ImageIO.read(getClass().getResource("/Level" + level + "Image" + (i+1) + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(level == 5) {
			for(int i = 0; i < IMAGE_NUM; i++) {
				try {
					images[i] = ImageIO.read(getClass().getResource("/Level" + 5 + "Image" + 1 + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public BufferedImage getRandomImage() {
		//return random image from array
		return images[(int )(Math.random() * 3)];
	}
}
