package game.utils;


public class Vector {
	
	public float x,y;
	
	public static float worldx, worldy;
	
	public Vector ()
	{
		x=0;
		y=0;
	}
	
	public Vector(Vector pos) {
		new Vector (pos.x, pos.y);
	}

	public Vector(float x, float y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y =y;
		
	}
	
	public void addX(float a){x+=a;}
	public void addY(float b){y+=b;}
	
	public void setX(float a) { x=a;}
	public void setY(float b) { y=b;}
	
	public void setVector(Vector vec) { this.x=vec.x; this.y =vec.y;}
	
	public void setVector(float x, float y) {this.x=x; this.y=y;}
	
	public static void setWorldVar(float x, float y) {worldx =x; worldy= y;}
	
	public Vector getWorldVar()
	{
		return new Vector(x-worldx,y-worldy);
	}
	@Override
	public String toString ()
	{
		return x+ "," +y;
	}
	
	
	
	
	
	

}
