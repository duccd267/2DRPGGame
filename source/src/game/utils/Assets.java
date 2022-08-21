package game.utils;

import java.awt.image.BufferedImage;

import game.graphics.ImageLoader;
import game.graphics.TileSprite;

import java.io.File;
import javax.imageio.ImageIO;

public class Assets {
	public static BufferedImage player, dirt, grass, rock,darkgrass, darkdirt, darkrock,sand, darksand;
	public static void init ()
	{
		TileSprite sheet = new TileSprite(ImageLoader.loadImage("/tile/master-tileset.png"));
		grass = sheet.Crop(0,128, 64, 64);
		dirt = sheet.Crop(0,192, 64, 64);
		rock = sheet.Crop(0,0,64,64);
		sand =sheet.Crop(0,64, 64, 64);
		darkgrass = sheet.Crop(448,128, 64, 64);
		darkdirt = sheet.Crop(448,192, 64, 64);
		darkrock = sheet.Crop(448,0, 64, 64);
		darksand=sheet.Crop(448,64, 64, 64);
		
		
	}
}
