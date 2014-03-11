package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends PaintObject {

	public Square(int x, int y, int size, Color color) {
		super(x, y, size, color);
		grabThreshold = 25;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(Math.round(this.x - size/2), Math.round(this.y - size/2), size, size);
	}

	@Override
	public void remove(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)Math.round(this.x - size/2), (int)Math.round(this.y - size/2), size, size);
	}

	@Override
	public String getType() {
		return "Square";
	}

}
