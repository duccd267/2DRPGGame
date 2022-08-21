package game.things;

import game.graphics.Sprite;
import game.utils.HealthBar;
import game.utils.Vector;

public class Skeleton extends Enemy {
	public Skeleton( Vector orgin, int size, boolean hard) {
		super( orgin, size,120,40);
		if(!hard)
		{
		sprite=	new Sprite("/entity/enemy.png",64,64);
		maxHealth = 160;
		health =160;
		damage =20;
		healthBar= new HealthBar(this);
		}else {
		sprite=	new Sprite("/entity/skeleton2.png",64,64);
		maxHealth = 210;
		health =210;
		damage =40;
		healthBar= new HealthBar(this);
		}
		attackSpeed=150;
		setAnimation(UP, sprite.getSpriteArray(UP),10);
	}
	
	public void update(Player player)
	{
		super.update(player);
		bound.setVector(pos.x+size/5, pos.y+size/5);
	}

}
