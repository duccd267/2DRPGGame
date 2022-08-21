package game.tiles;


import game.utils.Assets;

public class RockTile extends Tile {
     public RockTile (int id) {
    	 super(Assets.rock, id);
     }
     
     public boolean walkable()
     {
    	 return false;
     }
}
