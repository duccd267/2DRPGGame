package game.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import game.graphics.ImageLoader;
import game.graphics.Sprite;
import game.things.Enemy;
import game.things.Player;
import game.things.Portal;
import game.utils.Assets;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;
import game.world.World;

public abstract class PlayState extends GameState 
{
    public PlayState (GameStateManager gameStateManager) 
    {
    	super(gameStateManager);
    	popup = ImageLoader.loadImage("/buttons/popup.png");
    	ctn = ImageLoader.loadImage("/buttons/continue.png");
    	ctn1 = ImageLoader.loadImage("/buttons/continue1.png");
    	newgame = ImageLoader.loadImage("/buttons/newgame.png");
    	newgame1 = ImageLoader.loadImage("/buttons/newgame1.png");
    	exit = ImageLoader.loadImage("/buttons/exit.png");
    	exit1 = ImageLoader.loadImage("/buttons/exit1.png");
    	upbtn = ImageLoader.loadImage("/buttons/upbtn.png");
    	upbtn1 = ImageLoader.loadImage("/buttons/upbtn1.png");
    	downbtn = ImageLoader.loadImage("/buttons/downbtn.png");
    	downbtn1 = ImageLoader.loadImage("/buttons/downbtn1.png");
    }
    
    protected Player player;
    protected BufferedImage popup;
    protected BufferedImage ctn;
    protected BufferedImage ctn1;
    protected BufferedImage newgame;
    protected BufferedImage newgame1;
    protected BufferedImage exit;
    protected BufferedImage exit1;
    protected BufferedImage upbtn;
    protected BufferedImage upbtn1;
    protected BufferedImage downbtn;
    protected BufferedImage downbtn1;
    
    protected ArrayList<Enemy> enemies;
    protected int upclick=0;
    protected int downclick=0;
    protected int pause =0;
    protected int keycount=0;
    protected boolean escape=false;
    protected boolean resume=true;
    protected boolean choose=false;
    protected boolean up = false;
    protected boolean down =false;
    
    
    
    protected World world;
    
    protected Portal portal;
    
    public World getWorld() {
		return world;
	}

    
    public void update()
    {   
    	
    	//if(escape) pause++;
    	//if(pause%2==0)
    	if(!escape)	
    	{
    		keycount=999;
    		if(enemies.size()>0)
    		{
    				for(int i=0;i<enemies.size();i++)
    				{
    					enemies.get(i).update(player);	
    					if (enemies.get(i).disappear) enemies.remove(i);
    				}
    		}
    		player.update(enemies);
    		//world.update();
    		if (player.disappear)
    		{
    		gameStateManager.addandpop(gameStateManager.GAMEOVER);
    		}
    		if (portal!=null) {
    		portal.update(player);
    		}
    	}else {
    		if(up) {keycount++;System.out.println(keycount);}
    		if(down) {keycount--;System.out.println(keycount);}
    		if(keycount%3==0&&choose) escape =false;
    		if(keycount%3==2&&choose) gameStateManager.addandpop(gameStateManager.MENU);
    		if(keycount%3==1&&choose) System.exit(0);
		}
    	
    }
    
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler)
    {
    	
    	/*if(keyHandler.esc.down) 
    		{
    		keycount++;
    		if(keycount==0) escape=true; else escape=false;
    		
    		}
    	else keycount=-1;*/
    	
    	if (keyHandler.esc.down) escape =true;
    	if(escape)
    	{
    		if (keyHandler.next.down)
    	   {
    			upclick++;
    			if(upclick==0) up=true; else up=false;
    	   }else upclick=-1;
    		if (keyHandler.pre.down)
     	   {
     			downclick++;
     			if(downclick==0) down=true; else down=false;
     	   }else downclick=-1;	
    	}
    	if (keyHandler.enter.down)
   	    {
   			pause++;
   			if(pause==0) choose=true; else choose=false;
   	    }else pause=-1;
    	player.input(keyHandler, mouseHandler);
    }
    
    public void render(Graphics2D g)
    {
    	
    	g.setColor(Color.BLACK);
  
    	    	world.render(g);
    	    	if(portal != null)
    	    	{
    	    	portal.render(g);
    	    	}

    	player.render(g);
    	if(enemies.size()>0)
    	{
    	for(int i=0;i<enemies.size();i++)
    	{
    		enemies.get(i).render(g);
    	}
    	}
    	if(escape)
    	{
    		g.drawImage(popup,83,100,313,250, null);
    		if(keycount%3==0) g.drawImage(ctn1,170,140,182,50, null); else g.drawImage(ctn,170,140,182,50, null);
    		if(keycount%3==2) g.drawImage(newgame1,165,200,194,51, null); else g.drawImage(newgame,165,200,194,51, null);
    		if(keycount%3==1) g.drawImage(exit1,170,260,130,52, null); else g.drawImage(exit,170,260,130,52, null);
    		if(up) g.drawImage(upbtn1,120,140,45,49, null); else g.drawImage(upbtn,120,140,45,49, null);
    		if(down) g.drawImage(downbtn1,120,260,46,46, null); else g.drawImage(downbtn,120,260,46,46, null);
    	}
    	else 
    	{
    		g.setColor(Color.black);
			g.drawRect(2, 5, 35, 15);
			g.drawString("ESC",7, 18);
    	}
    	
    }
}
