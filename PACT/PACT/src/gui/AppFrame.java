package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class AppFrame extends JFrame {
	private static final long serialVersionUID = 5927664067446052032L;
	
	private JPanel toolPnl;
	private PreviewPanel previewPnl;
	private GridView gridView;
	private JMenuBar frameBarPnl;
	private RelatableRectHolder gridRect, toolRect, prevRect, frameBarRect;
	private RInt width, height;

	public AppFrame() {
		super("PACT");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setSize(1500, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setRectBounds();

		previewPnl = new PreviewPanel();
		gridView = new ScaledGridView(gridRect.w.getValue(), gridRect.h.getValue(), 50, 50, previewPnl);
		toolPnl = new ToolPanel(gridView);
		FileManager fm = new FileManager(gridView);

		frameBarPnl = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newGridItem = new JMenuItem("New Grid");
		newGridItem.addActionListener(this::showNewGridDialog);
		fileMenu.add(newGridItem);

		JMenu operationsMenu = new JMenu("Operations");
		JMenuItem clearGridItem = new JMenuItem("Clear Grid");
		clearGridItem.addActionListener(this::clearGrid);
		operationsMenu.add(clearGridItem);

		JMenuItem saveImageItem = new JMenuItem("Save");
		saveImageItem.addActionListener(x -> fm.runFileSelection("Save"));
		fileMenu.add(saveImageItem);

		JMenuItem openImageItem = new JMenuItem("Open");
		openImageItem.addActionListener(x -> fm.runFileSelection("Open"));
		fileMenu.add(openImageItem);

		JMenuItem saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(x -> fm.runFileSelection("Save As"));
		fileMenu.add(saveAsItem);

		frameBarPnl.add(fileMenu);
		frameBarPnl.add(operationsMenu);

		add(gridView);
		add(toolPnl);
		add(previewPnl);
		add(frameBarPnl);

		gridView.setBounds(gridRect.getRectangle());
		toolPnl.setBounds(toolRect.getRectangle());
		previewPnl.setBounds(prevRect.getRectangle());
		frameBarPnl.setBounds(frameBarRect.getRectangle());

		setVisible(true);
		requestFocus();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		width.setValue(this.getWidth());
		height.setValue(this.getHeight());

		gridView.setBounds(gridRect.getRectangle());
		toolPnl.setBounds(toolRect.getRectangle());
		previewPnl.setBounds(prevRect.getRectangle());
		frameBarPnl.setBounds(frameBarRect.getRectangle());
	}

	private void setRectBounds() {
		width = RInt.makeRInt(this.getWidth());
		height = RInt.makeRInt(this.getHeight());

		// frameBar Rect
		RInt frameBarX = RInt.makeRInt(0);
		RInt frameBarY = RInt.makeRInt(0);
		RInt frameBarWidth = RInt.makeRInt(width, RInt.makeRInt(0), Operations.constant());
		RInt frameBarHeight = RInt.makeRInt(30);
		frameBarRect = new RelatableRectHolder(frameBarX, frameBarY, frameBarWidth, frameBarHeight);

		// ToolSelection Rect
		RInt toolX = RInt.makeRInt(0);
		RInt toolY = RInt.makeRInt(frameBarHeight, RInt.makeRInt(0), Operations.constant());
		RInt toolWidth = RInt.makeRInt(75);
		RInt toolHeight = RInt.makeRInt(height, frameBarHeight, Operations.subtract());
		toolRect = new RelatableRectHolder(toolX, toolY, toolWidth, toolHeight);

		// Preview Rect
		RInt prevWidth = RInt.makeRInt(300);
		RInt prevX = RInt.makeRInt(width, prevWidth, Operations.subtract());
		RInt prevY = RInt.makeRInt(frameBarHeight, RInt.makeRInt(0), Operations.constant());
		RInt prevHeight = RInt.makeRInt(height, frameBarHeight, Operations.subtract());
		prevRect = new RelatableRectHolder(prevX, prevY, prevWidth, prevHeight);

		// GridView Rect
		RInt gridX = RInt.makeRInt(toolX, toolWidth, Operations.sum());
		RInt gridY = RInt.makeRInt(frameBarHeight, RInt.makeRInt(0), Operations.constant());
		RInt gridWidth = RInt.makeRInt(width, RInt.makeRInt(toolWidth, prevWidth, Operations.sum()),
				Operations.subtract());
		RInt gridHeight = RInt.makeRInt(height, frameBarHeight, Operations.subtract());
		gridRect = new RelatableRectHolder(gridX, gridY, gridWidth, gridHeight);

	}

	private void showNewGridDialog(ActionEvent e) {
		JTextField width = new JTextField();
		JTextField height = new JTextField();
		width.setPreferredSize(new Dimension(50, 20));
		height.setPreferredSize(new Dimension(50, 20));
		final JComponent[] inputs = new JComponent[] { new JLabel("Width in Pixels"), width,
				new JLabel("Height in Pixels"), height };
		boolean continuePrompt = true;
		while (continuePrompt) {
			int result = JOptionPane.showConfirmDialog(this, inputs, "New Grid Options", JOptionPane.CANCEL_OPTION,
					JOptionPane.DEFAULT_OPTION);
			if (result == 0) {
				String sWidth = width.getText();
				String sHeight = height.getText();
				try {
					int endWidth = Integer.parseInt(sWidth);
					int endHeight = Integer.parseInt(sHeight);
					continuePrompt = false;
					gridView.remakeGrid(endWidth, endHeight);
					repaint();
				} catch (NumberFormatException err) {
					JOptionPane.showConfirmDialog(this, "Invalid Input, Please Try Again", "Input Error Dialog",
							JOptionPane.DEFAULT_OPTION, JOptionPane.CANCEL_OPTION);
				}
			} else
				continuePrompt = false;
		}
	}

	private void clearGrid(ActionEvent e) {
		Dimension gridDim = gridView.getGrid().getPreferredSize();
		gridView.remakeGrid(gridDim.width, gridDim.height);
		repaint();
	}
}
