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
	
	public WordViewer(Container parent){
		super("Word Viewer");
		this.setVisible(false);
		this.add(mainPanel);
		
		PhraseBook pb = SwingDrownTheScurvyDog.getPhraseBook();
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
		
		words.setText(pb.getWords());
		words.setEditable(false);
		
		phrases.setText(pb.getPhrases());
		phrases.setEditable(false);
		
		this.setResizable(false);
		
		this.pack();
	}
	
	public void dispose(){
		this.dispose();
	}

}
