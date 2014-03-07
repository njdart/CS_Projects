package swingPaint;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import drawing.Circle;
import drawing.PaintObject;
import drawing.PaintObjectList;
import drawing.Square;
import drawing.PaintObjectList.ObjectTypes;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private PaintObjectList objects = new PaintObjectList();
	private int removalThreshold = 15;
	
	public Canvas(){ }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); //--DO IT!
		for(PaintObject obj : objects.asList()){
			//System.out.println("Painting " + obj.toString());
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
				obj = new Square(x, y, 5, Color.black);
				break;
			default:
				obj = new Circle(x, y, 5, Color.red);
				break;
		}
		objects.add(obj);
		repaint();
	}
	private PaintObject findClosest(int x, int y){
		//get closest object within threshold
			PaintObject closest = null;
			double closestDist = removalThreshold;
			for(PaintObject obj : objects.asList())
				if(obj.getDistanceFrom(x, y) < removalThreshold && obj.getDistanceFrom(x, y) < closestDist){
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
		PaintObject o = findClosest(x, y);
		if(o != null){
			o.setColor(Color.red);
			o.setPosition(x, y);
			repaint();
		}
	}

	public void releaseObject(int x, int y) {
		PaintObject o = findClosest(x, y);
		if(o != null)
			o.setColor(Color.black);
		repaint();
	}
}
