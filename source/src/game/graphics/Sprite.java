package game.graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.utils.Vector;



public class Sprite {
	private BufferedImage SPRITESHEET = null;
	private BufferedImage[][] spriteArray;
	private final int TILE_SIZE=32;
	public int width;
	public int height;
	private int wSprite;
	private int hSprite;
	
	public Sprite (String path)
	{
		width = TILE_SIZE;
		height =TILE_SIZE;
		
		System.out.println("loading" + path +"....");
		
		SPRITESHEET = ImageLoader.loadImage(path);
		
		wSprite = SPRITESHEET.getWidth()/width;
		hSprite = SPRITESHEET.getHeight()/height;
		
		loadSpriteArray();
	}
	public Sprite (String path, int width, int height)
	{
		this.width =width;
		this.height = height;
		
        System.out.println("loading" + path +"....");
		
		SPRITESHEET = ImageLoader.loadImage(path);
		
		wSprite = SPRITESHEET.getWidth()/width;
		hSprite = SPRITESHEET.getHeight()/height;
		
		loadSpriteArray();
	}
	public Sprite ()
	{
		
	}
	
	public void setSize(int width, int height)
	{
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int width)
	{
		this.width = width;
		wSprite= SPRITESHEET.getWidth()/width;
	}
	
    public void setHeight(int height)
    {
    	this.height = height;
    	hSprite = SPRITESHEET.getHeight()/height;
    }
    
    public int getWidth()
    {
    	return width;
    }
    
    public int getHeight()
    {
    	return height;
    }
    
    private BufferedImage loadSprite(String path)
    {
    	BufferedImage	sprite = null;
    	try {sprite = ImageIO.read(new File(path));} catch(Exception e){
    		System.out.println("ERROR cannot find " + path);
    	}
    	return sprite;
    }
    
    public void loadSpriteArray()
    {
    	spriteArray = new BufferedImage[hSprite][wSprite];
    	
    	
    		 for (int y=0; y<hSprite;y++)
    		 {
    			 for (int x=0; x<wSprite;x++)
    		    	{
    			 spriteArray[y][x]=getSprite(x,y);
    		 }
    	}
    }
    
    public BufferedImage getSpriteSheet()
    {
    	return SPRITESHEET;
    }
    
    public BufferedImage getSprite (int x, int y)
    {
    	return SPRITESHEET.getSubimage(x*width, y*height, width, height);
    }
    
    public BufferedImage[] getSpriteArray(int i)
    {
    	return spriteArray[i];
    }
    
    public BufferedImage[][] getSpriteArray2(int i)
    {
    	return spriteArray;
    }
    
    public void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector pos, int width, int height, int xOffset, int yOffset)
    {
    	float x= pos.x, y= pos.y;
    	
    	for(int i=0;i<img.size();i++)
    	{
    		if(img.get(i)!=null)
    		{
    			g.drawImage(img.get(i),(int)x, (int)y, width, height, null);
    		}
    		x+= xOffset;
    		y+=yOffset; //@@
    	}
    	
    }


}
