package game;

import javax.swing.JPanel;


import game.states.GameStateManager;
import game.utils.Assets;
import game.utils.KeyHandler;
import game.utils.MouseHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
	
	public static int width, height;
	
	private boolean running=false;
	
	private Thread thread;
	
	private BufferedImage image;
	
	private Graphics2D g;
	
	private MouseHandler mouseHandler;
	
	private KeyHandler keyHandler;
	
	private GameStateManager gsm;
	
    public GamePanel (int width, int height)
      { 
    	
    	this.width= width;
    	this.height = height;
     	setPreferredSize(new Dimension(width, height));
     	setFocusable(true);
      }
    
    public void addNotify()
    {
    	super.addNotify();/* Wait for the JPanel to be added to the
    	 JFrame/JApplet before starting. */
    	if(thread == null)
    	{
    		thread = new Thread (this, "GameThread");
    		thread.start();
    	}
    }
    
    public void init()
    {
    	running = true;
    	Assets.init();
    	image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    	g = (Graphics2D) image.getGraphics();
    	mouseHandler = new MouseHandler();
    	keyHandler= new KeyHandler(this);
    	gsm = new GameStateManager();
    	
    }
    
    public void run()
    {
    	init();
    	int fps = 60;
    	double timePerTick=1000000000/fps;// nanosecond
    	double delta=0;
    	long now;
    	long lastTime= System.nanoTime();
    	while (running)
    	{
    		now = System.nanoTime();
    		delta +=(now-lastTime)/timePerTick;
    		lastTime = now;	
    		if(delta>=1)
    		{		
    			update();
    			input(mouseHandler, keyHandler);
    			render();
    			draw();
    			delta--;
    		}
    	}
    }
    public void update() {
    	
    	gsm.update();
    	
    }
    
    public void render() {
    	if(g!=null) {
    		g.setColor(new Color(0,0,0));// mau RGB
    		g.fillRect(0, 0, width, height);
    		gsm.render(g);
    	}
    }
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler)
    {
    		gsm.input(mouseHandler, keyHandler);
    }
    public void draw () {
    	Graphics g2 = (Graphics) this.getGraphics();
    	g2.drawImage(image, 0, 0,width, height, null);
    	g2.dispose();//
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}