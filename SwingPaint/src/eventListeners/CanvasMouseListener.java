package eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import drawing.PaintObjectList;
import swingPaint.Canvas;
import swingPaint.Paint;

public class CanvasMouseListener implements MouseListener {
	
	private Canvas c;
	private Paint p;
	
	public CanvasMouseListener(Paint p, Canvas canvas){
		this.p = p;
		this.c = canvas;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		p.updateStatsPanel(new int[] {e.getX(), e.getY()}, true, true);
		if(SwingUtilities.isLeftMouseButton(e))
			c.addObject(e.getX(), e.getY(), PaintObjectList.ObjectTypes.CIRCLE);
		else
			c.remove(e.getX(), e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		p.updateStatsPanel(new int[] {-1,-1}, false, true);

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		p.updateStatsPanel(new int[] {-1,-1}, false, false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
			c.hold(e.getX(), e.getY());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
			c.release(e.getX(), e.getY());
	}

}
