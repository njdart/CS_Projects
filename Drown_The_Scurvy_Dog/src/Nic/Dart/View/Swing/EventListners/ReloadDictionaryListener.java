package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReloadDictionaryListener implements ActionListener {

    public GameModel model;

    public ReloadDictionaryListener(GameModel model){
        this.model = model;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(model.reload())
			JOptionPane.showMessageDialog(null, "The Dictionary " + model.getDict() + " was reloaded successfully!");
		else
			JOptionPane.showMessageDialog(null, "The Dictionary " + model.getDict() + " could not be reloaded");
	}

}
