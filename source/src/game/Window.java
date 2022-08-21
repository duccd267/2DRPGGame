package game;

import javax.swing.JFrame;

public class Window extends JFrame 
{
	public Window () {
		setTitle("RPG2");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(480,480));// @@
		pack();// thay doi kich thuoc jframe dua tren kich thuoc cac component ma no chua
		setLocationRelativeTo(null);// set cua so o giua man hinh
		setVisible(true);
	}
}
