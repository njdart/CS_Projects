package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.InputPanel;
import Nic.Dart.View.Swing.SwingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nic on 04/05/14.
 */

public class HintListener implements ActionListener {

    private GameModel model;
    private SwingView view;
    private InputPanel inputPanel;

    public HintListener(GameModel model, SwingView view, InputPanel inputPanel) {
        this.model = model;
        this.view = view;
        this.inputPanel = inputPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Character hint = model.giveHint();
        if(hint != null)
            inputPanel.guess(hint + "");
        else JOptionPane.showMessageDialog(view, "No hint available");
    }
}
