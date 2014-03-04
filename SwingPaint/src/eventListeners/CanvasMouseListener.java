package eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import swingPaint.Paint;

public class CanvasMouseListener implements MouseListener {

	private Paint p;
	public CanvasMouseListener(Paint p){
		super();
		this.p = p;		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		p.updateStatsPanel(new int[] {e.getX(), e.getY()}, true, true);

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
