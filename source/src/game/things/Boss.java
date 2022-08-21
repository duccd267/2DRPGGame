package game.things;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.HealthBar;
import game.utils.Vector;

public class Boss extends Enemy {
	
	
	protected ArrayList<Bullet> bullets;
	private int rskill;
	private AABB skillbound;
	private boolean canR=true;
	private boolean R;
	private boolean doingR;
	protected int Rcooldown=1050;
	protected int Rcount=400;
	
	
	
	
	public Boss(Vector orgin, int size, boolean hard)
	{
	super(orgin, size, 100, 100);
	if(!hard)
	{
	sprite=	new Sprite("/entity/Boss.png",85,95);
	maxHealth = 500;
	health =500;
	damage =10;
	healthBar= new HealthBar(this);
	centre = new Vector(pos.x+size/5+10,pos.y+size/5);
	bound = new AABB(centre,30,30);
	}
	else {
		sprite=	new Sprite("/entity/darkBoss.png",124,109);
		maxHealth = 100;
		health =100;
		damage =10;
		healthBar= new HealthBar(this);
		centre = new Vector(pos.x+size/5+10,pos.y+size/5);
		bound = new AABB(centre,30,30);
	}
	 bullets =new ArrayList<Bullet>();
	 attackSpeed=300;
	setAnimation(UP, sprite.getSpriteArray(UP),10);
	}
	
	
	protected void canR()
	{
		if(Rcount>=500)
		{
			canR=true;
			bullets.add(new Bullet(centre));
            Rcount=0;
		}
		else canR =false;
	}
	protected void doingR()
	{
		if(Rcount<=300)
			{doingR = true;}
		else doingR= false;
	}
	

	public void hit()
	{
		if(canAttack)
		{
		attack = true;
		bullets.add(new Bullet(centre,new Vector(centre.x,centre.y+50)));
		bullets.add(new Bullet(centre,new Vector(centre.x,centre.y-50)));
		bullets.add(new Bullet(centre,new Vector(centre.x+50,centre.y)));
		bullets.add(new Bullet(centre,new Vector(centre.x-50,centre.y)));
		bullets.add(new Bullet(centre,new Vector(centre.x+35,centre.y+35)));
		bullets.add(new Bullet(centre,new Vector(centre.x+35,centre.y+-35)));
		bullets.add(new Bullet(centre,new Vector(centre.x-35,centre.y+35)));
		bullets.add(new Bullet(centre,new Vector(centre.x-35,centre.y-35)));
		bullets.add(new Bullet(centre));
		
		count=0;
		}
		else attack =false;
	}
	
	
	public void update(Player player)
	{
		super.update(player);
		bound.setVector(pos.x+size/5+5, pos.y+size/5);
		canR();
		doingR();
		hit();
		Rcount++;
		count++;
		try {
		if(bullets.size()>0)
		{
			for(int i=0;i<bullets.size();i++)
			{
				bullets.get(i).update(player);
				
			}
			for(int i=0;i<bullets.size();i++)
			{
				if(bullets.get(i).disappear)
				{
					bullets.remove(i);
					System.out.println("remove");
				}
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void render(Graphics2D g) {
		
		super.render(g);
		try {
			if(bullets.size()>0)
			{
				for(int i=0;i<bullets.size();i++)
				{
					bullets.get(i).render(g);
					
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
