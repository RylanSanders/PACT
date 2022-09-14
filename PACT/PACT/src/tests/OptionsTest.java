package tests;

import javax.swing.JFrame;

import gui.CommandToolBar;
import tools.ButtonOption;
import tools.TextFieldOption;

import javax.swing.*;
import java.awt.*;

public class OptionsTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(500, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CommandToolBar cmd = new CommandToolBar();
		TextFieldOption op = new TextFieldOption();
		op.setBehavior(x->System.out.println(op.getTextField().getText()));
		cmd.addOption(op);
		ButtonOption op2 = new ButtonOption("Color");
		op2.setBehavior(x->System.out.println("Color"));
		cmd.addOption(op2);
		frame.add(cmd);
		frame.setVisible(true);
	}
}
