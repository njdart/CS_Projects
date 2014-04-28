package nic.dart.View.Swing.EventListners;

import nic.dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveWordMenuItem implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String word = (String) JOptionPane.showInputDialog(null,
                "Type the word to remove",
                "Remove a word",
                JOptionPane.PLAIN_MESSAGE,
                null,null,null);

		boolean success = SwingView.getPhraseBook().remove(word);
		
		if(success)
			JOptionPane.showMessageDialog(null, "Successfully removed \"" + word + "\"");
		else JOptionPane.showMessageDialog(null, "Failed to remove \"" + word + "\"\nIt probebly didn't exist");
	}
}
