package swingPaint;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



//custom event listeners
import eventListeners.*;

public class Paint extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Paint(){
		super("Swing Paint");
		setVisible(true);
		
		//JPanels
		JPanel canvas = new JPanel();
		JPanel status = new JPanel();
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem quit = new JMenuItem("Quit");
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(canvas, BorderLayout.CENTER);
		this.add(canvas, BorderLayout.SOUTH);
		
		menuBar.add(file);
		file.add(help);
		file.add(quit);
		
		quit.addActionListener(new CloseMenuItem());
		
		this.setSize(500, 500);
		//pack();
	}
}
