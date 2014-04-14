package nic.dart.View.Swing;

import nic.dart.Model.PhraseBook;
import nic.dart.Model.SwingDrownTheScurvyDog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WordViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextArea words = new JTextArea();
	private JTextArea phrases = new JTextArea();
	private JPanel mainPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
    PhraseBook pb;

	public WordViewer(Container parent){
		super("Word Viewer");
		this.setVisible(false);
		this.add(mainPanel);

		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		topPanel.add(new JLabel("Single Words"));
		topPanel.add(new JLabel("Phrases"));
		topPanel.setLayout(new GridLayout(1,2,10,10));
		
		bottomPanel.add(words);
		bottomPanel.add(phrases);
		bottomPanel.setLayout(new GridLayout(1,2,10,10));

		words.setEditable(false);

		phrases.setEditable(false);
		
		this.setResizable(false);
		
		this.pack();
	}

    @Override
    public void setVisible(boolean state){
        pb = SwingDrownTheScurvyDog.getPhraseBook();
        if(state)
            words.setText(pb.getWords());
        super.setVisible(state);
    }
}
