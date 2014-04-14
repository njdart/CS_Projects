package nic.dart.View.Swing.EventListners;

import nic.dart.Model.SwingDrownTheScurvyDog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReloadDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(SwingDrownTheScurvyDog.reload())
			JOptionPane.showMessageDialog(null, "The Dictionary " + SwingDrownTheScurvyDog.getDict() + " was reloaded successfully!");
		else
			JOptionPane.showMessageDialog(null, "The Dictionary " + SwingDrownTheScurvyDog.getDict() + " could not be reloaded");
	}

}
