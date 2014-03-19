package nic.dart.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import nic.dart.Model.DrownTheScurvyDog;

public class RemoveWordMenuItem implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String word = (String) JOptionPane.showInputDialog(null,
                "Type the word to remove",
                "Remove a word",
                JOptionPane.PLAIN_MESSAGE,
                null,null,null);

		boolean success = DrownTheScurvyDog.getPhraseBook().remove(word);
		
		if(success)
			JOptionPane.showMessageDialog(null, "Successfully removed \"" + word + "\"");
		else JOptionPane.showMessageDialog(null, "Failed to remove \"" + word + "\"\nIt probebly didn't exist");
	}
}
