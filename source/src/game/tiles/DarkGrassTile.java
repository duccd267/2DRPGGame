package game.tiles;

import game.utils.Assets;

public class DarkGrassTile extends Tile {
    public DarkGrassTile (int id) {
   	 super(Assets.darkgrass, id);
    }
    
    public boolean walkable()
    {
   	 return true;
    }
}
