package nic.dart.View.Swing.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import nic.dart.Model.SwingDrownTheScurvyDog;

public class LoadDictionaryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileAbsolute = null;
		String fileRelative = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			fileAbsolute = chooser.getSelectedFile().getAbsolutePath();
			fileRelative = chooser.getSelectedFile().getName();
			if(SwingDrownTheScurvyDog.load(fileAbsolute))
				JOptionPane.showMessageDialog(null, "Successfully loaded the new file!" + fileRelative);
			else JOptionPane.showMessageDialog(null, "Failed to load " + fileRelative);
		}
		
	}

}
