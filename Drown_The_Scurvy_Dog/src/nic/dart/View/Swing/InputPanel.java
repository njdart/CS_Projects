package nic.dart.View.Swing;

import nic.dart.Model.GameModel;
import nic.dart.View.Swing.EventListners.GuessPhraseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static JTextField guess = new JTextField();
	private JLabel output = new JLabel();
	private JButton guessBtn = new JButton();   //starts off saying "Begin"
	private JLabel usedLetters = new JLabel();

	public InputPanel(){
		super();
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(2,2,10,10));
		this.add(guess);
		this.add(guessBtn);
		this.add(usedLetters);
		this.add(output);
		
		guessBtn.addActionListener(new GuessPhraseListener());
		guess.addActionListener(new GuessPhraseListener());

		output.setText("y*a**");
		usedLetters.setText("ABCDY");
		usedLetters.setAlignmentX(CENTER_ALIGNMENT);
		usedLetters.setAlignmentY(CENTER_ALIGNMENT);

        setInGame(false);
	}
	
	public static String getGuessText(){
		return guess.getText();
	}

    public void setInGame(boolean state){
        if(state) {
            guess.setEditable(true);
            guessBtn.setText("Guess");
        } else {
            guess.setEditable(false);
            guessBtn.setText("Begin");
        }
    }

    public void guess(String guess){
        this.guess.setText("");
        GameModel model = SwingView.getModel();
        boolean result;
        if(guess.length() < 1 || guess == null)
            return;
        if(guess.length() == 1)
            result = model.tryThis(guess.toCharArray()[0]);
        else result = model.tryWord(guess);

        output.setText(model.getHidden());
        System.out.println(result);
        if(result){
            //success
        } else {
            usedLetters.setText(usedLetters.getText() + "," + guess);
        }
    }

    public void initGame() {
        usedLetters.setText("");
        SwingView.getModel().setInGame(true);
        output.setText(SwingView.getModel().getHidden());
        guess.setEditable(true);
    }
}
