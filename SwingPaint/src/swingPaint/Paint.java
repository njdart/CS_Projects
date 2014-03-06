package swingPaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import eventListeners.*;

public class Paint extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Color baseObjectColor = Color.black;
	
	private JLabel mouseX,
				   mouseY,
				   mouseClicks,
				   mouseIsOnCanvas;
	
	private Canvas canvas;
	
	private int clicks = 0;
				   
	public Paint(){
		super("Swing Paint");
		setVisible(true);
		
		//JPanels
		canvas = new Canvas();
		JPanel status = new JPanel();
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem quit = new JMenuItem("Quit");
		JMenu draw = new JMenu("Draw");
		//JColorChooser colorPicker = new JColorChooser();
		mouseX = new JLabel("-1");
		mouseY = new JLabel("-1");
		mouseClicks = new JLabel("0");
		mouseIsOnCanvas = new JLabel("No");
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(canvas, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);
		
		menuBar.add(file);
		file.add(help);
		file.add(quit);
		menuBar.add(draw);
		//draw.add(colorPicker);
		
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
		
		
		canvas.addMouseListener(new CanvasMouseListener(this, canvas));
		canvas.addMouseMotionListener(new CanvasMouseMotionListener(this, canvas));
		//colorPicker.addActionListener(new ColorPickerEventListener());
		quit.addActionListener(new CloseMenuItem());
		
		this.setSize(500, 500);
		//pack();
	}
	
	public void updateStatsPanel(int[] pos, boolean clicked, boolean isOnCanvas){
		mouseX.setText(Integer.toString(pos[0]));
		mouseY.setText(Integer.toString(pos[1]));
		if(clicked)
			clicks++;
		mouseClicks.setText(Integer.toString(clicks));
		if(isOnCanvas)
			mouseIsOnCanvas.setText("Yes");
		else mouseIsOnCanvas.setText("No");
	}
	
	public Color getColor(){
		return baseObjectColor;
	}
	
	public void setColor(Color c){
		baseObjectColor = c;
	}
	
	public void drawAt(int x, int y){
		
	}
	
	
}
