package tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.event.MouseInputAdapter;

import gui.GridMediator;

public class DragControlListener extends MouseInputAdapter implements ControlListener {

	private Command cmd;
	private GridMediator gridMed;
	private HashMap<String, Integer> mouseInfo;

	public DragControlListener(Command cmd, GridMediator gridMed) {
		this.cmd = cmd;
		this.gridMed = gridMed;
		mouseInfo = new HashMap<>();
	}

	public DragControlListener(GridMediator gridMed) {
		this.gridMed = gridMed;
		mouseInfo = new HashMap<>();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Dimension gridViewDim = gridMed.getGridViewPreferredSize();
		Utilities.addConvertedCoords(e.getPoint(), gridViewDim, gridMed.getGrid(), "Starting Mouse X",
				"Starting Mouse Y", mouseInfo, gridMed.getOffset());
		Utilities.addConvertedCoords(e.getPoint(), gridViewDim, gridMed.getGrid(), "Mouse X", "Mouse Y", mouseInfo,
				gridMed.getOffset());
		gridMed.executeCommand(cmd, mouseInfo);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gridMed.endCurrentCommand(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Dimension gridViewDim = gridMed.getGridViewPreferredSize();
		Utilities.addConvertedCoords(e.getPoint(), gridViewDim, gridMed.getGrid(), "Mouse X", "Mouse Y", mouseInfo,
				gridMed.getOffset());
		gridMed.setMouseInfo(mouseInfo);
	}

	@Override
	public void addListenerTo(Component comp) {
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
	}

	@Override
	public void removeListenerFrom(Component comp) {
		comp.removeMouseListener(this);
		comp.removeMouseMotionListener(this);
	}

	@Override
	public void setCommand(Command cmd) {
		this.cmd = cmd;
	}

	@Override
	public Command getCommand() {
		return cmd;
	}

	@Override
	public String toString() {
		return "Drag Listener";
	}

}
