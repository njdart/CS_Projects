package nic.dart.Model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import nic.dart.View.*;

public class DrownTheScurvyDog {

	private static PhraseBook pb;
	private static View v = new View();
	private static GameModel gm = new GameModel();
	//attempt to get the run location of the jar
	private static File dictionaryFile = new File(DrownTheScurvyDog.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "dictionary.json");
	
	public static void main(String[] args) throws FileNotFoundException {	//I can only catch it so many times!!
		try {
			pb = GameDictionaryReader.readDictionary(dictionaryFile);
		} catch (FileNotFoundException e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(false);
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				DrownTheScurvyDog.dictionaryFile = new File(chooser.getSelectedFile().getAbsolutePath());
				pb = GameDictionaryReader.readDictionary(dictionaryFile);
			} else {
				JOptionPane.showMessageDialog(null, "The Dictionary file does not exist, one will be created!");
				pb = GameDictionaryReader.createDictionary(dictionaryFile);
			}
		}
		
		v.setVisible(true);
		v.setSize(500, 500);
		
		//v.pack();
	}
	
	public static PhraseBook getPhraseBook(){
		return pb;
	}
	
	public static boolean save(){
		try{
			GameDictionaryReader.writeDictionary(pb, DrownTheScurvyDog.getDict());
			return true;
		} catch (IOException e){
			return false;
		}
	}
	
	public static boolean load(String dict){
		try {
			pb = GameDictionaryReader.readDictionary(new File(dict));
			DrownTheScurvyDog.dictionaryFile = new File(dict);	//only if we can actually read it!
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
}
