package nic.Dart.View.Swing;

import nic.Dart.Model.GameModel;
import nic.Dart.Model.PhraseBook;
import nic.Dart.View.PirateView;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame implements PirateView{

	private static final long serialVersionUID = 1L;

	private static InputPanel ip = new InputPanel();
	private Gallows g = new Gallows();
	private MenuBar mb = new MenuBar();
    private static GameModel model;
	private static WordViewer wv;
	
	public SwingView(){
		this.add(mb, BorderLayout.NORTH);
		this.add(ip, BorderLayout.CENTER);
		this.add(g, BorderLayout.SOUTH);
		this.setResizable(false);
        this.setPreferredSize(new Dimension(600, 600));

        this.pack();
		this.setVisible(true);
	}

    public static GameModel getModel() {
        return model;
    }

    public static InputPanel getInputPanel() {
        return ip;
    }

    @Override
    public void addModel(GameModel model) {
        this.model = model;
        wv = new WordViewer(model.getPhraseBook());
    }

    public static WordViewer getWordViewer(){
        return wv;
    }

    public static PhraseBook getPhraseBook() {return model.getPhraseBook(); }

    public static void setInGame() {
        wv.setVisible(false);
        model.setInGame(true);
        ip.initGame();
    }
}
