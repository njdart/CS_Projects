package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nic on 04/05/14.
 */
public class RevealAnswerListener implements ActionListener {

    private SwingView view;
    private GameModel model;

    public RevealAnswerListener(GameModel model, SwingView view) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(view, "The word was " + model.getVisible());
        view.abandonGame();
    }
}
