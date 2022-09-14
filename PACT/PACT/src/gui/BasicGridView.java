package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class BasicGridView extends GridView{
	private static final long serialVersionUID = 7909369711890727799L;
	
	private int width, height;
	public BasicGridView(int width, int height, int pixelWidth, int pixelHeight, PreviewPanel previewPnl) {
		super(width, height, pixelWidth, pixelHeight, previewPnl);
		this.width = width;
		this.height = height;	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		getToolBar().setBounds(0, 0, getWidth(), getToolBarHeight());
		g.clearRect(0, 0, width, height);
		getGrid().updateView();
		BufferedImage gridImg = getGrid().getView();
		g.drawImage(gridImg, 0, getToolBarHeight(), width, height, null);
	}

	@Override
	public Dimension getDimensions() {
		return getPreferredSize();
	}

	@Override
	public Point getOffset() {
		return new Point(0, -getToolBarHeight());
	}

}
