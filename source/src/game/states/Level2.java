package game.states;

import java.util.ArrayList;

import game.things.Bat;
import game.things.Enemy;
import game.things.Player;
import game.things.Portal;
import game.things.Skeleton;
import game.things.Slime;
import game.utils.Vector;
import game.world.World;

public class Level2 extends PlayState {

	public Level2(GameStateManager gameStateManager) {
		super(gameStateManager);
		if(!gameStateManager.HARD)
		{
			world = new World("world/world2.txt");
		}
		else {
			world = new World("world/darkworld2.txt");
		}
	    	player = new Player(new Vector(100,100), 32);
	    	enemies = new ArrayList<Enemy>();
	    	//enemies.add(new Slime(new Vector(200,200), 20));
	    	enemies.add(new Skeleton( new Vector(250,100), 32,gameStateManager.HARD));
	    	enemies.add(new Skeleton( new Vector(100,100), 32,gameStateManager.HARD));
	    	enemies.add(new Skeleton( new Vector(140,200), 32,gameStateManager.HARD));
	        enemies.add(new Bat( new Vector(100,150), 32,gameStateManager.HARD));
	        enemies.add(new Bat( new Vector(300,200), 32,gameStateManager.HARD));
	        enemies.add(new Bat( new Vector(400,100), 32,gameStateManager.HARD));
	        
		
	}
	public void update()
	{
		super.update();
		
		if(enemies.size()==0&&portal==null)
			
			portal = new Portal(new Vector(260,300),100);
		
		if(portal!=null&&portal.isNextLevel())
		{
            gameStateManager.addandpop(gameStateManager.LEVEL3);
			
			gameStateManager.currentState++;
		}
		
	}
	
}
