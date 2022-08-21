package game.states;

import java.util.ArrayList;

import game.things.Bat;
import game.things.Boss;
import game.things.Enemy;
import game.things.Player;
import game.things.Portal;
import game.things.Skeleton;
import game.utils.Vector;
import game.world.World;

public class Level3 extends PlayState{
	public Level3 (GameStateManager gameStateManager) {
	super(gameStateManager);
	if(!gameStateManager.HARD)
	{
		world = new World("world/world3.txt");
	}
	else {
		world = new World("world/darkworld3.txt");
	}
    	player = new Player(new Vector(100,100), 32);
    	enemies = new ArrayList<Enemy>();
    	//enemies.add(new Slime(new Vector(200,200), 20));
    	enemies.add(new Boss(new Vector(220,220), 64, gameStateManager.HARD));
    }
	public void update()
	{
		super.update();
		
		if(enemies.size()==0&&portal==null)
			
			portal = new Portal(new Vector(205,205),100);
		
		if(portal!=null&&portal.isNextLevel())
		{
            gameStateManager.addandpop(gameStateManager.WIN);
			
			gameStateManager.currentState++;
		}
	}

}
