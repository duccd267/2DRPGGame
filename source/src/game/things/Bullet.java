package game.things;

import java.awt.Color;
import java.awt.Graphics2D;


import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.Vector;

public class Bullet extends Object {
	protected Vector target;
	protected double bcount;
	protected double time;
	protected boolean canchase;
	protected Vector centre;
	
	
	

	public Bullet( Vector orgin,Vector target) {
		spawnPos =new Vector();
		spawnPos.x=orgin.x+5;
		spawnPos.y=orgin.y+5;
		pos=new Vector();
		pos.x=orgin.x+5;
		pos.y=orgin.y+5;
		disappear=false;	
		size=2;
		this.target = new Vector();
		this.target.x=target.x+10;
		this.target.y=target.y+10;
		aim();
		bound =new AABB(8,this);
		hitBound = new AABB(8,this);
		damage=50;
		canchase=false;
		centre =new Vector();
		centre.x =pos.x+4;
		centre.y= pos.y+4;
		
		
	}
	
	public Bullet(Vector orgin)
	{
		disappear=false;
		size=6;
		pos=new Vector();
		pos.x=orgin.x+5;
		pos.y=orgin.y+5;
		bound =new AABB(12,this);
		hitBound = new AABB(12,this);
		bcount=0;
		time=300;
		canchase=true;
		damage=200;
	}
	
	protected void aim()
	{
	    float t=0.01f;
	    dx=t*(target.x-spawnPos.x);
	    dy=t*(target.y-spawnPos.y);
	}
	
	protected void move()
	{
		pos.x+=dx;
		pos.y+=dy;
		
	}
	protected void hit(Player player)
	{
		if(hitBound.cirColBox(player.bound))
		{
			disappear =true;
			System.out.println("bullet hit");
			player.setHealth(damage);
		}
		if(hitBound.cirColBox(player.getSkillBound())&&player.isDoingR())
		{
			System.out.println("collied");
			disappear =true;
		}
		
	}
	
	protected void outTime()
	{
		if(bcount>time) disappear =true;
	}
	protected void outRange()
	{
		double distance=Math.sqrt((pos.x-spawnPos.x)*(pos.x-spawnPos.x)+(pos.y-spawnPos.y)*(pos.y-spawnPos.y));
		if(distance>150) disappear=true;
	}
	
	public void chase(Vector goal)
	{
		
		float x1=pos.x-goal.x;
		float y1=pos.y-goal.y;
		float t=1f;
		if(Math.abs(x1)>=Math.abs(y1))
		{
		
		dx=-t*x1/Math.abs(x1);
		dy=t*y1/x1;
		}
		else {
			dy=-t*y1/Math.abs(y1);
			dx=t*x1/y1;
		}
	}
	public void update()
	{
		
	}
    
	public void update(Player player)
	{
		hit(player);
		hitBound.setVector(pos.x, pos.y);
		if(!canchase)
		{
		outRange();
		}
		else {
		chase(player.getCentre());
		outTime();
		bcount++;
		}
		move();
		
	}

	public void render(Graphics2D g) {
		if(!canchase)
		{
		g.setColor(Color.RED);
		g.fillOval((int)pos.x,(int) pos.y,8, 8);
		}
		else {
			g.setColor(Color.DARK_GRAY);
			g.fillOval((int)pos.x,(int) pos.y,12, 12);
		}
	    g.setColor(Color.YELLOW);
        g.drawOval((int)hitBound.getPos().x,(int)hitBound.getPos().y,(int)hitBound.getRadius(),(int)hitBound.getRadius())	;	
	}

}
