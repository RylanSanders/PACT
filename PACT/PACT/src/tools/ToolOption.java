package tools;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

public abstract class ToolOption extends JComponent {
	private static final long serialVersionUID = -744574897388438297L;

	public abstract void setBehavior(ActionListener f);
}
