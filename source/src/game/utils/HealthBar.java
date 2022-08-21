package game.utils;

import java.awt.Color;
import java.awt.Graphics2D;

import game.things.Entity;
import game.things.Player;

public class HealthBar {

	private Entity entity;
	private Vector pos;
	private float width;
	private int height;
	private float healthmax;
	private float healthpercent;
	
	public HealthBar(Entity entity)
	{
		this.entity = entity;
		pos = new Vector();
		width = entity.getSize();
		height=5;
		pos.x=entity.getPos().x;
		pos.y=entity.getPos().y-height- 5;
		healthmax =entity.getMaxHealth();
	}
	
	public void update()
	{   
		pos.x=entity.getPos().x;
		pos.y=entity.getPos().y-height- 5;
		healthpercent=width*(entity.getHealth()/healthmax);
	}
	
	public void render(Graphics2D g)
	{
		if(entity instanceof Player) g.setColor(Color.green);
		else g.setColor(Color.red);
		g.fillRect((int)pos.x,(int)pos.y,(int)healthpercent,height);
		g.setColor(Color.black);
		g.drawRect((int)pos.x,(int)pos.y,(int)width,height);
	}
	

}
