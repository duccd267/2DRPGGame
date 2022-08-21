package game.tiles;

import game.utils.Assets;

public class DarkRockTile extends Tile {
    public DarkRockTile (int id) {
   	 super(Assets.darkrock, id);
    }
    
    public boolean walkable()
    {
   	 return false;
    }
}
