package game.states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import game.GamePanel;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;

public class GameStateManager {
	
	public static final int LEVEL1 = 1;
	public static final int lEVEL2 = 2;
	public static final int LEVEL3 = 3;
	public static final int MENU = 0;
	public static final int GAMEOVER = 4;
	public static final int WIN = 5;
	public static int currentState = 0;
	public static boolean HARD;
	
	
	
	private static LinkedList<GameState> states ;
	 
	
	
    public GameStateManager ()
     {
    	
    	 states = new LinkedList<GameState>();
    	 states.add(new MenuState(this));
    	 //states.add(new Level1(null)); 
    	 //states.add(new Level2(null));
    	 currentState = 0;
     }
    
    public static int getCurrentStateID()
    {
    	return currentState;
    }
    public void setCurrentState(int i)
    {
    	currentState=i;
    }
    public static GameState getCurrentState()
    {
    	return states.get(0);
    }
     
     public void pop (int state)
     {
    	 states.remove(state);
     }
     
     public void add (int state)
     {
    	 if (state == LEVEL1) {states.add(new Level1(this));}
    	 if (state == lEVEL2){states.add(new Level2(this));}
    	 if (state == LEVEL3) {states.add(new Level3(this));}
    	 if (state == MENU) {states.add(new MenuState(this));}
 
    	 if (state == GAMEOVER) {states.add(new GameOverState(this));}
    	 if (state == WIN) {states.add(new WinState(this));}
     }
     
     public void addandpop(int state)
     {
    	 
    	 add(state);
    	 states.remove(0);
     }
  
     public void update()
     {

    	 
    
    	
    	 // static
    	  //for(int i=0;i<states.size(); i++)
    	  //{
    		//  states.get(i).update(time);//@@
    	  //}
    	 states.get(0).update();
     }
     
     public void input( MouseHandler mouseHandler, KeyHandler keyHandler) {
    	/* for(int i=0;i<states.size(); i++)
   	  {
   		  states.get(i).input(mouseHandler, keyHandler);
   	  }*/
    	 
    	 states.get(0).input(mouseHandler, keyHandler);
	     }
     
     public void render(Graphics2D g)
     {
      	/* for(int i=0;i<states.size(); i++)
   	  {
   		  states.get(i).render(g);
   	  }*/
    	 states.get(0).render(g);
     }
}
