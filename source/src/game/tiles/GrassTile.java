package game.tiles;


import game.utils.Assets;

public class GrassTile extends Tile {
	 
	
     public GrassTile (int id) {
    	 super(Assets.grass, id);
     }
     
     public boolean walkable()
     {
    	 return true;
     }
}
