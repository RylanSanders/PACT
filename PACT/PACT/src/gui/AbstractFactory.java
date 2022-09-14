package gui;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public interface AbstractFactory {
public JButton getButton(String str);
public JPanel getGridView(int width, int height, int pixelWidth, int pixelHeight, PreviewPanel pnl);
public Window getWidgetWindow();
public JToolBar getToolBar();
public JFileChooser getFileChooser();
}
