package nic.dart.View.Swing.EventListners;

import nic.dart.Model.SwingDrownTheScurvyDog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWordMenuItem implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String word = (String) JOptionPane.showInputDialog(null,
            "Type a word to add",
            "Add a word",
            JOptionPane.PLAIN_MESSAGE,
            null,null,null);
	    
		boolean success = SwingDrownTheScurvyDog.getPhraseBook().add(word);
		
		if(success)
			JOptionPane.showMessageDialog(null, "Successfully added \"" + word + "\"");
		else JOptionPane.showMessageDialog(null, "Failed to add \"" + word + "\"\nIt probebly already existed");
	}
}
