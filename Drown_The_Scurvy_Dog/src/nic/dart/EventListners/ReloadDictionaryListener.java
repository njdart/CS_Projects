package nic.dart.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import nic.dart.Model.DrownTheScurvyDog;

public class ReloadDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(DrownTheScurvyDog.reload())
			JOptionPane.showMessageDialog(null, "The Dictionary " + DrownTheScurvyDog.getDict() + " was reloaded successfully!");
		else
			JOptionPane.showMessageDialog(null, "The Dictionary " + DrownTheScurvyDog.getDict() + " could not be reloaded");
	}

}
