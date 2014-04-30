package nic.Dart.View.Swing.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import nic.Dart.View.Swing.SwingView;

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
			if(SwingView.getModel().load(fileAbsolute))
				JOptionPane.showMessageDialog(null, "Successfully loaded the new file!" + fileRelative);
			else JOptionPane.showMessageDialog(null, "Failed to load " + fileRelative);
		}
		
	}

}
