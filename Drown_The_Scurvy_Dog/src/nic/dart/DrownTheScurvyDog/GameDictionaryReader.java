package nic.dart.DrownTheScurvyDog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;

public class GameDictionaryReader {
	
	private Gson g = new Gson();
	private String dict = "./dictionary.json";
	
	public GameDictionaryReader(){}
	
	public GameDictionaryReader(String dict){
		this.dict = dict;
	}
	
	public PhraseBook readDictionary() throws FileNotFoundException{
		//BufferedWriter s = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("students.json"))
		
		try(BufferedReader dictReader = new BufferedReader(new InputStreamReader(new FileInputStream(dict)))){
			return g.fromJson(dictReader, PhraseBook.class);
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
}
