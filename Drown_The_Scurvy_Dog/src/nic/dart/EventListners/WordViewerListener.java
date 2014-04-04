package nic.dart.EventListners;

import nic.dart.Model.SwingDrownTheScurvyDog;
import nic.dart.View.Swing.WordViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordViewerListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new WordViewer(SwingDrownTheScurvyDog.getViewPosition());
	}

}
