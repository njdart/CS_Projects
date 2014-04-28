package nic.dart.View.Swing.EventListners;

import nic.dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordViewerListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

        if(!SwingView.getWordViewer().show(SwingView.getPhraseBook()))
            JOptionPane.showMessageDialog(null, "That would be cheating!");
	}

}
