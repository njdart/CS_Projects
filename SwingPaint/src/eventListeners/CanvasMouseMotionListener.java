package eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import swingPaint.Paint;

public class CanvasMouseMotionListener implements MouseMotionListener {
	
	private Paint p;
	
	public CanvasMouseMotionListener(Paint p){
		super();
		this.p = p;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		p.updateStatsPanel(new int[] {e.getX(), e.getY()}, false, true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		p.updateStatsPanel(new int[] {e.getX(), e.getY()}, false, true);

	}

}
