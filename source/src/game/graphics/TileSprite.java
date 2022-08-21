package game.graphics;

import java.awt.image.BufferedImage;


public class TileSprite {
	private BufferedImage sheet;

	public TileSprite(BufferedImage sheet) {

		this.sheet = sheet;
	}
	
	public BufferedImage Crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
}
