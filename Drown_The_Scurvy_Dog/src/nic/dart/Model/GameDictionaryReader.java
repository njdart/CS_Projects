package nic.dart.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

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
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	}
}
