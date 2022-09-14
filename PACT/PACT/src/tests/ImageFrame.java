package tests;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFrame extends JFrame {
	JLabel pic;
	public ImageFrame(BufferedImage img) {
		super("Image Display");
		
		pic = new JLabel(new ImageIcon(img));
		add(pic);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void setImage(BufferedImage img) {
		pic = new JLabel(new ImageIcon(img));
		System.out.println("Refreshed");
		repaint();
	}
	
	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		try {
			BufferedImage img2 = ImageIO.read(new File("C:/Users/sande/OneDrive/Desktop/Art Files/Wood/thOJBC4J67.jpg"));
			SwingUtilities.invokeLater(()->new ImageFrame(img2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D gImg = (Graphics2D)img.createGraphics();
		gImg.setColor(Color.BLUE);
		gImg.fillOval(0, 0, 50, 50);
		gImg.setBackground(Color.BLACK);
		SwingUtilities.invokeLater(()->new ImageFrame(img));
	}
}
