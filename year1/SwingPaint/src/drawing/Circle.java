package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends PaintObject{

	public Circle(int x, int y, int size, Color color) {
		super(x, y, size, color);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)Math.round(x - size/2), (int)Math.round(y - size/2), size * 2, size * 2);
	}

	@Override
	public void remove(Graphics g) {
		g.setColor(Color.white);
		g.drawOval((int)Math.round(x - size/2), (int)Math.round(y - size/2), size * 2, size * 2);
	}
	
	public String getType(){
		return "Circle";
	}
	
}
