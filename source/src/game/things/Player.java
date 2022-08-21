package game.things;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.graphics.Animation;
import game.graphics.Sprite;
import game.states.PlayState;
import game.utils.AABB;

import game.utils.HealthBar;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;
import game.world.World;

public class Player extends Entity{
	
	private int rhitbound;
	private int rskill;
	private AABB skillbound;
	private boolean canR=true;
	private boolean R;
	private boolean doingR;
	protected int Rcooldown=1050;
	protected int Rcount=400;
	
	protected Sprite skilSprite;
    protected Animation skillAni;
	
	
	public Player(Vector orgin, int size) {
		super(orgin, size);
		sprite = new Sprite("/entity/player1.png");
		centre = new Vector (orgin.x+5,orgin.y +10);
		bound= new AABB(centre,20,20);
		rskill=70;
		skillbound = new AABB(rskill,this);
		rhitbound = 60;
		hitBound =new AABB(rhitbound,this);
		maxHealth =300;
		health =300;
		damage =50;
		healthBar= new HealthBar(this);
		acc=2;
		deacc=1;
		speed=2;
		attackSpeed=90;

		setAnimation(UP, sprite.getSpriteArray(UP),10);
		skilSprite= new Sprite("/entity/skill.png",86,68);
		skillAni = new Animation();
		skillAni.setFrame(skilSprite.getSpriteArray(0));
		skillAni.setDelay(5);
		
		
		Rcount=400;
	}
	

	public AABB getSkillBound()
	{
		return skillbound;
	}
	
	
	protected void canR()
	{
		if(Rcount>=400)
		{
			canR=true;
		}
		else canR =false;
	}
	protected void doingR()
	{
		if(Rcount<=300)
			{doingR = true;System.out.println("doingR");}
		else doingR= false;
	}
	
	protected void move()
	{
		if(up) { 
			    if(upcollide()) dy=0;
		        else 
		            {
			         dy=-acc;
			         if (dy<speed) {dy= -speed;}}
		             }else {if (dy<0) { 
			              dy+=deacc;
			               if(dy>0)dy=0;} 
		            
		        }
		 
		if(down) { if(downcollide()) dy=0;else {
			dy=+acc;if (dy>speed) {dy= speed;}}
		}else {if (dy>0) { 
			              dy-=deacc;
			               if(dy<0)dy=0;} 
		}	
		if(left) { if(leftcollide())dx =0;else {
			dx=-acc;if (dx<speed) {dx= -speed;}}
		}else {if (dx<0) { 
			              dx+=deacc;
			               if(dx>0)dx=0;} 
		      }
		
		if(right) { if(rightcollide()) dx=0;else {
			dx=+acc;if (dx<speed) {dx= speed;}}
		}else {if (dx>0) { 
			             dx-=deacc;
			               if(dx<0) dx=0;} 
		      }
	}

	
	
	public void update (ArrayList<? extends Enemy> enemy)
	{
		super.update();
		healthBar.update();
		move();
		pos.x+=dx;
		pos.y+=dy;
		bound.setVector(pos.x+5, pos.y+10);
		canAttack();
		attacking();
		canR();
		doingR();
		
		hitted(enemy);
		killed();
		if(doingR)
		{
			skillAni.update();
		}
		
		
		hitBound.setVector(pos.x+size/2-rhitbound/2, pos.y+size/2-rhitbound/2);
		skillbound.setVector(pos.x+size/2-rskill/2, pos.y+size/2-rskill/2);
	    Rcount++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(doingR) g.drawImage(skillAni.getImage(),(int)pos.x-42,(int)pos.y-30,120,90, null);
		g.drawImage(ani.getImage(),(int)(pos.x), (int)(pos.y), size, size, null);
		//g.drawRect((int)bound.getPos().x, (int)bound.getPos().y,(int) bound.getWidth(),(int) bound.getHeight());
		//g.setColor(Color.red);
		//g.drawRect((int)pos.x, (int)pos.y, 32, 32);
		//g.setColor(Color.cyan);
		//if(attack)
		//g.drawOval((int)skillbound.getPos().x, (int)skillbound.getPos().y , (int)skillbound.getRadius(), (int)skillbound.getRadius());
		//{
		//g.setColor(Color.RED);
		//g.drawOval((int)hitBound.getPos().x,(int)hitBound.getPos().y , rhitbound, rhitbound);
		//}
		healthBar.render(g);
	
	}
	
	public void input (KeyHandler key, MouseHandler mouse) {
		if(key.up.down) {
			up= true;
		}else {up= false;}
		if(key.down.down) {
			down = true;
		}else {down = false;}
		if(key.left.down) {
	        left= true;
		}else {left = false;}
		if(key.right.down) {
	        right = true;
		}else {right = false;}
        if(key.attack.down&&canAttack) {
			attack = true;
			count=0;
			System.out.println("canattack");
		}else {attack = false;}
        if(key.skill.down&&canR) {
			R = true;
			Rcount=0;
			System.out.println("canR");
		}else {R = false;}
	}
	
	
	
	
	protected void hitted(ArrayList<? extends Enemy> enemy)
	{
		for(Enemy e:enemy)
		{
		if(e.hitBound.cirColBox(bound)&&e.attack)
			{
			System.out.println("Hitted");
			}
		}
		/*for(Bullet bl:bullets)
		{
			if(bl.hitBound.cirColBox(bound))
			{
			System.out.println("Hitted");
			health-=0;
			}
		}*/
	}
	public boolean isDoingR() {
		return doingR;
	}

	public void setDoingR(boolean doingR) {
		this.doingR = doingR;
	}
	
	public AABB getBound()
	{
		return bound;
	}
}
