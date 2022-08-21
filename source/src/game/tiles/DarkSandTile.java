package game.tiles;

import game.utils.Assets;

public class DarkSandTile extends Tile{
    public DarkSandTile (int id) {
   	 super(Assets.darksand, id);
    }
    
    public boolean walkable()
    {
   	 return true;
    }
}
