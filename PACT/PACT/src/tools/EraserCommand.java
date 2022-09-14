package tools;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;

public class EraserCommand implements Command {
	private final Tools tool = Tools.eraser;
	private int size = 1;
	private CommandToolBar cmdBar;
	public EraserCommand() {
		cmdBar = new CommandToolBar();
		TextFieldOption sizeOp = new TextFieldOption();
		sizeOp.setBehavior(x->this.setSize(Integer.parseInt(sizeOp.getTextField().getText())));
		cmdBar.addOption(sizeOp);
	}
	
	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");
		grid.updateView();
		
		BufferedImage img = grid.getLayer(Grid.BASE_LAYER);
		Graphics2D gImg = img.createGraphics();
		
		gImg.fillRect(x-size/2, y-size/2, size, size);
		grid.setLayer(Grid.BASE_LAYER, img);
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
	
	public void setSize(int i) {
		size = i;
	}

}
