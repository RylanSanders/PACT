package tests;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.FactoryManager;
import gui.Grid;

public class BasicCommandTest {
	public static void main(String[] args) {
		testActionShouldDrawDot();
	}
	
	//Tests the basic command tool which is meant for testing purposes
	private static void testActionShouldDrawDot() {
		Grid grid = new Grid(100,100);
		new BasicToolCommand().doAction(grid, null);
		grid.updateView();
		SwingUtilities.invokeLater(()->new ImageFrame(grid.getView()));
	}
	
}
