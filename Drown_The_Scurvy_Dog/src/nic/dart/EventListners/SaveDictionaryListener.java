package nic.dart.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import nic.dart.Model.DrownTheScurvyDog;

public class SaveDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DrownTheScurvyDog.save();
		JOptionPane.showMessageDialog(null, "Successfully saved " + DrownTheScurvyDog.getDict());
	}

}
