package eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import swingPaint.ColorPickerWindow;

public class ColorPickerEventListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new ColorPickerWindow();

	}

}
