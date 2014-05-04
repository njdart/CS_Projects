package Nic.Dart.View.Swing;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.EventListners.GuessPhraseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static JTextField guess = new JTextField();
	private JLabel output = new JLabel();
	private JButton guessBtn = new JButton();   //starts off saying "Begin"
	private JLabel usedLetters = new JLabel();
    private GameModel model;
    private SwingView view;
    private Gallows gallows;

	public InputPanel(GameModel model, SwingView view, Gallows gallows){
		super();
        this.model = model;
        this.view = view;
        this.gallows = gallows;
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(2,2,10,10));
		this.add(guess);
		this.add(guessBtn);
		this.add(usedLetters);
		this.add(output);

        GuessPhraseListener gpl = new GuessPhraseListener(model, view, this, gallows);
		
		guessBtn.addActionListener(gpl);
		guess.addActionListener(gpl);

		output.setText("y*a**");
		usedLetters.setText("ABCDY");
        reset();
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
        boolean result;
        if(guess.length() < 1 || guess == null)
            return;
        if(guess.length() == 1)
            result = model.tryThis(guess.charAt(0));
        else result = model.tryWord(guess);

        guessBtn.setText(model.guessLeft() + " Lives Left");

        output.setText(model.getHidden());
        usedLetters.setText(model.getLetters());
        String message = "";

        if(!result)
            gallows.update(model.getFails());

        if(model.isGameOver()){
            JOptionPane.showMessageDialog(this, "You ran out of lives! The word was " + model.getVisible() + ".\nBetter luck next time!");
            model.setInGame(false);
            reset();
            setInGame(false);
        }
        else if(model.isCompletedWord()){
            JOptionPane.showMessageDialog(this, "Congratulations! You won in " + model.getGuesses() + " guesses! (" + model.getFails() + " incorrect guesses)");
            model.setInGame(false);
            reset();
            setInGame(false);
        }
    }

    public void initGame() {
        guessBtn.setText(model.guessLeft() + " Lives Left");
        usedLetters.setText("");
        output.setText(model.getHidden());
        guess.setEditable(true);
    }

    public void reset() {
        guessBtn.setText("Begin");
        guess.setEditable(false);
        output.setText("");
        usedLetters.setText("");


    }
}
