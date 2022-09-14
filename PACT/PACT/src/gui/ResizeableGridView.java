package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.MouseInputAdapter;

//TODO fix
public class ResizeableGridView extends GridView {

	private JScrollBar HBar, VBar;
	private ViewPanel viewPnl;
	private double sizePercent = 1.0;
	private final int BAR_SIZE = 20;
	private int width, height;

	public ResizeableGridView(int width, int height, int pixelWidth, int pixelHeight, PreviewPanel previewPnl) {
		super(width, height, pixelWidth, pixelHeight, previewPnl);
		this.height = height;
		this.width = width;

		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		HBar = new JScrollBar();
		HBar.setOrientation(HBar.HORIZONTAL);
		HBar.setMaximum(110);
		HBar.setMinimum(0);
		HBar.setValue(50);
		HBar.addAdjustmentListener(this::movePanel);

		VBar = new JScrollBar();
		VBar.setOrientation(VBar.VERTICAL);
		VBar.setMaximum(110);
		VBar.setMinimum(0);
		VBar.setValue(50);
		VBar.addAdjustmentListener(this::movePanel);

		viewPnl = new ViewPanel();
		viewPnl.setPreferredSize(new Dimension(480, 480));
		getGrid().updateView();
		viewPnl.setImagePanel(getGrid().getView());

		add(HBar);
		add(VBar);
		add(viewPnl);

		Dimension size = this.getPreferredSize();
		HBar.setBounds(0, size.height - BAR_SIZE, size.width, BAR_SIZE);
		VBar.setBounds(size.width - BAR_SIZE, 0, BAR_SIZE, size.height - BAR_SIZE);
		viewPnl.setBounds(0, 0, size.width - BAR_SIZE, size.height - BAR_SIZE);

		addMouseWheelListener(this::resizePanel);
		//addMouseMotionListener(new MouseMover());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		HBar.setBounds(0, getHeight() - BAR_SIZE, getWidth(), BAR_SIZE);
		VBar.setBounds(getWidth() - BAR_SIZE, 0, BAR_SIZE, getHeight() - BAR_SIZE);
		viewPnl.setBounds(0, 0, getWidth() - BAR_SIZE, getHeight() - BAR_SIZE);
		g.clearRect(0, 0, width, height);
		getGrid().updateView();
		viewPnl.setImagePanel(getGrid().getView());
	}

	private void resizePanel(MouseWheelEvent e) {
		sizePercent += e.getPreciseWheelRotation();
		double finPercent = (sizePercent < 1) ? 1 / (sizePercent - 1) : sizePercent;
		viewPnl.setSizePercent(finPercent);
		repaint();
	}

	private void movePanel(AdjustmentEvent e) {
		viewPnl.setWidthPercent((double) (HBar.getValue()) / (double) HBar.getMaximum() * 1.1);
		viewPnl.setHeightPercent((double) (VBar.getValue()) / (double) VBar.getMaximum() * 1.1);
		repaint();
	}

	class MouseMover extends MouseInputAdapter {
		private int startX, startY;
		private double sens = 1.0f;
		private boolean isCorrect = false;

		public MouseMover() {
			startX = startY = getWidth() / 2;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			System.out.println(e.getButton());
			if (e.getButton() != e.BUTTON1)
				isCorrect = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			//TODO e.getButton always returns 0 so can't set Controls
				int THRESH = 5;
				int SENSITIVITY = 2;
				if (isCorrect = false) {
					startX = e.getX();
					startY = e.getY();
					isCorrect = true;
				}
				int x = 0;
				if (x != e.getX() && Math.abs(e.getX() - startX) > THRESH)
					x = (startX < e.getX()) ? SENSITIVITY : -SENSITIVITY;

				int y = 0;
				if (y != e.getY() && Math.abs(e.getY() - startY) > THRESH)
					y = (startY < e.getY()) ? SENSITIVITY : -SENSITIVITY;

				HBar.setValue(HBar.getValue() + x);
				VBar.setValue(VBar.getValue() + y);
				startX = e.getX();
				startY = e.getY();
		}
	}

	class ViewPanel extends JPanel {
		private JPanel pnl;
		private Rectangle pnlRect;
		private int width, height;

		public ViewPanel() {
			setLayout(null);
			pnl = new ImageFillPanel(getGrid().getView());
			add(pnl);

			width = pnl.getPreferredSize().width;
			height = pnl.getPreferredSize().height;
			pnlRect = new Rectangle(200, 200, pnl.getPreferredSize().width, pnl.getPreferredSize().height);
			pnl.setBounds(pnlRect);
		}

		public void setWidthPercent(double percent) {
			int xPos = (int) (percent * (getWidth() - pnl.getWidth()));
			pnlRect.x = xPos;
			pnl.setBounds(pnlRect);
		}

		public void setHeightPercent(double percent) {
			int yPos = (int) (percent * (getHeight() - pnl.getHeight()));
			pnlRect.y = yPos;
			pnl.setBounds(pnlRect);
		}

		public void setSizePercent(double percent) {
			int hDiff = pnlRect.height - (int) (height * percent);
			int wDiff = pnlRect.width - (int) (width * percent);
			pnlRect.height = (int) (height * percent);
			pnlRect.width = (int) (width * percent);
			pnlRect.x = pnlRect.x + wDiff / 2;
			pnlRect.y = pnlRect.y + hDiff / 2;
			pnl.setBounds(pnlRect);
		}

		public void setImagePanel(BufferedImage img) {
			
			//pnl = new ImageFillPanel(img);
			
			//TODO testing
			BufferedImage test = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
			Graphics g = test.createGraphics();
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 200, 200);
			JLabel l = new JLabel(new ImageIcon(test));
			JPanel t = new JPanel();
			t.setPreferredSize(new Dimension(200, 200));
			t.add(l);
			pnl = t;
		}
	}

	@Override
	public Dimension getDimensions() {
		// TODO Auto-generated method stub
		return new Dimension(width, height);
	}

	@Override
	public Point getOffset() {
		// TODO Auto-generated method stub
		return new Point(0, 0);
	}
}

class ImageFillPanel extends JPanel {
	private BufferedImage img;

	public ImageFillPanel(BufferedImage img) {
		this.img = img;
		//img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(new Dimension(100, 100));
		//Graphics gImg = img.createGraphics();
		//gImg.setColor(Color.GREEN);
		//gImg.fillOval(10, 10, 20, 20);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}
}
