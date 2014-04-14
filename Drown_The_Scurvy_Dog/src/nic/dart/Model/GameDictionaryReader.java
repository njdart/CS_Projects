package nic.dart.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameDictionaryReader {
			
	private static Gson g = new GsonBuilder().setPrettyPrinting().create();
	
	public static PhraseBook readDictionary(File dictionaryFile) throws FileNotFoundException {
		BufferedReader dictReader = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFile)));
		return g.fromJson(dictReader, PhraseBook.class);
	}
	
	public static void writeDictionary(PhraseBook pb, File dict) throws FileNotFoundException, IOException {
		BufferedWriter dictWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dict)));
		dictWriter.write(g.toJson(pb));
		dictWriter.close();
	}
	
	public static void createDictionary(File dict){
		try {
			new PrintWriter(dict, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	}
}
