package nic.dart.Model;

import java.util.ArrayList;
import java.util.Random;

public class PhraseBook {

	private ArrayList<String> singleWords = new ArrayList<String>();
	private ArrayList<String> phrases     = new ArrayList<String>();
	private Random r = new Random();
	
	public PhraseBook(){}
	
	public String getRandomWord(){
		return singleWords.get(r.nextInt(singleWords.size()));
	}
	
	public String getRandomPhrase(){
		return phrases.get(r.nextInt(phrases.size()));
	}
	
	public boolean addWord(String word){
		for(String w : singleWords)
			if(w.equals(word))
				return false;
		singleWords.add(word);
		return true;
	}
	
	public boolean addPhrase(String phrase){
		for(String p : phrases)
			if(p.equals(phrase))
				return false;
		phrases.add(phrase);
		return true;
	}
	
	public String toString(){
		String out = "PHRASEBOOK {\n\tSingle Words:[\n";
		for(String w : singleWords)
			out += "\t\t" + w + "\n";
		out += "\t],\n\tPhrases:[\n";
		for(String p : phrases)
			out += "\t\t" + p + "\n";
		out += "\t]\n}";
		return out;
	}
}
