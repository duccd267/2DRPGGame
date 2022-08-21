package game.utils;

import java.awt.Color;
import java.awt.Graphics2D;

import game.states.GameStateManager;
import game.states.PlayState;
import game.things.Entity;
import game.things.Object;
import game.world.World;

public class AABB {
    private Vector pos;
    private float width, height;
    private float xOffset=0;
    private float yOffset=0;
    private float r;// ban kinh hinh tron 
    private int size; 
    
    private Entity e;
    private Object object;
    
    private World world = GameStateManager.getCurrentState().getWorld();
    
    public AABB(Vector pos, int width, int height)
    {   // dung cho tile
    	this.pos= pos;
    	this.width = width;
    	this.height = height;
    	
    	size= Math.max(width, height);
    }

	
    public AABB (int r, Entity e)
    {
    	//dung cho player, enemy....
    	Vector pos = new Vector((int)(e.getBound().getPos().x+e.getBound().getWidth()/2-r/2),(int)(e.getBound().getPos().y+e.getBound().getHeight()/2-r/2));
    	this.pos = pos;
    	this.r= r;
    	this.e =e;
    	size=r;
    }
    
    public AABB(int r, Object object)
    {
    	Vector posVector = new Vector(object.getPos().x, object.getPos().y);
    	this.pos =posVector;
    	this.r=r;
    	this.object=object;
    	
    }
	
    public Vector getPos()
    {

    	return pos;
    }
    
     public float getRadius()
     {
    	 return r;
     }
     
     public float getWidth()
     {
    	 return width;
     }
    
     public float getHeight()
     {
    	 return height;
     }
     
     public void setBox(Vector pos, int w, int h)
     {
    	 this.pos = pos;
    	 height = h;
    	 width =w;
    	 
    	 size = Math.max(w, h);
     }
     
     public void setCircle(Vector pos, int r)
     {
    	 this.pos=pos;
    	 this.r= r;
    	 size = r; 
     }
     
     public void setWidth(float f)
     {
    	 width = f;
     }
     
     public void setHeight(float f)
     {
    	 height = f;
     }
     
     public void setXOffset(float f)
     {
    	 xOffset =f;
     }
     
     public void setYOffset(float f)
     {
    	 yOffset= f;
     }
     public void setRadius(float f)
     {
    	 r=f;
     }
     public void setVector(float x, float y)
     {
    	 pos.x=x;
    	 pos.y=y;
     }
     
     public boolean collideWithTile(int x, int y)
     {
    	 return world.getTile(x, y).walkable();
     }
     
     public boolean collides(AABB bBox)
     {   
    	 //@@
    	 float ax = (pos.x+ (width/2));
    	 float ay = (pos.y+ (height/2));
    	 float bx = (bBox.pos.x+(width/2));
    	 float by = (bBox.pos.y+(height/2));
    	 
    	 if(Math.abs(ax-bx)<(this.width /2 )+(bBox.width/2)) {
    		 if(Math.abs(ay-by)<(this.height /2 )+(bBox.height/2)) {
    			 return true;
    		 }
    	 }
    	 return false;
     }
     
     public boolean colCircleBox(AABB aBox)
     {
    	//@@
    	 float cx = (float)(pos.x+r/Math.sqrt(2)- e.getSize()/Math.sqrt(2));
    	 float cy = (float)(pos.y+r/Math.sqrt(2)- e.getSize()/Math.sqrt(2));
    	 
    	 float xDelta = cx - Math.max(aBox.pos.x+ (aBox.getWidth()/2), Math.min(cx,aBox.pos.x));
    	 float yDelta = cy - Math.max(aBox.pos.y+ (aBox.getHeight()/2), Math.min(cy,aBox.pos.y));
    	 
    	 if((xDelta*xDelta +yDelta*yDelta)< ((this.r/Math.sqrt(2))*(this.r/Math.sqrt(2)))) {
    		return true;
    	 }
    	 
    	 return false; 
     }
     
     public boolean cirColBox(AABB bBox)
     {
    	 float ax= pos.x+r/2;
    	 float ay= pos.y+r/2;
    	 float bx=bBox.getPos().x+bBox.size/2;
    	 float by=bBox.getPos().y+bBox.size/2;
    	 float distance = (ax-bx)*(ax-bx)+(ay-by)*(ay-by);
    	 if(distance<=(r/2+bBox.size/2)*(r/2+bBox.size/2)) return true;
    	 else return false;
     }
  
     
     
     
     
     
     
}
