package game.utils;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


import game.GamePanel;





public class KeyHandler implements KeyListener {

	public static List<Key> keys = new ArrayList<Key>();
	
	public class Key
	{
		public int presses=0, absorbs=0, count=0;
		public boolean down, clicked;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed)
		{
			if(pressed != down) down= pressed;
			if (pressed) {
				presses ++;
			}
		}
		
		
		public void tick()
		{
			if(absorbs<presses) {
				absorbs++;
				clicked=true;
			} else {
				clicked =false;
			}
		}
		public void count()
		{
			count++;
		}
		public void reset()
		{
			count=0;
		}
	}
	

	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key skill = new Key();
	public Key menu = new Key();
	public Key enter = new Key();
	
	public Key start= new Key();
	public Key hard= new Key();
	public Key easy= new Key();
	public Key esc = new Key();
	public Key pre = new Key();
	public Key next = new Key();
	
	
	public KeyHandler(GamePanel gamePanel) {
		gamePanel.addKeyListener(this);
		
		// TODO Auto-generated constructor stub
	}
	
	
	public void releaseAll() {
		for(int i=0;i<keys.size();i++)
		{
			keys.get(i).down=false;
		}

	}
	
	public void tick ()
	{
		for (int i=0; i<keys.size();i++)
		{
			keys.get(i).tick();
		}
	}
	
	public void Toggle(KeyEvent e, boolean pressed)
	{
		if (e.getKeyCode()==KeyEvent.VK_W) up.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_S) down.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_LEFT) left.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_D||e.getKeyCode()==KeyEvent.VK_RIGHT) right.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_SPACE) attack.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_R) skill.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_M) menu.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_ENTER) enter.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) esc.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_H) hard.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_E) easy.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W) next.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S) pre.toggle(pressed);
		

		
			
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		Toggle(e, true);
		
		
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Toggle(e, false);
		
		
			
			
		
	}
	
	

}
