package game.things;

import java.awt.Graphics2D;

import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.Vector;

public abstract class Object {
	
	protected Vector pos;
	protected Vector spawnPos;
	protected AABB bound;
	protected AABB hitBound;
	protected int size;
	public boolean disappear = false;
	protected int damage;
	protected float dx;
	protected float dy;
	protected float speed;
	protected float maxSpeed;
	protected float acc;
	protected float deacc; 
	
	
     protected abstract void update();

	
	protected abstract void render(Graphics2D g);
	
	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public Vector getSpawnPos() {
		return spawnPos;
	}

	public void setSpawnPos(Vector spawnPos) {
		this.spawnPos = spawnPos;
	}

	public AABB getBound() {
		return bound;
	}

	public void setBound(AABB bound) {
		this.bound = bound;
	}

	public AABB getHitBound() {
		return hitBound;
	}

	public void setHitBound(AABB hitBound) {
		this.hitBound = hitBound;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isDisappear() {
		return disappear;
	}

	public void setDisappear(boolean disappear) {
		this.disappear = disappear;
	}

	public int getDamage() {
		
	return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getAcc() {
		return acc;
	}

	public void setAcc(float acc) {
		this.acc = acc;
	}

	public float getDeacc() {
		return deacc;
	}

	public void setDeacc(float deacc) {
		this.deacc = deacc;
	}

	
	
	public Object()
	{
		
	}
	
	
	
	

	

}
