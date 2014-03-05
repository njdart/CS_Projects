package swingPaint;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eventListeners.ColorPickerCancel;

public class ColorPickerWindow extends JFrame{
	

	private static final long serialVersionUID = 1L;

	public ColorPickerWindow(){
		super();
		JColorChooser jc = new JColorChooser();
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		
		north.add(jc);
		south.add(ok);
		south.add(cancel);
		
		cancel.addActionListener(new ColorPickerCancel());
		pack();
		
		
		setVisible(true);
	}

}
