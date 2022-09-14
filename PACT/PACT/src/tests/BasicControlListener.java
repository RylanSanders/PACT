package tests;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import gui.Grid;
import gui.GridMediator;
import tools.Command;
import tools.ControlListener;
import tools.Utilities;

public class BasicControlListener extends MouseAdapter implements ControlListener {

	private Command cmd;
	private GridMediator gridMed;
	public BasicControlListener(Command cmd, GridMediator gridMed) {
		this.cmd = cmd;
		this.gridMed = gridMed;
	}
	
	public BasicControlListener(GridMediator med) {
		this.gridMed = med;
	}

	@Override
	public void addListenerTo(Component comp) {
		comp.addMouseListener(this);
	}
	@Override
	public void setCommand(Command cmd) {
		this.cmd = cmd;
		
	}
	@Override
	public void removeListenerFrom(Component comp) {
		comp.removeMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		HashMap<String, Integer> mouseInfo = new HashMap<>();
		mouseInfo.put("Mouse X", e.getX());
		mouseInfo.put("Mouse Y", e.getY());
		gridMed.executeCommand(cmd, mouseInfo);
	}
	
	private Container getTopContainer(Component comp) {
		Component cursor = comp;
		while(cursor.getParent()!=null)
			cursor = cursor.getParent();
		return (Container)cursor;
	}


	@Override
	public Command getCommand() {
		// TODO Auto-generated method stub
		return cmd;
	}
	
	@Override
	public String toString() {
		return "Basic Control Listener";
	}

}
