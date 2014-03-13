package nic.dart.Model;

import java.io.FileNotFoundException;
import nic.dart.View.*;

public class DrownTheScurvyDog {

	private static GameDictionaryReader dict = new GameDictionaryReader();
	private static PhraseBook pb;
	
	public static void main(String[] args) throws FileNotFoundException{
		pb = dict.readDictionary();
		System.out.println(pb);
		
		View v = new View();
		v.setVisible(true);
		v.setSize(500, 500);
		
		//v.pack();
		
	}
}
