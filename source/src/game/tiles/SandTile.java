package game.tiles;

import game.utils.Assets;

public class SandTile extends Tile {
    public SandTile (int id) {
   	 super(Assets.sand, id);
    }
    
    public boolean walkable()
    {
   	 return true;
    }
}
