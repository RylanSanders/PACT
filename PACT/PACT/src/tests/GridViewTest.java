package tests;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.BasicGridView;
import gui.FactoryManager;
import gui.GridView;
import gui.PreviewPanel;
import gui.ResizeableGridView;
import tools.Command;
import tools.ControlListener;
import tools.DragControlListener;
import tools.LineCommand;
import tools.Tools;
import tools.Utilities;

import java.util.Map;
public class GridViewTest {
	public static void main(String[] args) {
		//testListenerOnGridView();
		//testGridMediator();
		//testResizeGridView();
		//testGridViewOnBigGUI();
		//testListenersRemoveAndAdd();
		//testGridViewSetActiveTool();
		testGridGridViewSize();
		//testConvertCoords();
	}
	
	//should draw dot when key pressed
	//Passed but depreciated
	private static void testListenerOnGridView() {
		BasicGridView grid = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		frame.add(grid);
		Command cmd = new BasicToolCommand();
		//ControlListener listener = new BasicControlListener(cmd, grid.getGrid());
		//listener.addListenerTo(grid);
		Timer timer = new Timer(100, x->grid.repaint());
		Timer timer2 = new Timer(100, x->frame.repaint());
		timer.start();
		timer2.start();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void testGridMediator() {
		GridView gridView = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		frame.add(gridView);
		

		//listener.addListenerTo(grid);
		/*Timer timer = new Timer(100, x->grid.repaint());
		Timer timer2 = new Timer(100, x->frame.repaint());
		timer.start();
		timer2.start();*/
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void testResizeGridView() {
		//TODO work on
		GridView gridView = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		frame.add(gridView);
		

		//listener.addListenerTo(grid);
		/*Timer timer = new Timer(100, x->grid.repaint());
		Timer timer2 = new Timer(100, x->frame.repaint());
		timer.start();
		timer2.start();*/
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void testGridViewOnBigGUI() {
		GridView gridView = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		frame.setLayout(new GridLayout(1, 2));
		frame.add(gridView, 0, 0);
		frame.add(new JButton("Test"), 0, 1);

		//listener.addListenerTo(grid);
		/*Timer timer = new Timer(100, x->grid.repaint());
		Timer timer2 = new Timer(100, x->frame.repaint());
		timer.start();
		timer2.start();*/
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void testListenersRemoveAndAdd() {
		GridView gridView = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		//ControlListener list = new DragListener(gridView.getMediator());
		//ControlListener list2 = new BasicControlListener(gridView.getMediator());
		//list.setCommand(new LineCommand());
		//list2.setCommand(new BasicToolCommand());
		//list.addListenerTo(gridView);
		//list.removeListenerFrom(gridView);
		//list2.addListenerTo(gridView);
		frame.add(gridView);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void testGridViewSetActiveTool() {
		GridView gridView = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		frame.add(gridView);
		
		
		gridView.setActiveTool(Tools.pencil);
		gridView.setActiveTool(Tools.eraser);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				gridView.setActiveTool(Tools.eraser);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				gridView.setActiveTool(Tools.pencil);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
			}
		
		});
		//listener.addListenerTo(grid);
		/*Timer timer = new Timer(100, x->grid.repaint());
		Timer timer2 = new Timer(100, x->frame.repaint());
		timer.start();
		timer2.start();*/
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void testGridGridViewSize() {
		GridView gridView = new BasicGridView(500, 500, 10, 10, new PreviewPanel());
		JFrame frame = new JFrame("GridView Test Frame");
		frame.add(gridView);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//Should print a map with the keys x, y and  values 20, 30
	private static void testConvertCoords() {
		JPanel pnl = new JPanel();
		pnl.setPreferredSize(new Dimension(500, 500));
		JPanel pnl2 = new JPanel();
		pnl2.setPreferredSize(new Dimension(50, 50));
		Point p = new Point(200, 300);
		//Map<String, Integer> m = Utilities.convertCoords(p, pnl, pnl2, "X", "Y");
		//System.out.println(m);
	}
}
