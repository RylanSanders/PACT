package tools;

import java.util.HashMap;

import gui.GridMediator;

public class ToolExecutionThread extends Thread {
	private Command cmd;
	private GridMediator gridMed;
	private HashMap<String, Integer> mouseInfo;
	private boolean isRunning = false;

	public ToolExecutionThread(Command cmd, GridMediator gridMed, HashMap<String, Integer> mouseInfo) {
		this.cmd = cmd;
		this.gridMed = gridMed;
		this.mouseInfo = mouseInfo;
	}

	@Override
	public void run() {
		isRunning = true;
		if (!cmd.isActive()) {
			cmd.doAction(gridMed.getGrid(), mouseInfo);
			isRunning = false;
		} else {
			while (isRunning) {
				cmd.doAction(gridMed.getGrid(), mouseInfo);
				gridMed.refreshGridView();
			}
		}
		gridMed.refreshGridView();

	}

	public void endRunningThread() {
		isRunning = false;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setMouseInfo(HashMap<String, Integer> mouseInfo) {
		this.mouseInfo = mouseInfo;
	}

	public Tools getCurrentTool() {
		return cmd.getToolID();
	}

}
