package game.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.utils.Vector;

public class Tile {
	
	public static  Tile[] tiles = new Tile[20];
	public static Tile  grassTile = new GrassTile(0);
	public static Tile  dirtTile = new DirtTile(1);
	public static Tile  rockTile = new RockTile(2);
	public static Tile sandTile = new SandTile(3);
	public static Tile darkGrassTile = new DarkGrassTile(4);
	public static Tile darkDirtTile = new DarkDirtTile(5);
	public static Tile darkRockTile = new DarkRockTile(6);
	public static Tile darkSandTile = new DarkSandTile(7);
	
	
	
	protected BufferedImage texture;
	protected int id;
	public int width=32, height=32;
	
   public Tile(BufferedImage texture,int id)
   {
	   this.texture=texture;
	   this.id=id; 
	   tiles[id]=this;
	 
   }
   
 
    public int getID()
    {
    	return id;
    }
    
    public void update()
    {
    	
    }
    public void render(Graphics2D g,int x, int y )
    {
    	g.drawImage(texture, x, y, width,height,null);
    }
    
    public boolean walkable() {
    	return true;
    }
}
