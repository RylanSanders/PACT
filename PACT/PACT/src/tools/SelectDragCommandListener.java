package tools;

import java.awt.event.MouseEvent;

import gui.GridMediator;

public class SelectDragCommandListener extends DragControlListener {
	private GridMediator gridMed;
	public SelectDragCommandListener(SelectCommand cmd, GridMediator gridMed) {
		super(cmd, gridMed);
		this.gridMed = gridMed;
		BasicSelectCommand sCmd = (BasicSelectCommand)getCommand();
		gridMed.addKeyControlListener(sCmd.getCopyListener());
		gridMed.addKeyControlListener(sCmd.getPasteListener());
	}
	
	public SelectDragCommandListener(GridMediator gridMed) {
		super(gridMed);
		this.gridMed = gridMed;
	}
	
	@Override
	public void setCommand(Command cmd) {
		super.setCommand(cmd);
		BasicSelectCommand sCmd = (BasicSelectCommand)getCommand();
		gridMed.addKeyControlListener(sCmd.getCopyListener());
		gridMed.addKeyControlListener(sCmd.getPasteListener());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		BasicSelectCommand sCmd = (BasicSelectCommand)getCommand();
		if(sCmd.isSelecting()) {
			sCmd.endSelection();
			gridMed.endCurrentCommand(false);
		}
			
		else {
			sCmd.setSelecting(false);
			super.mouseReleased(e);
			sCmd.clearSelection();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
	}
	

}
