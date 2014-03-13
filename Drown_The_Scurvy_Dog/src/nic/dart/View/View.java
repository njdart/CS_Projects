package nic.dart.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class View extends JFrame{

	private static final long serialVersionUID = 1L;

	private InputPanel ip = new InputPanel();
	private Gallows g = new Gallows();
	
	public View(){
		super("Drown the Scurvy Dog");
		this.add(ip, BorderLayout.NORTH);
		this.add(g, BorderLayout.SOUTH);
		this.setResizable(false);
	}
}
