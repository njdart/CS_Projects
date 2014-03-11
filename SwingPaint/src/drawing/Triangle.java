package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends PaintObject{

	public Triangle(int x, int y, int size, Color color) {
		super(x, y, size, color);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		int[] xPoly = new int[] {x,          x - size/2, x + size/2};
		int[] yPoly = new int[] {y - size/2, y + size/2, y + size/2};
		g.fillPolygon(xPoly, yPoly, xPoly.length);
	}

	@Override
	public void remove(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
