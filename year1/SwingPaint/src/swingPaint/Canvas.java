package swingPaint;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import drawing.Circle;
import drawing.PaintObject;
import drawing.PaintObjectList;
import drawing.Square;
import drawing.Triangle;
import drawing.PaintObjectList.ObjectTypes;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private PaintObjectList objects = new PaintObjectList();
	private int removalThreshold = 15;
	private PaintObject held;
	
	public Canvas(){ }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); //--DO IT!
		for(PaintObject obj : objects.asList()){
			obj.draw(g);
		}
	}
	public void addObject(int x, int y, ObjectTypes type) {
		PaintObject obj;
		switch(type){
			case CIRCLE:
				obj = new Circle(x, y, 5, Color.black);
				break;
			case SQUARE:
				obj = new Square(x, y, 11, Color.black);
				break;
			case TRIANGLE: 
				obj = new Triangle(x, y, 10, Color.black);
				break;
			default:
				obj = new Circle(x, y, 5, Color.red);
				break;
		}
		objects.add(obj);
		repaint();
	}
	
	public PaintObject findClosest(int x, int y){
		//get closest object within threshold
		PaintObject closest = null;
		double closestDist = removalThreshold;
		for(PaintObject obj : objects.asList())
			if(obj.getDistanceFrom(x, y) < obj.getThreshold() && obj.getDistanceFrom(x, y) < closestDist){
				closest = obj;
				closestDist = obj.getDistanceFrom(x, y);
			}
		return closest;
	}
	
	public void remove(int x, int y){
		PaintObject closest = findClosest(x, y);
		if(closest != null){
			objects.remove(closest);
			repaint();
		}
	}
	
	public void moveObject(int x, int y){
		if(held != null){
			held.setColor(Color.red);
			held.setPosition(x, y);
			repaint();
		}
	}

	public void release(int x, int y) {
		if(held != null)
			held.setColor(Color.black);
		repaint();
	}
	
	public void hold(int x, int y) {
		held = findClosest(x, y);
	}
}
