package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDictionaryListener implements ActionListener {

    public GameModel model;

    public SaveDictionaryListener(GameModel model){
        this.model = model;
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
        if(model.save())
		    JOptionPane.showMessageDialog(null, "Successfully saved " + model.getDict());
        else JOptionPane.showMessageDialog(null, "Could not save the dictionary file " + model.getDict());
	}

}
