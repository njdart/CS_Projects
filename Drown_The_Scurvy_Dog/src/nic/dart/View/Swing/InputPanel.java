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
            result = model.tryThis(guess.charAt(0));
        else result = model.tryWord(guess);

        guessBtn.setText(model.guessLeft() + " Lives Left");

        output.setText(model.getHidden());
        System.out.println(result);
        usedLetters.setText(model.getLetters());
        String message = "";

        if(model.isGameOver()){
            JOptionPane.showMessageDialog(this, "You ran out of lives! The word was " + model.getVisible() + ".\nBetter luck next time!");
            model.setInGame(false);
            setInGame(false);
        }
        else if(model.isCompletedWord()){
            JOptionPane.showMessageDialog(this, "Congratulations! You won in " + model.getGuesses() + " guesses! (" + model.getFails() + " incorrect guesses)");
            model.setInGame(false);
            setInGame(false);
        }
    }

    public void initGame() {
        guessBtn.setText(SwingView.getModel().guessLeft() + " Lives Left");
        usedLetters.setText("");
        output.setText("");
        output.setText(SwingView.getModel().getHidden());
        guess.setEditable(true);
    }
}
