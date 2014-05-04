package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWordMenuItem implements ActionListener {

    private GameModel model;

    public AddWordMenuItem(GameModel model){
        this.model = model;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String word = (String) JOptionPane.showInputDialog(null,
            "Type a word to add",
            "Add a word",
            JOptionPane.PLAIN_MESSAGE,
            null,null,null);
	    
		boolean success = model.getPhraseBook().add(word);
		
		if(success)
			JOptionPane.showMessageDialog(null, "Successfully added \"" + word + "\"");
		else JOptionPane.showMessageDialog(null, "Failed to add \"" + word + "\"\nIt probebly already existed");
	}
}
