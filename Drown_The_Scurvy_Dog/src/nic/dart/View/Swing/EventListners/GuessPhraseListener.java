package nic.dart.View.Swing.EventListners;

import nic.dart.View.Swing.InputPanel;
import nic.dart.View.Swing.SwingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessPhraseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String guess = InputPanel.getGuessText();
		
		if(!SwingView.getModel().getInGame()) {
            SwingView.setInGame();
        }
        if(guess.length() > 0)
                SwingView.getInputPanel().guess(guess);
	}

}
