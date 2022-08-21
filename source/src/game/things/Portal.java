package game.things;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.Animation;
import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.Vector;

public class Portal extends Object {

	private Animation ani;
	private int currentAnimation;
	private Sprite sprite;
	private boolean nextlevel;
	public Portal() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isNextLevel()
	{
		return nextlevel;
	}
	
	public Portal(Vector orgin, int size) {
		spawnPos = orgin;
		pos=new Vector();
		pos.x=orgin.x;
		pos.y=orgin.y;
		ani= new Animation();
		sprite = new Sprite("/entity/portal.png",65,81);
		Vector boundVector = new Vector (orgin.x+21,orgin.y +32);
		bound= new AABB(boundVector,25,25);
		setAnimation(0, sprite.getSpriteArray(0),10);
		nextlevel=false;
		System.out.println("load portal");
		
	}

	
	public void setAnimation(int i, BufferedImage[] frames, int delay)
	{
		currentAnimation =i; 
		ani.setFrame(frames);
		ani.setDelay(delay);
	}
	
	public Animation getAnimation() {
		return ani;
	}
	
	public void animate() {
		setAnimation(0, sprite.getSpriteArray(0), 10);
	}
	
	public void nextlevel(Player player) {
		if(player.bound.collides(bound))
		nextlevel=true;

	}
	
	@Override
	
	public void update()
	{
		
	}
	
	public void update(Player player) {
		
		
	    ani.update();
	    nextlevel(player);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(ani.getImage(), (int)(pos.x), (int)(pos.y), 70, 90, null);
		//g.setColor(Color.ORANGE);
		//g.drawRect((int)bound.getPos().x, (int)bound.getPos().y, 25, 25);
		System.out.println("render portal");
	}

	
}
