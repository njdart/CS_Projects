package nic.dart.Model;

import java.io.File;
import java.io.FileNotFoundException;

public class CliDrownTheScurvyDog implements DrownTheScurvyDog {

	GameModel gm = new GameModel();
	PhraseBook pb;
	
	
	public CliDrownTheScurvyDog(String dict){
		try{
			pb = GameDictionaryReader.readDictionary(new File(dict));
		} catch(FileNotFoundException e){
			pb = GameDictionaryReader.createDictionary(new File(dict));
		}
	}
	
	@Override
	public GameModel getModel() {
		return gm;
	}

}
