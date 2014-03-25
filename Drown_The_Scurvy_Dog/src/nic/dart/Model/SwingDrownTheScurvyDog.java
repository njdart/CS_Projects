package nic.dart.Model;

import nic.dart.View.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SwingDrownTheScurvyDog {

	private static PhraseBook pb;
	private static View v = new View();
	private static GameModel gm = new GameModel();
	//attempt to get the run location of the jar
	private static File dictionaryFile = new File(SwingDrownTheScurvyDog.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "dictionary.json");
	
	public SwingDrownTheScurvyDog() throws FileNotFoundException {	//I can only catch it so many times!!
		
		//If the file doesnt exist, ask for it, if they say no, ceate it
		try {
			pb = GameDictionaryReader.readDictionary(dictionaryFile);
		} catch (FileNotFoundException e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(false);
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				SwingDrownTheScurvyDog.dictionaryFile = new File(chooser.getSelectedFile().getAbsolutePath());
				pb = GameDictionaryReader.readDictionary(dictionaryFile);
			} else {
				JOptionPane.showMessageDialog(null, "The Dictionary file does not exist, one will be created!");
				pb = GameDictionaryReader.createDictionary(dictionaryFile);
			}
		}
		
		v.setVisible(true);
		//v.setSize(500, 500);
		
		v.pack();
	}

	
	public static boolean save(){
		try{
			GameDictionaryReader.writeDictionary(pb, SwingDrownTheScurvyDog.getDict());
			return true;
		} catch (IOException e){
			return false;
		}
	}
	
	public static boolean load(String dict){
		try {
			pb = GameDictionaryReader.readDictionary(new File(dict));
			SwingDrownTheScurvyDog.dictionaryFile = new File(dict);	//only if we can actually read it!
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public static boolean reload(){
		try{
			pb = GameDictionaryReader.readDictionary(dictionaryFile);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public static Point getViewPosition() {
		return v.getPosition();
	}

	public static boolean isInGame() {
		return gm.isInGame();
	}
	
	public static File getDict(){
		return dictionaryFile;
	}

    public static PhraseBook getPhraseBook(){
        return pb;
    }
}
