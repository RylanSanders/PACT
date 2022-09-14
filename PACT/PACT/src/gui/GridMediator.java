package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import tools.Command;
import tools.KeyControlListener;
import tools.ToolExecutionThread;
import tools.Tools;

public class GridMediator {
	private GridView gridView;
	private ToolExecutionThread tet;
	private LimitedStack<BufferedImage> previousImgs, futureImgs;

	public GridMediator(GridView gridView) {
		this.gridView = gridView;
		previousImgs = new LimitedStack<>(40);
		futureImgs = new LimitedStack<>(40);
	}

	public void executeCommand(Command cmd, HashMap<String, Integer> mouseInfo) {
		if (tet == null || !tet.isRunning()) {
			previousImgs.put(gridView.getGrid().getView());
			tet = new ToolExecutionThread(cmd, this, mouseInfo);
			tet.start();
		}
	}

	public void endCurrentCommand(boolean mergeLayerWithBase) {
		tet.endRunningThread();
		if (mergeLayerWithBase)
			gridView.getGrid().mergeLayers(Grid.BASE_LAYER, Tools.toolIDInt(tet.getCurrentTool()) + 1);
	}

	public Grid getGrid() {
		return gridView.getGrid();
	}

	public void refreshGridView() {
		gridView.repaint();
	}

	public Dimension getGridViewPreferredSize() {
		return gridView.getDimensions();
	}

	public Point getOffset() {
		return gridView.getOffset();
	}

	public void setMouseInfo(HashMap<String, Integer> mouseInfo) {
		tet.setMouseInfo(mouseInfo);
	}

	public int getGridToolBarHeight() {
		return gridView.getToolBarHeight();
	}

	public void undoAction() {
		futureImgs.put(getGrid().getView());
		try {
			getGrid().setLayer(Grid.BASE_LAYER, previousImgs.pop());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		refreshGridView();

	}

	public void redoAction() {
		previousImgs.put(getGrid().getView());
		try {
			getGrid().setLayer(Grid.BASE_LAYER, futureImgs.pop());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		refreshGridView();
	}

	public void addKeyControlListener(KeyControlListener kcl) {
		kcl.setGridMediator(this);
		gridView.setUpListener(kcl);
	}

	public GridView getGridView() {
		return gridView;
	}

}
