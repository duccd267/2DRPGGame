package game.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.ImageLoader;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.world.World;

public class WinState extends GameState{
	
   private boolean restart=false;
   private BufferedImage backGround;
   public WinState(GameStateManager gameStateManager)
   {
	   super(gameStateManager);
	   backGround = ImageLoader.loadImage("/entity/Winbackground.jpg");
   }

@Override
public void update() {
	if(restart)
	{
	gameStateManager.HARD=false;
	gameStateManager.addandpop(gameStateManager.MENU);
	//gameStateManager.setCurrentState(gameStateManager.MAP);
     gameStateManager.currentState++;
    }
	
}

@Override
public void input(MouseHandler mouseHandler, KeyHandler key) {
	if(key.enter.down) {
		restart= true;
	}
	
}

@Override
public void render(Graphics2D g) {
	// TODO Auto-generated method stub
	g.drawImage(backGround,0,0,480,480, null);
	g.setColor(Color.WHITE);
	g.drawString(">--Press Enter--<", 195, 440);
	
}

@Override
public World getWorld() {
	// TODO Auto-generated method stub
	return null;
}
}
