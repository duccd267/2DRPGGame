package game.tiles;

import game.utils.Assets;

public class DarkDirtTile extends Tile{
    public DarkDirtTile (int id) {
   	 super(Assets.darkdirt, id);
    }
    
    public boolean walkable()
    {
   	 return true;
    }
}
