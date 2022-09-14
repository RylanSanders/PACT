package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;

public class LineCommand implements Command{
	private final Tools tool = Tools.line;
	private int thickness = 1;
	private CommandToolBar cmdBar;
	public LineCommand() {
		cmdBar = new CommandToolBar();
		ButtonOption btnOp = new ButtonOption("Color");
		btnOp.setBehavior(x->cmdBar.getPreviewPanel().setPanel("Color", this));
		cmdBar.addOption(btnOp);
		
		TextFieldOption txtOp = new TextFieldOption();
		txtOp.setBehavior(x->this.setThickness(Integer.parseInt(txtOp.getTextField().getText())));
		cmdBar.addOption(txtOp);
	}
	
	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		int width = grid.getView().getWidth();
		int height = grid.getView().getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gImg = img.createGraphics();
		
		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");
		int x2 = mouseInfo.get("Starting Mouse X");
		int y2 = mouseInfo.get("Starting Mouse Y");
		
		gImg.setStroke(new BasicStroke(thickness));
		gImg.setColor(new Color(getToolBar().getPreviewPanel().getPanelVal("Color")));
		gImg.drawLine(x, y, x2, y2);
		gImg.dispose();
		grid.setLayer(Tools.toolIDInt(tool)+1, img);
		
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
	
	public void setThickness(int t) {
		thickness = t;
	}
	
}
