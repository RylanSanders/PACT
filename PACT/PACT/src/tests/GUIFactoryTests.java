package tests;

import javax.swing.*;

import gui.AbstractFactory;
import gui.FactoryManager;

import java.awt.*;

public class GUIFactoryTests {
	public static void main(String[] args) {
		AbstractFactory factory = FactoryManager.getInstance().getFactory();
		 Window frame = factory.getWidgetWindow();
		 frame.setSize(500, 500);
		 //frame.add(factory.getButton("Hello"));
		 //frame.add(factory.getGridView());
		 //frame.add(factory.getToolBar());
		 frame.add(factory.getFileChooser());
		 frame.setVisible(true);
		 
	}
}
