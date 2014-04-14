package nic.dart.View.Swing;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import nic.dart.View.Swing.EventListners.GuessPhraseListener;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static JTextField guess = new JTextField();
	private JLabel output = new JLabel();
	private JButton guessBtn = new JButton();
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
		guessBtn.setText("Guess");
	}
	
	public static String getGuessText(){
		return guess.getText();
	}
}
