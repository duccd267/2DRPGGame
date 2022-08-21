package game.graphics;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static  BufferedImage loadImage(String path) 
	{
		BufferedImage sprite = null;
		// TODO Auto-generated method stub
		try {
			sprite = ImageIO.read(ImageLoader.class.getResource(path));
			return sprite;		
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ko load dc");
		}
		return null;
	
	}
}