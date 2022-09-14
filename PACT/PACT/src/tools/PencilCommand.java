package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;

public class PencilCommand implements Command {
	private final Tools tool = Tools.pencil;
	private int size = 1;
	private CommandToolBar cmdBar;

	public PencilCommand() {
		cmdBar = new CommandToolBar();
		ButtonOption btnOp = new ButtonOption("Color");
		btnOp.setBehavior(x -> cmdBar.getPreviewPanel().setPanel("Color", this));
		cmdBar.addOption(btnOp);

		TextFieldOption txtOp = new TextFieldOption();
		txtOp.setBehavior(x -> this.setSize(Integer.parseInt(txtOp.getTextField().getText())));
		cmdBar.addOption(txtOp);
	}

	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		BufferedImage img = grid.getLayer(Grid.BASE_LAYER);
		Graphics2D gImg = img.createGraphics();

		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");

		gImg.setStroke(new BasicStroke(size));
		gImg.setColor(new Color(cmdBar.getPreviewPanel().getPanelVal("Color")));
		gImg.fillRect(x - size / 2, y - size / 2, size, size);
		gImg.dispose();
		grid.setLayer(Tools.toolIDInt(tool) + 1, img);

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
		return Tools.pencil;
	}

	private void setSize(int i) {
		size = i;
	}
}
