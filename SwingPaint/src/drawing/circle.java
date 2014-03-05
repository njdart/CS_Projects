package drawing;

import java.awt.Color;

public class circle extends PaintObject{

	public circle(int x, int y, int size, Color color) {
		super(x, y, size, color);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Pos: (" + super.x + "," + super.y + "), size " + super.size + " color: " + super.color.toString();
	}
	
	
}
