package nic.Dart.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class CliDrownTheScurvyDog implements Observer {

    public CliDrownTheScurvyDog(){}
	
	public CliDrownTheScurvyDog(String dict){
		try{
			GameDictionaryReader.readDictionary(new File(dict));
		} catch(FileNotFoundException e){
			GameDictionaryReader.createDictionary(new File(dict));
		}
        //gm = new GameModel();
	}

    @Override
    public void update(Observable o, Object arg) {

    }
}
