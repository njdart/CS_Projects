package Nic.Dart.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class GameDictionaryReader {
			
	private static Gson g = new GsonBuilder().setPrettyPrinting().create();

    /**
     * reads the json file for a phrasebook
     * @param dictionaryFile
     * @return Phrasebook instance
     * @throws FileNotFoundException
     */
	public static PhraseBook readDictionary(File dictionaryFile) throws FileNotFoundException {
		BufferedReader dictReader = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFile)));
		return g.fromJson(dictReader, PhraseBook.class);
	}

    /**
     * writes the phrasebook to the sepcified JSON file
     * @param pb phrasebook to write
     * @param dict File of the dictionary
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static void writeDictionary(PhraseBook pb, File dict) throws FileNotFoundException, IOException {
		BufferedWriter dictWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dict)));
		dictWriter.write(g.toJson(pb));
		dictWriter.close();
	}

    /**
     * created the dictionary at the specified location using a new
     * /empty phrasebook
     * @param dict File of the location to make the dictionary
     * @return empty Phrasebook
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static PhraseBook createDictionary(File dict) throws FileNotFoundException, IOException {
        BufferedWriter dictWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dict)));
        PhraseBook pb = new PhraseBook();
        dictWriter.write(g.toJson(pb));
        dictWriter.close();
        return pb;
    }
}
