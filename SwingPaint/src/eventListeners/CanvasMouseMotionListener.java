package eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import swingPaint.Canvas;
import swingPaint.Paint;

public class CanvasMouseMotionListener implements MouseMotionListener {
	
	private Paint p;
	private Canvas c;
	
	public CanvasMouseMotionListener(Paint p, Canvas c){
		super();
		this.p = p;
		this.c = c;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		c.moveObject(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		p.updateStatsPanel(new int[] {e.getX(), e.getY()}, false, true);

	}

}
