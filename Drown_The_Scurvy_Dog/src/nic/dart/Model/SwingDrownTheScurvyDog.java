package nic.dart.Model;

import nic.dart.View.Swing.SwingView;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Deprecated
public class SwingDrownTheScurvyDog {

	private static PhraseBook pb;
	private static SwingView v = new SwingView();
	private static File dictionaryFile;
    private static GameModel model;

	public SwingDrownTheScurvyDog() throws FileNotFoundException {	//I can only catch it so many times!!

		//try to find the dictionary file.
		String runtimeLocation = SwingDrownTheScurvyDog.class.getProtectionDomain().getCodeSource().getLocation().getFile();	//backtrace fo find teh runtime path
		System.out.println(runtimeLocation);
		if(runtimeLocation.contains(".jar")){
			System.out.println("RUNNING FROM JAR!");
			//regex adapted from http://stackoverflow.com/questions/8374742/regex-last-occurrence
			runtimeLocation = runtimeLocation.replaceAll("(\\\\|/)(?:.(?!(\\\\|/)))+$", "");
			System.out.println("New Runtime: " + runtimeLocation);
		}
		dictionaryFile = new File(runtimeLocation + "/dictionary.json");
		System.out.println(dictionaryFile.toString());

		//If the file doesnt exist, ask for it, if they say no, create it
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
				//pb = GameDictionaryReader.createDictionary(dictionaryFile);
			}
		}

		v = new SwingView();
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

	public static boolean isInGame() {
		return false;
	}

	public GameModel getModel(){
		return null;
	}

	public static File getDict(){
		return dictionaryFile;
	}

    public static PhraseBook getPhraseBook(){
        return pb;
    }

    public static SwingView getView(){
    	return v;
    }
}
