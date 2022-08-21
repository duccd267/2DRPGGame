package game.things;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.Vector;

public class Enemy extends Entity {
	
	
	protected AABB sense;
	
	

	protected int rsense;
	protected int rhitbound;
	protected Random random = new Random();
	
	protected int rmcount;
	protected double distance=0;
	public Enemy( Vector orgin, int size,int rsense,int rhitbound) {
		 super( orgin, size);
		 acc = 0.5f;
		 maxSpeed = 0.5f;
		 speed=maxSpeed;
		 this.rsense=rsense;
		 this.rhitbound=rhitbound;
		 centre = new Vector(pos.x+size/5,pos.y+size/5);
		 bound = new AABB(centre,20,20);
		 sense = new AABB(rsense, this);
		 hitBound = new AABB(rhitbound,this);
		 Random random = new Random(); 
		 rmcount=200;
	}
	
	
	protected void randomDirection()
	{   
		rmcount=0;
		int i=random.nextInt(5);
		if (i==0) up =true; else up=false;
		if (i==1) down =true; else down=false;
		if (i==2) left =true; else left=false;
		if (i==3) right =true; else right=false;
		if (i==4)  {up=false;down=false;left=false;right=false;}
	}
	protected boolean canRandomMove()
	{ 
		if((rmcount>=200)||upcollide()||downcollide()||leftcollide()||rightcollide()) return true;
		else return false;
	}
	
	protected void RandomMove()
	{
		distance = Math.sqrt((getPos().x-spawnPos.x)*(getPos().x-spawnPos.x)+(getPos().y-spawnPos.y)*(getPos().y-spawnPos.y));
		if(distance<=100) {
			canRandomMove();
			if(canRandomMove()) randomDirection();
			move();
		}
		else {
			up=false;
			down=false;
			left=false;
			right=false;
			chase(spawnPos);
	
			move();
		}
	}
	protected void move() {
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
	 
		if(down) { 
			if(downcollide()) dy=0;
			else {
				dy=+acc;
				if (dy>speed) {dy= speed;}
				}
				}
		/*else {if (dy>0) { 
		              dy-=deacc;
		               if(dy<0)dy=0;} 
		}	*/
		
		if(left) {  if(leftcollide())dx =0;else {
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
	
	protected void chase(Vector goal)
	{
		if(pos.y>goal.y+5) {
			up= true;down =false; 
		}else {up= false;}
		if(pos.y<goal.y-5) {
			down = true;up=false;
		}else {down = false;}
		if(pos.x>goal.x+5) {
	        left= true;right=false;
		}else {left = false;}
		if(pos.x<goal.x-5) {
	        right = true;left=false;
		}else {right = false;}
	}
	
	protected void hitted(Player player)
	{
		if(player.getSkillBound().cirColBox(bound)&&player.isDoingR())
		{
			health-=(float)1/300*player.getDamage();
		}
		if(player.hitBound.cirColBox(bound)&&player.attack)
			{
			System.out.println("Hitted");
			health-=player.getDamage();// sua lai damage ? bo nho ??
			}
	}
	protected void hit(Player player)
	{
		if(hitBound.cirColBox(player.bound)&&canAttack)
			{System.out.println("fuck player !!");
			attack = true;
			player.setHealth(damage);
			count=0;
			}else attack =false;
	}

	public void update(Player player) {
	   super.update();
	   if(player.isDoingR()&&player.getSkillBound().cirColBox(bound))
	   {
		   force(player.getPos());
		   speed=3f;
		   move();
	   }
	   else {
	   speed=maxSpeed;
	   if (sense.colCircleBox(player.getBound())) {
		   //System.out.println("fuck player!!!");
		   chase(player.pos);
		   move();
	   }
	   else {
		   RandomMove();
		   }}
	   hitted(player);
	   hit(player);
	   canAttack();
	   attacking();
	   killed();
	   pos.x+=dx;
	   pos.y+=dy;
	 
	   hitBound.setVector(pos.x+size/2-rhitbound/2, pos.y+size/2-rhitbound/2);
	   sense.setVector(pos.x+size/2-rsense/2, pos.y+size/2-rsense/2);
	   rmcount++;
	}
	
    @Override
	public void render(Graphics2D g) {
		 g.drawImage(ani.getImage(),(int)pos.x,(int)pos.y,size,size,null);
		// g.setColor(Color.yellow);
		 //g.drawRect((int)pos.getWorldVar().x,(int)pos.getWorldVar().y,32,32);
		// g.drawOval((int)sense.getPos().x,(int)sense.getPos().y , rsense, rsense);
		// g.setColor(Color.RED);
		// g.drawOval((int)hitBound.getPos().x,(int)hitBound.getPos().y , rhitbound, rhitbound);
		 //g.drawOval((int)hitBound.getPos().x,(int)hitBound.getPos().y , (int)hitBound.getRadius(), (int)hitBound.getRadius());
		 //g.setColor(Color.BLACK);
		 //g.drawRect((int)bound.getPos().x,(int)bound.getPos().y, (int)bound.getWidth(),(int)bound.getHeight());
		 healthBar.render(g);
	}
    
    public void setRsense(int rsense)
	{
		this.rsense=rsense;
	}
	
	public void setRhitbound(int rhitbound)
	{
		this.rhitbound=rhitbound;
	}

	
	
	
}
