package nic.dart.Model;

import java.awt.Point;
import java.io.FileNotFoundException;

import nic.dart.View.*;

public class DrownTheScurvyDog {

	private static GameDictionaryReader dict = new GameDictionaryReader();
	private static PhraseBook pb;
	private static View v = new View();
	private static GameModel gm = new GameModel();
	
	public static void main(String[] args) throws FileNotFoundException{
		pb = dict.readDictionary();
		System.out.println(pb);
		
		v.setVisible(true);
		v.setSize(500, 500);
		
		//v.pack();
	}
	
	public static PhraseBook getPhraseBook(){
		return pb;
	}

	public static Point getViewPosition() {
		return v.getPosition();
	}

	public static boolean isInGame() {
		return gm.isInGame();
	}
}
