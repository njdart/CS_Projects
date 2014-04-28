package nic.dart.View.Swing;

import nic.dart.View.Swing.EventListners.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	//Menu headders
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
    private JMenu game = new JMenu("Game");
	//file menu items
	private JMenuItem save = new JMenuItem("Save Phrasebook");
	private JMenuItem load = new JMenuItem("Load Phrasebook");
	private JMenuItem reload = new JMenuItem("Reload Phrasebook");
	private JMenuItem exit = new JMenuItem("Exit");
	//edit menu items
	private JMenuItem addWord = new JMenuItem("Add a Phrases");
	private JMenuItem deleteWord = new JMenuItem("Delete a Phrases");
	private JMenuItem showWords = new JMenuItem("Show Phrases");
    //game menu items
    private JMenuItem abandonGame = new JMenuItem("Abandon");
    private JMenuItem revealAnswer = new JMenuItem("Reveal Answer");
	
	public MenuBar(){
		super();
		this.add(file);
		this.add(edit);
        this.add(game);
		
		file.add(save);
		file.add(load);
		file.add(reload);
		file.add(new JSeparator());
		file.add(exit);
		edit.add(addWord);
		edit.add(deleteWord);
		edit.add(showWords);
        game.add(abandonGame);
        game.add(revealAnswer);
		
		addWord.addActionListener(new AddWordMenuItem());
		deleteWord.addActionListener(new RemoveWordMenuItem());
		showWords.addActionListener(new WordViewerListener());
		save.addActionListener(new SaveDictionaryListener());
		reload.addActionListener(new ReloadDictionaryListener());
		load.addActionListener(new LoadDictionaryListener());
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
	}	
}
