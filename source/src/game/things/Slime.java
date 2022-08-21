package game.things;

import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.HealthBar;
import game.utils.Vector;

public class Slime extends Enemy {

	public Slime( Vector orgin, int size,boolean hard) {
		super( orgin, size,70,20);
		if(!hard)
		{
	    sprite= new Sprite("/entity/slime.png",51,48);
		maxHealth = 130;
		health =130;
		damage =15;
		healthBar= new HealthBar(this);
		System.out.println("easymode");
		}else {
	    sprite= new Sprite("/entity/slime2.png",51,48);
		maxHealth = 160;
		health =160;
		damage =30;
		healthBar= new HealthBar(this);
		System.out.println("hardmode");
		}
		centre = new Vector(orgin.x+size/5,orgin.y+size/5);
		bound = new AABB(centre,20,20);
		attackSpeed=180;
		setAnimation(UP, sprite.getSpriteArray(UP),10);
		
	}
	public void update(Player player)
	{
		super.update(player);
		bound.setVector(pos.x+size/5-3, pos.y+size/5);
	}

	
}
