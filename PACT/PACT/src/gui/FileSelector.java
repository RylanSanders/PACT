package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelector extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JFileChooser jfc;
	private FileManager fm;

	public FileSelector(FileManager fm) {
		super("File Selector");
		this.fm = fm;
	}

	public void frameInit() {
		super.frameInit();
		jfc = new JFileChooser();
		add(jfc);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		jfc.setFileFilter(filter);
		jfc.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String result = e.getActionCommand();
		switch (result) {
		case "ApproveSelection":
			try {
				File file = jfc.getSelectedFile();
				fm.returnFile(file);
				this.dispose();
			} catch (Exception f) {
				f.printStackTrace();
			}
			break;
		case "CancelSelection":
			this.dispose();
			break;
		default:
			System.out.println("Unimplemented Button");
			break;
		}
	}

}
