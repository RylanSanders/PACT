package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import tools.Tools;

public class ToolPanel extends JPanel{
	private static final long serialVersionUID = 5776276987118217734L;
	
	private int width = 1, height = 7;
	private GridView grid;
	public ToolPanel(GridView grid) {
		setLayout(new GridLayout(height, width));
		this.grid = grid;
		
		addButton(Tools.pencil);
		addButton(Tools.eraser);
		addButton(Tools.fill);
		addButton(Tools.line);
		addButton(Tools.shape);
		addButton(Tools.select);
		addButton(Tools.eyedropper);
	}
	
	public void addButton(Tools tool) {
		JButton btn = FactoryManager.getInstance().getFactory().getButton(Tools.toolToString(tool));
		btn.setPreferredSize(new Dimension(50, 50));
		btn.setBackground(randomColor());
		btn.addActionListener(x->grid.setActiveTool(tool));
		add(btn);
	}
	
	private Color randomColor() {
		Random rand = new Random();
		return new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}
}
