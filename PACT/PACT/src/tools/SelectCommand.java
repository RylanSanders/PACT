package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;

public class SelectCommand implements Command {
	//Not Used TODO implement Correctly
	private final Tools tool = Tools.select;
	private CommandToolBar cmdBar;
	private Rectangle selectedArea, tempSelectedArea;
	private boolean isSelecting = true;

	public SelectCommand() {
		cmdBar = new CommandToolBar();
	}

	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		int width = grid.getView().getWidth();
		int height = grid.getView().getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gImg = img.createGraphics();
		gImg.setColor(Color.BLACK);

		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");
		int x2 = mouseInfo.get("Starting Mouse X");
		int y2 = mouseInfo.get("Starting Mouse Y");

		if (isSelecting) {
			int startX = (x2 < x) ? x2 : x;
			int startY = (y2 < y) ? y2 : y;

			tempSelectedArea = new Rectangle(startX, startY, Math.abs(x - x2), Math.abs(y - y2));

			gImg.draw(tempSelectedArea);
			gImg.dispose();
			grid.setLayer(Tools.toolIDInt(tool) + 1, img);
		} else {
			if (selectedArea.contains(new Point(x2, y2))) {
				img = grid.getLayer(Grid.BASE_LAYER);
				grid.updateView();
				BufferedImage toRetImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D gRet = toRetImg.createGraphics();

				int sX = selectedArea.x - 1;
				int sY = selectedArea.y - 1;
				int sW = selectedArea.width - 1;
				int sH = selectedArea.height - 1;
				int xDiff = x - x2;
				int yDiff = y - y2;

				gRet.drawImage(img, sX + xDiff, sY + yDiff, sX + xDiff + sW, sY + yDiff + sH, sX, sY, sX + sW, sY + sH,
						null);
				gRet.setColor(Color.black);
				gRet.drawRect(sX + xDiff, sY + yDiff, sW, sH);
				grid.setLayer(Tools.toolIDInt(tool) + 1, toRetImg);
			}
		}

	}

	@Override
	public CommandToolBar getToolBar() {
		return cmdBar;
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public Tools getToolID() {
		return tool;
	}

	public void endSelection() {
		selectedArea = tempSelectedArea;
		tempSelectedArea = null;
		isSelecting = false;
	}

	public boolean isSelecting() {
		return isSelecting;
	}

	public void clearSelection() {
		isSelecting = true;

	}

}
