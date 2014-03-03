package swingPaint;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		JLabel mouseX = new JLabel("-1");
		JLabel mouseY = new JLabel("-1");
		JLabel mouseClicks = new JLabel("0");
		JLabel mouseIsOnCanvas = new JLabel("No");
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(canvas, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);
		
		menuBar.add(file);
		file.add(help);
		file.add(quit);
		status.setLayout(new GridLayout(3,3));
		status.add(new JLabel("Mouse Position (X,Y)"));
		status.add(mouseX);
		status.add(mouseY);
		status.add(new JLabel("Mouse Clicks"));
		status.add(mouseClicks);
		status.add(new JLabel());
		status.add(new JLabel("Is on Canvas"));
		status.add(mouseIsOnCanvas);
		status.add(new JLabel());
		
		
		
		quit.addActionListener(new CloseMenuItem());
		
		this.setSize(500, 500);
		//pack();
	}
}
