package game.tiles;

import game.utils.Assets;

public class DirtTile extends Tile {
     public DirtTile (int id) {
    	 super(Assets.dirt, id);
     }
     
     public boolean walkable()
     {
    	 return true;
     }
}
