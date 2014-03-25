package nic.dart.EventListners;

import nic.dart.Model.SwingDrownTheScurvyDog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingDrownTheScurvyDog.save();
		JOptionPane.showMessageDialog(null, "Successfully saved " + SwingDrownTheScurvyDog.getDict());
	}

}
