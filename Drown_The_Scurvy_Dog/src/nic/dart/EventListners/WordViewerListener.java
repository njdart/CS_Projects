package nic.dart.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nic.dart.Model.DrownTheScurvyDog;
import nic.dart.View.WordViewer;

public class WordViewerListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new WordViewer(DrownTheScurvyDog.getViewPosition());
	}

}
