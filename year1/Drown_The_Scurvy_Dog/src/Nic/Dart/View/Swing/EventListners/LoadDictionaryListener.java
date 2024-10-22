package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadDictionaryListener implements ActionListener {

    private GameModel model;
    private SwingView view;

    public LoadDictionaryListener(GameModel model, SwingView view){
        this.model = model;
        this.view = view;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileAbsolute = null;
		String fileRelative = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		if(chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION){
			fileAbsolute = chooser.getSelectedFile().getAbsolutePath();
			fileRelative = chooser.getSelectedFile().getName();
			if(model.load(fileAbsolute))
				JOptionPane.showMessageDialog(view, "Successfully loaded the new file!" + fileRelative);
			else JOptionPane.showMessageDialog(view, "Failed to load " + fileRelative);
		}
		
	}

}
