package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDictionaryListener implements ActionListener {

    private GameModel model;
    private SwingView view;

    public SaveDictionaryListener(GameModel model, SwingView view){
        this.model = model;
        this.view = view;
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
        if(model.save())
		    JOptionPane.showMessageDialog(view, "Successfully saved " + model.getDict());
        else JOptionPane.showMessageDialog(view, "Could not save the dictionary file " + model.getDict());
	}

}
