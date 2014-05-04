package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveWordMenuItem implements ActionListener {

    private GameModel model;

    public RemoveWordMenuItem(GameModel model){
        this.model = model;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String word = (String) JOptionPane.showInputDialog(null,
                "Type the word to remove",
                "Remove a word",
                JOptionPane.PLAIN_MESSAGE,
                null,null,null);
		
		if(model.getPhraseBook().remove(word))
			JOptionPane.showMessageDialog(null, "Successfully removed \"" + word + "\"");
		else JOptionPane.showMessageDialog(null, "Failed to remove \"" + word + "\"\nIt probably didn't exist");
	}
}
