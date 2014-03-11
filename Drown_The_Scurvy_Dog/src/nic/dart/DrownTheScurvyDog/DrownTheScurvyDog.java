package nic.dart.DrownTheScurvyDog;

import java.io.FileNotFoundException;

public class DrownTheScurvyDog {

	private static GameDictionaryReader dict = new GameDictionaryReader();
	private static PhraseBook pb;
	
	public static void main(String[] args) throws FileNotFoundException{
		pb = dict.readDictionary();
		System.out.println(pb);
	}
}
