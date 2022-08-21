package game.states;

import java.util.ArrayList;

import game.graphics.Sprite;
import game.things.Bat;
import game.things.Boss;
import game.things.Enemy;
import game.things.Player;
import game.things.Portal;
import game.things.Skeleton;
import game.things.Slime;
import game.utils.Vector;
import game.world.World;

public class Level1 extends PlayState {

	
	public Level1(GameStateManager gameStateManager) {
		super(gameStateManager);
		if(!gameStateManager.HARD)
		{	
		world = new World("world/world1.txt");
		}
		else {
			world = new World("world/darkworld1.txt");
		}
    	player = new Player(new Vector(100,100), 32);
    	enemies = new ArrayList<Enemy>();
    	enemies.add(new Slime(new Vector(128,256), 20,gameStateManager.HARD));
    	enemies.add(new Slime(new Vector(256,100), 20,gameStateManager.HARD));
    	enemies.add(new Slime(new Vector(300,234), 20,gameStateManager.HARD));
    	enemies.add(new Slime(new Vector(234,100), 20,gameStateManager.HARD));
    	enemies.add(new Skeleton( new Vector(250,100),32,gameStateManager.HARD));
    	enemies.add(new Skeleton( new Vector(312,200),32,gameStateManager.HARD));
    	enemies.add(new Skeleton( new Vector(200,412),32,gameStateManager.HARD));
    	//enemies.add(new Bat( new Vector(300,200), 32,gameStateManager.HARD));
    	//enemies.add(new Boss(new Vector(100,100), 64, gameStateManager.HARD));
    	//portal = new Portal(new Vector(100,100),100);
       
	}
    public void update()
	{
		super.update();
		
		if(enemies.size()==0&&portal==null)
	
			portal = new Portal(new Vector(300,300),100);
		
		if(portal!=null&&portal.isNextLevel())
		{
			gameStateManager.addandpop(gameStateManager.lEVEL2);
			
			gameStateManager.currentState++;
		}
		
	}
}
