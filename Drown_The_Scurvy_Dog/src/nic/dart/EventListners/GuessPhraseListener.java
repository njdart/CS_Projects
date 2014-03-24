package nic.dart.EventListners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sun.awt.OSInfo.WindowsVersion;
import sun.java2d.Disposer;
import nic.dart.Model.GameModel;
import nic.dart.View.InputPanel;
import nic.dart.View.WordViewer;

public class GuessPhraseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String guess = InputPanel.getGuessText();
		
		if(guess.length() > 0){
			GameModel.setInGame();
		}
	}

}
