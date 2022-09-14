package tools;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TextFieldOption extends ToolOption {
	private static final long serialVersionUID = 5367382205031137558L;

	private JTextField txt;

	public TextFieldOption() {
		setLayout(null);
		txt = new JTextField();
		txt.setPreferredSize(new Dimension(40, 30));
		this.setPreferredSize(new Dimension(40, 30));
		add(txt);
		txt.setBounds(0, 0, 40, 30);
	}

	@Override
	public void setBehavior(ActionListener f) {
		txt.addActionListener(f);

	}

	public JTextField getTextField() {
		return txt;
	}
}
