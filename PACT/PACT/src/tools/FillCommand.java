package tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import gui.CommandToolBar;
import gui.Grid;

public class FillCommand implements Command {
	private Color color = Color.black;
	private Tools tool = Tools.fill;
	private Set<Point> test;
	private CommandToolBar cmdBar;

	public FillCommand() {
		cmdBar = new CommandToolBar();
		ButtonOption btnOp = new ButtonOption("Color");
		btnOp.setBehavior(x -> cmdBar.getPreviewPanel().setPanel("Color", this));
		cmdBar.addOption(btnOp);
	}

	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		test = new HashSet<Point>();
		BufferedImage gridImg = grid.getLayer(Grid.BASE_LAYER);
		BufferedImage img = Utilities.getBlankImage(gridImg.getWidth(), gridImg.getHeight());
		
		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");
		color = new Color(cmdBar.getPreviewPanel().getPanelVal("Color"));

		fillRec(img,gridImg, x, y, gridImg.getRGB(x, y));
		grid.setLayer(Tools.toolIDInt(tool) + 1, img);

	}

	private void fillRec(BufferedImage img, BufferedImage gridImg, int x, int y, int col) {
		if (x < 0 || x > gridImg.getWidth() - 1 || y < 0 || y > gridImg.getHeight() - 1)
			return;
		if (gridImg.getRGB(x, y) != col)
			return;
		if (test.contains(new Point(x, y)))
			return;

		img.setRGB(x, y, color.getRGB());
		test.add(new Point(x, y));

		fillRec(img, gridImg, x + 1, y, col);
		fillRec(img, gridImg, x - 1, y, col);
		fillRec(img, gridImg, x, y + 1, col);
		fillRec(img, gridImg, x, y - 1, col);

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
}
