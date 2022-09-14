package tests;

import javax.swing.SwingUtilities;

import gui.AppFrame;

public class AppFrameTest {
	public static void main(String[] args) {
		testAppFrame();
	}
	
	private static void testAppFrame() {
		SwingUtilities.invokeLater(AppFrame::new);
	}
}
