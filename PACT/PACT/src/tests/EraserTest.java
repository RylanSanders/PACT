package tests;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import gui.BasicGridView;
import gui.GridView;
import tools.Tools;

public class EraserTest {
	public static void main(String[] args) {
		GridView grid = new BasicGridView(500, 500, 10, 10, null);
		JFrame frame = new JFrame("GridView Test Frame");
		frame.add(grid);
		
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				grid.setActiveTool(Tools.eraser);
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		//listener.addListenerTo(grid);
		/*Timer timer = new Timer(100, x->grid.repaint());
		Timer timer2 = new Timer(100, x->frame.repaint());
		timer.start();
		timer2.start();*/
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
