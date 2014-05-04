package Nic.Dart.View.Swing;

import Nic.Dart.Model.GameModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gallows extends JPanel {

	private static final long serialVersionUID = 1L;

    private BufferedImage background;
    private BufferedImage pirate;

    private int pirateX, pirateY, lives;
    private final int pirateStartingX = 490,
                      pirateStartingY = 155,
                      plankLength = 240;
    private GameModel model;

	public Gallows(int lives, GameModel model){
		super();
        this.model = model;
        this.lives = lives;

        File backgroundImageLoc = new File("res/pirateShip.png");
        File pirateImageLoc = new File("res/pirate.png");

        try {
            background = ImageIO.read(Gallows.class.getResourceAsStream("/res/pirateShip.png"));
            pirate = ImageIO.read(Gallows.class.getResourceAsStream("/res/pirate.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        reset();
	}

    public void update(int failedGuesses){
        if(failedGuesses == lives)
            doFailAnimation();
        else {
            pirateX = pirateStartingX - (failedGuesses * (plankLength / lives));
            pirateY = pirateStartingY; //this shouldnt need to change
            repaint();
        }
    }

    private void doFailAnimation() {

    }

    public void reset(){
        pirateX = pirateStartingX;
        pirateY = pirateStartingY;

        repaint();
    }

    public Dimension getPreferredSize() {
        return background == null ? super.getPreferredSize() : new Dimension(background.getWidth(), background.getHeight());
    }


    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(background, 0, 0, this);
        g.drawImage(pirate, pirateX, pirateY, this);
    }

}
