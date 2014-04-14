package nic.dart.View.Swing.EventListners;

import nic.dart.Model.GameModel;
import nic.dart.Model.SwingDrownTheScurvyDog;
import nic.dart.View.Swing.InputPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessPhraseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String guess = InputPanel.getGuessText();
		
		if(guess.length() > 0){
			GameModel.setInGame();
			SwingDrownTheScurvyDog.getView().hideWordViewer();
		}
	}

}
