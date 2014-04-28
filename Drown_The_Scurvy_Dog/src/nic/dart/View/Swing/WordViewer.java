package nic.dart.View.Swing;

import nic.dart.Model.PhraseBook;

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
    PhraseBook phraseBook;

    private boolean isInGame = false;

	public WordViewer(PhraseBook phraseBook){
		super("Word Viewer");

        this.phraseBook = phraseBook;

		setVisible(false);
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
		
		updateView();
	}

    public boolean show(PhraseBook phraseBook){
        if(!SwingView.getModel().isInGame()) {
            this.phraseBook = phraseBook;
            updateView();
            setVisible(true);
            return true;
        } else return false;
    }

    private void updateView(){
        String words = "";
        String phrases = "";

        for(String word: phraseBook.getWordsAsList())
            words += word + "\n";
        for(String phrase: phraseBook.getPhrasesAsList())
            phrases += phrase + "\n";

        this.words.setText(words.trim());
        this.phrases.setText(phrases.trim());
        this.pack();
    }

    public boolean isInGame(){
        return isInGame;
    }
}
