package tools;

import java.util.HashMap;

import gui.CommandToolBar;
import gui.Grid;

public interface Command {
public void doAction(Grid grid, HashMap<String, Integer> mouseInfo);
public CommandToolBar getToolBar();
public boolean isActive();
public Tools getToolID();
}
