package nic.dart.View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JTextField guess = new JTextField();
	JLabel output = new JLabel();
	JButton guessBtn = new JButton();
	JLabel usedLetters = new JLabel();
	JLabel visableWord = new JLabel();
	
	public InputPanel(){
		super();
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(2,3,10,10));
		this.add(guess);
		this.add(guessBtn);
		this.add(usedLetters);
		this.add(new JLabel());
		this.add(output);
		this.add(new JLabel());
		
		output.setText("******");
		usedLetters.setText("ABCD");
		usedLetters.setAlignmentX(CENTER_ALIGNMENT);
		usedLetters.setAlignmentY(CENTER_ALIGNMENT);
		guessBtn.setText("Guess");
	}
}
