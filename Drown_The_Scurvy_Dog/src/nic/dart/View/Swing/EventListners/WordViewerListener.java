package nic.dart.View.Swing.EventListners;

import nic.dart.Model.SwingDrownTheScurvyDog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordViewerListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingDrownTheScurvyDog.getView().showWordViewer();
	}

}
