package game.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.ImageLoader;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.world.World;

public class MenuState extends GameState {
	
	private BufferedImage backGround;
	private BufferedImage leftbtn;
	private BufferedImage rightbtn;
	private BufferedImage RPG;
	
	private boolean hard=false;
	private boolean easy=false;
	private boolean start=false;
	private boolean menu=true;
	private boolean control=false;
	private boolean left = false;
	private boolean right = false;
	private boolean choose =false;
	private int leftclick=0;
	private int rightclick=0;
	private int chooseclick=0;
	private int keycount1;
	private int keycount2;
	
	public MenuState (GameStateManager gameStateManager)
	{
		super(gameStateManager);
		backGround = ImageLoader.loadImage("/entity/Background.png");
		leftbtn = ImageLoader.loadImage("/buttons/leftbtn.png");
		rightbtn = ImageLoader.loadImage("/buttons/rightbtn.png");
		RPG = ImageLoader.loadImage("/buttons/RPG.png");
		menu=true;
		keycount1=999;
		keycount2=400;
		easy=false;
		hard=false;
	}
    
	
	@Override
	public void update() {
		if(menu)
		{
			keycount2=400;
			if(left) {keycount1++;System.out.println(keycount1);}
    		if(right) {keycount1--;System.out.println(keycount1);}
    		if(keycount1%3==0&&choose) {start =true; menu=false;}
    		if(keycount1%3==1&&choose) {control =true; menu=false;}
    		if(keycount1%3==2&&choose) System.exit(0);
			
		}
		else {
			if(start)
			{	
				keycount1=999;
				if(left) {keycount2++;System.out.println(keycount2);}
	    		if(right) {keycount2--;System.out.println(keycount2);}
	    		if((keycount2%2==0)&&choose) {easy = true;System.out.println("easy");}
	    		if((keycount2%2==1)&&choose) hard = true;	
			}
			if(control)
			{
				
			}
		}
		if(easy)
		{
			gameStateManager.HARD=false;
			gameStateManager.addandpop(gameStateManager.LEVEL1);
			//gameStateManager.setCurrentState(gameStateManager.MAP);
			gameStateManager.currentState++;}
		if(hard)
		{
			gameStateManager.HARD=true;
			gameStateManager.addandpop(gameStateManager.LEVEL1);
			//gameStateManager.setCurrentState(gameStateManager.MAP);
			gameStateManager.currentState++;
		}
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		
			
			if(start)
			{
		    	if(key.esc.down)
		    	{
		    		start=false; menu=true;
		    	}
			}
			if(control)
			{
				if(key.esc.down)
				{
					control=false; menu=true;
				}
			}
			if (key.left.down)
	    	   {
	    			leftclick++;
	    			if(leftclick==0) left=true; else left=false;
	    	   }else leftclick=-1;
	    		if (key.right.down)
	     	   {
	     			rightclick++;
	     			if(rightclick==0) right=true; else right=false;
	     	   }else rightclick=-1;	
			if (key.enter.down)
	   	    {
	   			chooseclick++;
	   			if(chooseclick==0) choose=true; else choose=false;
	   	    }else chooseclick=-1;
	}


	@Override
	public void render(Graphics2D g) {
		
		
		
		g.drawImage(backGround,0,0,480,480, null);
		
		if(menu)
		{
			
			if(keycount1%3==1) {
				g.setColor(Color.WHITE);
				g.fillRect(65, 80, 70, 20);
				g.setColor(Color.black);
				g.drawString("CONTROL", 70, 95);
			}else {
				g.setColor(Color.WHITE);
				g.drawString("CONTROL", 70, 95);
			}
			
			if(keycount1%3==0) {
				g.setColor(Color.WHITE);
				g.fillRect(210, 80, 70, 20);
				g.setColor(Color.black);
				g.drawString("START", 215, 95);
			}else {
				g.setColor(Color.WHITE);
				g.drawString("START", 215, 95);
			}
			if(keycount1%3==2) {
				g.setColor(Color.WHITE);
				g.fillRect(350, 80, 70, 20);
				g.setColor(Color.black);
				g.drawString("EXIT", 365, 95);
			}else {
				g.setColor(Color.WHITE);
				g.drawString("EXIT", 365, 95);
			}
			g.setColor(Color.WHITE);
			g.drawImage(leftbtn, 20, 75, 30, 30, null);
			g.drawImage(rightbtn, 430, 75, 30, 30, null);
			g.drawString("<--Press-Enter-->", 190, 140);
			g.drawImage(RPG, 180, 15, 120, 45, null);
		}
		else {
			g.setColor(Color.white);
			g.drawRect(30, 22, 40, 15);
			g.drawString("ESC",35, 35);
			
			if(start)	
			{
				if(keycount2%2==0) {
					g.setColor(Color.WHITE);
					g.fillRect(90, 80, 60, 20);
					g.setColor(Color.black);
					g.drawString("EASY", 100, 95);
				}else {
					g.setColor(Color.WHITE);
					g.drawString("EASY", 100, 95);
				}
				
				if(keycount2%2==1) {
					g.setColor(Color.WHITE);
					g.fillRect(330, 80, 60, 20);
					g.setColor(Color.black);
					g.drawString("HARD", 340, 95);
				}else {
					g.setColor(Color.WHITE);
					g.drawString("HARD", 340, 95);
				}
				
				g.setColor(Color.white);
				g.drawString("MODE", 220, 50);
				g.drawImage(leftbtn, 20, 75, 30, 30, null);
				g.drawImage(rightbtn, 430, 75, 30, 30, null);
				g.drawString("<--Press-Enter-->", 190, 140);
				
			}
			if(control)
			{
				g.setColor(Color.white);
				g.drawString("MOVE: W, A, S, D", 100, 100);
				g.drawString("ATACK: SPACE", 100, 120);
				g.drawString("SKILL: R", 100, 140);
			}
		
		}
		
		
	}


	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean hard) {
		this.hard = hard;
	}

	public boolean isEasy() {
		return easy;
	}

	public void setEasy(boolean easy) {
		this.easy = easy;
	}
	
	public World getWorld()
	{
		return null;
	}

}
