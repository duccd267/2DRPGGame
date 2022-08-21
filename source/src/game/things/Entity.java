package game.things;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;



import game.graphics.Animation;
import game.graphics.Sprite;
import game.states.GameStateManager;
import game.states.PlayState;
import game.utils.AABB;
import game.utils.HealthBar;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;
import game.world.World;


public abstract class Entity extends Object{
	
	protected final int ATTACK =5;
	protected final int DIE = 4;	
	protected final int UP=3;
	protected final int DOWN =2;
	protected final int LEFT = 1;
	protected final int RIGHT =0;	
	protected Animation ani;
	protected int currentAnimation;
	protected Sprite sprite;
	protected Vector centre;
	
	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	
	protected boolean attack;
	protected boolean attacking;
	protected boolean canAttack =true;
	protected int attackSpeed;
	
	protected int attackAni=650;
	protected int count;
	
	
	protected float health;
	protected float maxHealth;
	
	protected HealthBar healthBar;
	
	protected int preState=0;
	
	
	protected World world;
	
	
	
	public Entity( Vector orgin, int size)
	{
		
		spawnPos = orgin;
		pos=new Vector();
		pos.x=orgin.x;
		pos.y=orgin.y;
		this.size= size;
		healthBar= new HealthBar(this);
		ani= new Animation();	
		count =120;
		damage =50;
		try {
			world = GameStateManager.getCurrentState().getWorld();
		} catch (Exception e) {
			world = new World("C:\\Users\\Latitude 7490\\eclipse-workspace\\RPG2\\res\\world\\world2.txt");
		}
	}
	
	
	
	
	
	protected void setAnimation(int i, BufferedImage[] frames, int delay)
	{
		currentAnimation =i; 
		ani.setFrame(frames);
		ani.setDelay(delay);
	}
	
	protected Animation getAnimation() {
		return ani;
	}
	
	protected void animate() {
		    if (attacking) {
		    	if(currentAnimation<5)
		    		setAnimation(currentAnimation+ATTACK, sprite.getSpriteArray(currentAnimation + ATTACK), attackAni/100);
		    }else if(up) {
			if(currentAnimation!=UP||ani.getDelay()==-1)
			{
				setAnimation(UP, sprite.getSpriteArray(UP), 10);
			}
		    }
			else if(down) {
				if(currentAnimation!=DOWN||ani.getDelay()==-1)
				{
					setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10	);
				}
			}
			else if(left) {
				if(currentAnimation!=LEFT||ani.getDelay()==-1)
				{
					setAnimation(LEFT, sprite.getSpriteArray(LEFT), 10	);
				}
			}
			else if(right) {
				if(currentAnimation!=RIGHT||ani.getDelay()==-1)
				{
					setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
				}
			} else if(disappear)
			{
				 if (currentAnimation != DIE || ani.getDelay() == -1) {
		                setAnimation(DIE, sprite.getSpriteArray(DIE), 15);
			}}
		    
			else {if(currentAnimation<=4)
				setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
			else setAnimation(currentAnimation-ATTACK, sprite.getSpriteArray(currentAnimation-ATTACK), -1);
			}
		
	}
	

	
	
	protected boolean upcollide()
	{
		float toprightx = bound.getPos().x;
		float topleftx = bound.getPos().x+20;
		float toprighty = bound.getPos().y-4;
		int tiley =(int)toprighty/32;
		int tile1x=(int)toprightx/32;
		int tile2x=(int)topleftx/32;
		if(tile1x==tile2x) {
			  return !world.getTile(tile1x, tiley).walkable();
		 }
		else {
			return !world.getTile(tile1x, tiley).walkable()||!world.getTile(tile2x, tiley).walkable();
		}
	}
	
	protected boolean downcollide()
	{
		float downleftx = bound.getPos().x+20;
		float downrightx= bound.getPos().x;
		float downrighty = bound.getPos().y+20+4;
		int tiley =(int)downrighty/32;
		int tile1x=(int)downrightx/32;
		int tile2x=(int)downleftx/32;
		if(tile1x==tile2x) {
			  return !world.getTile(tile1x, tiley).walkable();
		 }
		else {
			return !world.getTile(tile1x, tiley).walkable()||!world.getTile(tile2x, tiley).walkable();
		}
	}
	protected boolean leftcollide()
	{
		float topleftx = bound.getPos().x-4;
		float toplefty = bound.getPos().y;
		float downlefty = bound.getPos().y+20;
		int tilex =(int)topleftx/32;
		int tile1y=(int)toplefty/32;
		int tile2y=(int)downlefty/32;
		if(tile1y==tile2y) {
			  return !world.getTile(tilex, tile1y).walkable();
		 }
		else {
			return !world.getTile(tilex, tile1y).walkable()||!world.getTile(tilex, tile2y).walkable();
		}
	}
	protected boolean rightcollide()
	{
		float toprightx = bound.getPos().x+20+4;
		float toprighty = bound.getPos().y;
		float downrighty =bound.getPos().y+20;
		int tilex =(int)toprightx/32;
		int tile1y=(int)toprighty/32;
		int tile2y=(int)downrighty/32;
		if(tile1y==tile2y) {
			  return !world.getTile(tilex, tile1y).walkable();
		 }
		else {
			return !world.getTile(tilex, tile1y).walkable()||!world.getTile(tilex, tile2y).walkable();
		}
	}
	
	protected void canAttack()
	{
		if(count>=attackSpeed)
		{
			canAttack=true;
		}
		else canAttack =false;
	}
	protected void attacking()
	{
		if(count<=30)attacking = true;
		else attacking= false;
	}
	
	
	protected void killed()
	{
		if (health<=0) disappear =true;
		else disappear=false;
	}
	
	protected void force(Vector vec)
	{
		float x1=pos.x-vec.x+5;
		float y1=pos.y-vec.y+6;
		if(x1>=0) down =true; else down=false;
		if(x1<0) up =true; else up= false;
		if(y1>=0) right =true; else right=false;
		if(y1<0) left =true; else left =false;
	}
	
	public void update()
	{
		if(GameStateManager.currentState>preState) {
		world=world = GameStateManager.getCurrentState().getWorld();
		preState= GameStateManager.currentState;
		}
		animate();
	    ani.update();
	    healthBar.update();
	    count++;
	}
	
	public abstract void render(Graphics2D g);
	public void input(KeyHandler key,MouseHandler mouse)
	{
		// not really need
	}
	
	
	public Vector getCentre() {
		return centre;
	}

	public void setCentre(Vector centre) {
		this.centre = centre;
	}
	
	public int getSize()
	{
		return size;
	}
	public boolean isAttacking() {
		return attacking;
	}
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	public float getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health -= health;
	}
	public float getMaxHealth()
	{
		return maxHealth;
	}
	public Vector getPos()
	{
		return pos;
	}
	public void setSprite(Sprite sprite)
	{
		this.sprite=sprite;
	}
	public void setSize(int i)
	{
		size = i;
	}
	public void setAcc(float f)
	{
		acc = f;
	}   
	public void setDeacc(float f)
	{
		deacc =f ;
	}
	
}

