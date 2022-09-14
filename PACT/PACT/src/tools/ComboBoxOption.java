package tools;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ComboBoxOption<T> extends ToolOption{
	private static final long serialVersionUID = -4962675104191741847L;
	
	private JComboBox<T> cb;
	public ComboBoxOption() {
		cb = new JComboBox<>();
		setLayout(null);
		this.setPreferredSize(new Dimension(30, 30));
		add(cb);
		cb.setBounds(0, 0, 30, 30);
	}
	
	@Override
	public void setBehavior(ActionListener f) {
		cb.addActionListener(f);
	}
	
	public void addComboBoxItem(T item) {
		cb.addItem(item);
	}
	
	public int getSelectedIndex() {
		return cb.getSelectedIndex();
	}

}
