package gui;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class BasicFactory implements AbstractFactory{

	@Override
	public JButton getButton(String str) {
		JButton toRet = new JButton(str);
		toRet.addActionListener(x->toRet.getParent().requestFocus());
		return toRet;
	}

	@Override
	public JPanel getGridView(int width, int height, int pixelWidth, int pixelHeight, PreviewPanel previewPnl) {
		return new ScaledGridView(width, height, pixelWidth, pixelHeight, previewPnl);
	}

	@Override
	public Window getWidgetWindow() {
		JFrame toRet = new JFrame();
		toRet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return toRet;
	}

	@Override
	public JToolBar getToolBar() {
		return new JToolBar();
	}

	@Override
	public JFileChooser getFileChooser() {
		return new JFileChooser();
	}

}
