package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ScaledGridView extends GridView {
	private static final long serialVersionUID = -9038847779095996127L;

	private int width, height, viewWidth, viewHeight, startX, startY;

	public ScaledGridView(int width, int height, int pixelWidth, int pixelHeight, PreviewPanel previewPnl) {
		super(width, height, pixelWidth, pixelHeight, previewPnl);
		this.width = width;
		this.height = height;
		initVars(pixelWidth, pixelHeight);
	}

	private void initVars(int pixelWidth, int pixelHeight) {
		int hFactor = height / pixelHeight;
		int wFactor = width / pixelWidth;
		if (hFactor < wFactor) {
			viewWidth = pixelWidth * hFactor;
			viewHeight = height - 80;
			startX = (width - viewWidth) / 2;
			startY = getToolBarHeight();
		} else {
			viewWidth = width;
			viewHeight = pixelHeight * wFactor;
			startX = 0;
			startY = (height - viewHeight - getToolBarHeight()) / 2;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		getToolBar().setBounds(0, 0, getWidth(), getToolBarHeight());
		g.clearRect(0, 0, width, height);
		getGrid().updateView();
		BufferedImage gridImg = getGrid().getView();
		g.drawImage(gridImg, startX, startY, viewWidth, viewHeight, null);
		g.drawRect(startX, startY, viewWidth, viewHeight);
	}

	@Override
	public Dimension getDimensions() {
		return new Dimension(viewWidth, viewHeight);
	}

	@Override
	public Point getOffset() {
		return new Point(-startX, -startY);
	}

	@Override
	public void remakeGrid(int newWidth, int newHeight) {
		super.remakeGrid(newWidth, newHeight);
		initVars(newWidth, newHeight);
	}

}
