package tests;


import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import gui.Grid;
import tools.ControlListener;

public class BasicListenerTest {
	public static void main(String[] args) {
		testBasicListenerShouldDrawDotOnKeyPress();
	}
	
	private static void testBasicListenerShouldDrawDotOnKeyPress() {
		
		Grid grid = new Grid(100,100);
		//ControlListener list = new BasicControlListener(new BasicToolCommand(), grid);
		ImageFrame imgFrame = new ImageFrame(grid.getView());
		//list.addListenerTo(imgFrame);
		//list.removeListenerFrom(imgFrame);
		Timer timer = new Timer(1000, x->new ImageFrame(grid.getView()));
		timer.start();
	}
	
}
