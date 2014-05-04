package Nic.Dart.View.Swing;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.Swing.EventListners.*;

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
    private JMenuItem newPb = new JMenuItem("New Phrasebook");
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
    private JMenuItem hint = new JMenuItem("Hint");

    private GameModel model;
    private SwingView view;
    private WordViewer wordViewer;
	private InputPanel inputPanel;

	public MenuBar(GameModel model, SwingView view, WordViewer wordViewer, InputPanel inputPanel){
		super();
        this.model = model;
        this.view = view;
        this.wordViewer = wordViewer;
        this.inputPanel = inputPanel;

		this.add(file);
		this.add(edit);
        this.add(game);

        file.add(newPb);
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
        game.add(hint);

		newPb.addActionListener(new NewPhraseBookListener(model, view));
		addWord.addActionListener(new AddWordMenuItem(model, view));
		deleteWord.addActionListener(new RemoveWordMenuItem(model, view));
		showWords.addActionListener(new WordViewerListener(model, view, wordViewer));
		save.addActionListener(new SaveDictionaryListener(model, view));
		reload.addActionListener(new ReloadDictionaryListener(model, view));
		load.addActionListener(new LoadDictionaryListener(model, view));
        abandonGame.addActionListener(new AbandonGameListener(model, view));
        revealAnswer.addActionListener(new RevealAnswerListener(model, view));
        hint.addActionListener(new HintListener(model, view, inputPanel));
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
	}	
}
