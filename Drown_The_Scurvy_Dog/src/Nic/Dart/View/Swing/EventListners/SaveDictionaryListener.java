package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
        if(SwingView.getModel().save())
		    JOptionPane.showMessageDialog(null, "Successfully saved " + SwingView.getModel().getDict());
        else JOptionPane.showMessageDialog(null, "Could not save the dictionary file " + SwingView.getModel().getDict());
	}

}
