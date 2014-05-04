package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadDictionaryListener implements ActionListener {

    private GameModel model;

    public LoadDictionaryListener(GameModel model){
        this.model = model;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileAbsolute = null;
		String fileRelative = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			fileAbsolute = chooser.getSelectedFile().getAbsolutePath();
			fileRelative = chooser.getSelectedFile().getName();
			if(model.load(fileAbsolute))
				JOptionPane.showMessageDialog(null, "Successfully loaded the new file!" + fileRelative);
			else JOptionPane.showMessageDialog(null, "Failed to load " + fileRelative);
		}
		
	}

}
