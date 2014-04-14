package nic.dart.View.Swing;

import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nic.dart.Model.SwingDrownTheScurvyDog;

public class View extends JFrame{

	private static final long serialVersionUID = 1L;

	private InputPanel ip = new InputPanel();
	private Gallows g = new Gallows();
	private MenuBar mb = new MenuBar();
	private WordViewer wv = new WordViewer(this);
	
	public View(){
		super("Drown the Scurvy Dog");
		this.add(mb, BorderLayout.NORTH);
		this.add(ip, BorderLayout.CENTER);
		this.add(g, BorderLayout.SOUTH);
		this.setResizable(false);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void hideWordViewer(){
		wv.setVisible(false);
	}
	
	public void showWordViewer(){
		if(!SwingDrownTheScurvyDog.isInGame())
			wv.setVisible(true);
		else
			JOptionPane.showMessageDialog(this, "That would be cheating!");
	}
}
