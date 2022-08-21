package game.states;

import java.awt.Graphics2D;

import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.world.World;

public abstract class GameState {
	
	protected GameStateManager gameStateManager;
	protected World world;
	
	public GameState (GameStateManager gameStateManager) {
		
		this.gameStateManager = gameStateManager;
		
	}
	public abstract void update();
	public abstract void input(MouseHandler mouseHandler, KeyHandler keyHandler);
	public abstract void render(Graphics2D g);
	public abstract World getWorld();


}
