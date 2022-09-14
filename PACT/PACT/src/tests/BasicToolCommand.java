package tests;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import gui.CommandToolBar;
import gui.Grid;
import tools.Command;
import tools.Tools;

public class BasicToolCommand implements Command{

	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		int width = grid.getView().getWidth();
		int height = grid.getView().getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gImg = img.createGraphics();
		gImg.setColor(Color.BLACK);
		int x = mouseInfo.get("Mouse X");
		int y = mouseInfo.get("Mouse Y");
		gImg.fillOval(x-25 , y-25, 50, 50);
		gImg.dispose();
		grid.setLayer(2, img);
		grid.mergeLayers(Grid.BASE_LAYER, 2);
	}


	@Override
	public CommandToolBar getToolBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Tools getToolID() {
		// TODO Auto-generated method stub
		return Tools.pencil;
	}

}
