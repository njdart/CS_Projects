package Nic.Dart.View.Swing;

import Nic.Dart.Model.GameModel;
import Nic.Dart.View.PirateView;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame implements PirateView{

	private static final long serialVersionUID = 1L;

    private GameModel model;
	private InputPanel ip;
	private MenuBar mb;
    private Gallows g;
	private WordViewer wv;
	
	public SwingView(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}

    @Override
    public void addModel(GameModel model) {
        this.model = model;

        g = new Gallows(model.getLives(), model);
        wv = new WordViewer(model);
        ip = new InputPanel(model, this, g);
        mb = new MenuBar(model, this, wv, ip);

        this.add(mb, BorderLayout.NORTH);
        this.add(ip, BorderLayout.CENTER);
        this.add(g, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    public void setInGame() {
        wv.setVisible(false);
        model.setInGame(true);
        ip.initGame();
    }

    public void abandonGame() {
        ip.reset();
        model.setInGame(false);
    }
}
