package tests;

import java.awt.Color;
import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;
import tools.Command;
import tools.Tools;

public class PrintTestCommand implements Command {

	@Override
	public void doAction(Grid grid, HashMap<String, Integer> mouseInfo) {
		System.out.println("Test Print Command");

	}


	@Override
	public CommandToolBar getToolBar() {
		// TODO Auto-generated method stub
		return new CommandToolBar();
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public Tools getToolID() {
		return Tools.shape;
	}


}
