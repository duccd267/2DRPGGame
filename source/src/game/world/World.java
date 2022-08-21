package game.world;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;


import game.tiles.Tile;


public class World {
	private int width, height;
    private int sX, sY;
    private int[][] tiles;
    
    public World (String path)  {
       loadWorld(path);
    }
    
    public void update() {
    	
    }
    
    public void render(Graphics2D g)
    {
    	for (int x=0;x< width;x++)
    	{
    		for (int y=0;y<height;y++) {
    			getTile(x, y).render(g,x*32,y*32);
    	
    		}
    	}
    }
    
    public Tile getTile(int x, int y)
    {
    	Tile t = Tile.tiles[tiles[x][y]];
    	
    	 if (t==null) return Tile.dirtTile;
    	 return t;
    		 
    }
    
    public void loadWorld (String path) 
    {
      
       int[] tk = LoadWorldTxt.readFile(path);
       width= tk[0];
       height =  tk[1];
       sX =  tk[2];
       sY =  tk[3];
       tiles = new int [width][height];
       for (int y=0;y<height;y++)
       {
    	    for (int x=0; x<width ; x++) {
    	    	tiles [x][y]= tk[(x+y*width) +4];
    	    }
    	    	
       }
       
    }
    
}
