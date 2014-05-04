package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReloadDictionaryListener implements ActionListener {

    private GameModel model;
    private SwingView view;

    public ReloadDictionaryListener(GameModel model, SwingView view){
        this.model = model;
        this.view = view;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(model.reload())
			JOptionPane.showMessageDialog(view, "The Dictionary " + model.getDict() + " was reloaded successfully!");
		else
			JOptionPane.showMessageDialog(view, "The Dictionary " + model.getDict() + " could not be reloaded");
	}

}
