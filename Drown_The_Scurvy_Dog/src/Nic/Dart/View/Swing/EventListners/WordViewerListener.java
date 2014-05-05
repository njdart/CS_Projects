package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;
import Nic.Dart.View.Swing.WordViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordViewerListener implements ActionListener {

    private GameModel model;
    private WordViewer viewer;
    private SwingView view;

    public WordViewerListener(GameModel model, SwingView view, WordViewer viewer){
        this.model = model;
        this.viewer = viewer;
        this.view = view;
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {

        if(!viewer.showViewer(model))
            JOptionPane.showMessageDialog(view, "That would be cheating!");
	}

}
