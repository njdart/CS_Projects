package nic.Dart.View.Swing.EventListners;

import nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReloadDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(SwingView.getModel().reload())
			JOptionPane.showMessageDialog(null, "The Dictionary " + SwingView.getModel().getDict() + " was reloaded successfully!");
		else
			JOptionPane.showMessageDialog(null, "The Dictionary " + SwingView.getModel().getDict() + " could not be reloaded");
	}

}
