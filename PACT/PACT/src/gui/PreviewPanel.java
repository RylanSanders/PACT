package gui;

import java.awt.CardLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import tools.Command;

public class PreviewPanel extends JPanel {
	private static final long serialVersionUID = -65340454542273911L;
	
	private CardLayout layout;
	private ImprovedColorPanel col;
	private List<Command> cmds;
	private PreviewContentPanel activePnl;
	private HashMap<String, PreviewContentPanel> contentPnls;
	public PreviewPanel() {
		layout = new CardLayout();
		cmds = new ArrayList<>();
		contentPnls = new HashMap<>();
		setLayout(layout);
		
		col = new ImprovedColorPanel(this);
		add(col, "Color");
		contentPnls.put("Color", col);
		activePnl = col;
	}
	
	public void setPanel(String pnlName, Command cmd) {
		cmds.add(cmd);
		layout.show(this, pnlName);
		activePnl = contentPnls.get(pnlName);
		repaint();
	}
	
	public void addPanel(JPanel nextPnl, String pnlName) {
		add(nextPnl, pnlName);
	}
	
	public PreviewContentPanel getPanel(String pnlName) {
		return contentPnls.get(pnlName);
	}
	
	
	public int getPanelVal(String valName) {
		return activePnl.getVal(valName);
	}
	
	
}

