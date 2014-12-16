package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.SwingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nic on 04/05/14.
 */

public class AbandonGameListener implements ActionListener {

    private GameModel model;
    private SwingView view;


    public AbandonGameListener(GameModel model, SwingView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.setInGame(false);
        view.abandonGame();
    }
}
