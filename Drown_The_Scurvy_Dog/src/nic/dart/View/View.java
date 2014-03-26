package nic.dart.View;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;

public class View extends JFrame{

	private static final long serialVersionUID = 1L;

	private InputPanel ip = new InputPanel();
	private Gallows g = new Gallows();
	private MenuBar mb = new MenuBar();
	
	public View(){
		super("Drown the Scurvy Dog");
		this.add(mb, BorderLayout.NORTH);
		this.add(ip, BorderLayout.CENTER);
		this.add(g, BorderLayout.SOUTH);
		this.setResizable(false);
		
		this.pack();
		this.setVisible(true);
	}

	public Point getPosition() {
		return new Point(this.getX() + this.getWidth()/4, this.getY() + this.getHeight()/4);
	}
}
