package tools;

import java.awt.Color;
import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;
import gui.PreviewContentPanel;

public class EyeDropperCommand implements Command {
	private Tools tool = Tools.eyedropper;
	private CommandToolBar cmdBar;

	public EyeDropperCommand() {
		cmdBar = new CommandToolBar();
	}

	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");
		grid.updateView();

		int colVal = grid.getView().getRGB(x, y);
		Color testCol = new Color(colVal, true);
		if (testCol.getAlpha() != 0) {
			PreviewContentPanel colPnl = cmdBar.getPreviewPanel().getPanel("Color");
			colPnl.setVal("Color", colVal);
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

}
