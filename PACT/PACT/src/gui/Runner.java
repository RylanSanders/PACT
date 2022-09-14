package gui;

import javax.swing.SwingUtilities;

public class Runner {
	public static void main(String[] args) {
		runAppFrame();
	}
	
	private static void runAppFrame() {
		SwingUtilities.invokeLater(AppFrame::new);
	}
}
