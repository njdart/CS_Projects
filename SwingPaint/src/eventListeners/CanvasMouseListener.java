package eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import drawing.PaintObjectList;
import swingPaint.Canvas;
import swingPaint.Paint;

public class CanvasMouseListener implements MouseListener {
	
	private Canvas canvas;
	private Paint p;
	
	public CanvasMouseListener(Paint p, Canvas canvas){
		super();
		this.p = p;
		this.canvas = canvas;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		p.updateStatsPanel(new int[] {e.getX(), e.getY()}, true, true);
		if(SwingUtilities.isLeftMouseButton(e))
			canvas.addObject(e.getX(), e.getY(), PaintObjectList.ObjectTypes.CIRCLE);
		else
			canvas.remove(e.getX(), e.getY());

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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
