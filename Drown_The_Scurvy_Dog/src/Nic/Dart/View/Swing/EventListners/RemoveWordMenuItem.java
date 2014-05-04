package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveWordMenuItem implements ActionListener {

    private GameModel model;
    private SwingView view;

    public RemoveWordMenuItem(GameModel model, SwingView view){
        this.model = model;
        this.view = view;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String word = (String) JOptionPane.showInputDialog(view,
                "Type the word to remove",
                "Remove a word",
                JOptionPane.PLAIN_MESSAGE,
                null,null,null);
		
		if(model.getPhraseBook().remove(word))
			JOptionPane.showMessageDialog(view, "Successfully removed \"" + word + "\"");
		else JOptionPane.showMessageDialog(view, "Failed to remove \"" + word + "\"\nIt probably didn't exist");
	}
}
