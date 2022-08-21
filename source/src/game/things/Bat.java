package game.things;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.HealthBar;
import game.utils.Vector;

public class Bat extends Enemy {
	
	protected ArrayList<Bullet> bullets;
	
	public Bat( Vector orgin, int size, boolean hard) {
		super( orgin, size,40,200);
		if(!hard) {
		sprite= new Sprite("/entity/bat.png",68,76);
		maxHealth = 150;
		health =150;
		damage =0;
		healthBar= new HealthBar(this);
		
		/*setRsense(200);*/
		
		
		
		}else {
			sprite= new Sprite("/entity/bat2.png",68,76);
			maxHealth = 200;
			health =200;
			damage =0;
			healthBar= new HealthBar(this);
			/*setRsense(200);*/
			
			bullets =new ArrayList<Bullet>();
			hitBound.setRadius(200);
		}
		attackSpeed=100;
		bullets =new ArrayList<Bullet>();
		 centre = new Vector(pos.x+size/5,pos.y+size/5);
		 bound = new AABB(centre,20,20);
		setAnimation(UP, sprite.getSpriteArray(UP),10);

		
	}
	
	@Override
	public void hit(Player player)
	{
		if(hitBound.cirColBox(player.bound)&&canAttack)
		{
		attack = true;
		bullets.add(new Bullet(pos,player.getBound().getPos()));
		count=0;
		}
		else attack =false;
	}
	

	public void update(Player player)
	{
		super.update(player);
		
	
			bound.setVector(pos.x+size/5, pos.y+size/5);
		 hitted(player);
		//try {
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
		//} catch (Exception e) {
			// TODO: handle exception
		//}
			System.out.println(count);
		
	}
	public void render(Graphics2D g)
	{
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

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	

	
	
	
	
	
	

}