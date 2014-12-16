package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWordMenuItem implements ActionListener {

    private GameModel model;
    private SwingView view;

    public AddWordMenuItem(GameModel model, SwingView view){
        this.model = model;
        this.view = view;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String word = (String) JOptionPane.showInputDialog(view,
            "Type a word to add",
            "Add a word",
            JOptionPane.PLAIN_MESSAGE);
	    
		boolean success = model.getPhraseBook().add(word);
		
		if(success)
			JOptionPane.showMessageDialog(view, "Successfully added \"" + word + "\"");
		else JOptionPane.showMessageDialog(view, "Failed to add \"" + word + "\"\nIt probably already existed");
	}
}
