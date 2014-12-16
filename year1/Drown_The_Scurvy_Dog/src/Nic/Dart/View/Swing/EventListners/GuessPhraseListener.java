package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.Gallows;
import Nic.Dart.View.Swing.InputPanel;
import Nic.Dart.View.Swing.SwingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessPhraseListener implements ActionListener {

    private GameModel model;
    private SwingView view;
    private InputPanel inputPanel;
    private Gallows gallows;

    public GuessPhraseListener(GameModel model, SwingView view, InputPanel inputPanel, Gallows gallows){
        this.model = model;
        this.view = view;
        this.inputPanel = inputPanel;
        this.gallows = gallows;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String guess = inputPanel.getGuessText();

        if(guess != null){

            if(!model.getInGame()) {
                view.setInGame();
                gallows.reset();
            }
            if(guess.length() > 0 && guess.charAt(0) != ' ')
                    inputPanel.guess(guess);
        }
	}
}
