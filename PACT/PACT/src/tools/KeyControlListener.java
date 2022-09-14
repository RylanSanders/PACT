package tools;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import gui.GridMediator;

public class KeyControlListener implements ControlListener {
	private GridMediator gridMed;
	private Command cmd;
	private String key;
	private Consumer<GridMediator> c;
	public KeyControlListener(Command cmd, GridMediator gridMed, String key) {
		this.cmd = cmd;
		this.gridMed = gridMed;
		this.key = key;
	}
	
	public KeyControlListener(GridMediator gridMed, String key) {
		this.gridMed = gridMed;
		this.key = key;
	}
	
	public KeyControlListener(GridMediator gridMed, String key, Consumer<GridMediator> c) {
		this.gridMed = gridMed;
		this.key = key;
		this.c = c;
	}
	
	public KeyControlListener(String key, Consumer<GridMediator> c) {
		this.key = key;
		this.c = c;
	}

	@Override
	public void addListenerTo(Component comp) {
		JComponent jComp = (JComponent)comp;
		jComp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), this.hashCode());
		jComp.getActionMap().put(this.hashCode(),new AbstractAction() {
			private static final long serialVersionUID = 4265396072493571481L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(c==null)
					cmd.doAction(gridMed.getGrid(), new HashMap<String, Integer>());
				else
					c.accept(gridMed);
			}
		
		});
	}

	@Override
	public void removeListenerFrom(Component comp) {
		JComponent jComp = (JComponent)comp;
		jComp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(key));
	}

	@Override
	public void setCommand(Command cmd) {
		this.cmd = cmd;
	}

	@Override
	public Command getCommand() {
		return cmd;
	}
	
	public void setGridMediator(GridMediator gridMed) {
		this.gridMed = gridMed;
	}

}
