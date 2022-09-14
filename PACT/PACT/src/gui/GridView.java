package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import tools.BasicSelectCommand;
import tools.Command;
import tools.ControlListener;
import tools.DragControlListener;
import tools.EraserCommand;
import tools.EyeDropperCommand;
import tools.FillCommand;
import tools.KeyControlListener;
import tools.LineCommand;
import tools.PencilCommand;
import tools.SelectDragCommandListener;
import tools.ShapeCommand;
import tools.Tools;

public abstract class GridView extends JPanel {
	private static final long serialVersionUID = 5935745687973321329L;

	private Grid grid;
	private List<ControlListener> activeListeners;
	private List<ControlListener> passiveListeners;
	private Tools activeTool = Tools.pencil;
	private GridMediator gridMed;
	private PreviewPanel previewPnl;
	private CommandToolBar toolBar;
	private final int TOOL_BAR_HEIGHT = 30;

	public GridView(int width, int height, int pixelWidth, int pixelHeight, PreviewPanel previewPnl) {
		activeListeners = new ArrayList<>();
		passiveListeners = new ArrayList<>();
		setLayout(null);
		setPreferredSize(new Dimension(width, height));
		grid = new Grid(pixelWidth, pixelHeight);
		gridMed = new GridMediator(this);
		this.previewPnl = previewPnl;

		initListeners();
		
		activateTool(activeTool);
	}

	public Grid getGrid() {
		return grid;
	}

	public void setActiveTool(Tools tool) {
		deactivateTool(tool);
		activateTool(tool);
	}
	
	private void activateTool(Tools tool) {
		ControlListener newListener = activeListeners.get(Tools.toolIDInt(tool));
		newListener.addListenerTo(this);
		activeTool = tool;
		
		toolBar = activeListeners.get(Tools.toolIDInt(tool)).getCommand().getToolBar();
		toolBar.setPreviewPanel(previewPnl);
		previewPnl.setPanel("Color", newListener.getCommand());
		add(toolBar);
		toolBar.setBounds(0, 0, getWidth(), TOOL_BAR_HEIGHT);
		
		toolBar.repaint();
		previewPnl.repaint();
	}
	
	private void deactivateTool(Tools tool) {
		ControlListener oldListener = activeListeners.get(Tools.toolIDInt(activeTool));
		oldListener.removeListenerFrom(this);
		remove(toolBar);
	}



	public void setUpListener(Command cmd, ControlListener listener) {
		listener.setCommand(cmd);

		List<ControlListener> addTo = (cmd.isActive()) ? activeListeners : passiveListeners;
		addTo.add(listener);

		if (!cmd.isActive())
			listener.addListenerTo(this);
	}

	public void setUpListener(KeyControlListener kcl) {
		passiveListeners.add(kcl);
		kcl.addListenerTo(this);
	}

	private void initListeners() {
		setUpListener(new PencilCommand(), new DragControlListener(gridMed));
		setUpListener(new EraserCommand(), new DragControlListener(gridMed));
		setUpListener(new FillCommand(), new DragControlListener(gridMed));
		setUpListener(new LineCommand(), new DragControlListener(gridMed));
		setUpListener(new ShapeCommand(), new DragControlListener(gridMed));
		setUpListener(new BasicSelectCommand(), new SelectDragCommandListener(gridMed));
		setUpListener(new EyeDropperCommand(), new DragControlListener(gridMed));

		setUpListener(new KeyControlListener(gridMed, "Z", x -> x.undoAction()));
		setUpListener(new KeyControlListener(gridMed, "Y", x -> x.redoAction()));
	}

	public int getToolBarHeight() {
		return TOOL_BAR_HEIGHT;
	}

	public CommandToolBar getToolBar() {
		return toolBar;
	}

	public void remakeGrid(int newWidth, int newHeight) {
		grid = new Grid(newWidth, newHeight);
	}

	public abstract Dimension getDimensions();

	public abstract Point getOffset();
}
