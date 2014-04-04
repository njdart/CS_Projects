package nic.dart.Model;

import java.util.ArrayList;
import java.util.Random;

public class PhraseBook {

	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> phrases = new ArrayList<String>();
	private Random r = new Random();
	
	public PhraseBook(){}
	
	public String getRandomWord(){
		return words.get(r.nextInt(words.size()));
	}
	
	public String getRandomPhrase(){
		return phrases.get(r.nextInt(phrases.size()));
	}
	
	public boolean addWord(String word){
		for(String w : words)
			if(w.equals(word))
				return false;
		words.add(word);
		return true;
	}
	
	public boolean addPhrase(String phrase){
		for(String p : phrases)
			if(p.equals(phrase))
				return false;
		phrases.add(phrase);
		return true;
	}

	public boolean add(String s) {
		if(s == null || s.equals("")) return false;
		if(s.split("\\s+").length > 1){
			for(String p: phrases)
				if(s.toLowerCase().equals(p.toLowerCase()))
					return false;
			this.addPhrase(s);
			return true;
		} else {
			for(String w : words)
				if(s.toLowerCase().equals(w.toLowerCase()))
					return false;
			this.addWord(s);
			return true;
		}
	}
	
	public boolean remove(String s){
		if(s == null || s.equals("")) return false;
		if(s.split("\\s+").length > 1){
			for(String p: phrases){
				if(s.toLowerCase().equals(p.toLowerCase())){
					phrases.remove(p);
					return true;
				}
			}
			return false;
		} else {
			for(String w: words){
				if(s.toLowerCase().equals(w.toLowerCase())){
					words.remove(w);
					return true;
				}
			}
			return false;
		}
	}

	public String getWords() {
		String returnWords = "";
		for(String w: words)
			returnWords+=w+"\n";
		return returnWords.trim();
	}

    public ArrayList<String> getWordsAsList(){
        return words;
    }
	
	public String getPhrases(){
		String returnPhrases = "";
		for(String p: phrases)
			returnPhrases+=p+"\n";
		return returnPhrases.trim();
	}

    public ArrayList<String> getPhrasesAsList(){
        return phrases;
    }

	
	public String toString(){
		String out = "PHRASEBOOK {\n\tSingle Words:[\n";
		for(String w : words)
			out += "\t\t" + w + "\n";
		out += "\t],\n\tPhrases:[\n";
		for(String p : phrases)
			out += "\t\t" + p + "\n";
		out += "\t]\n}";
		return out;
	}

    public ArrayList<String> getAllItems() {
        ArrayList<String> toReturn = new ArrayList<String>();
        toReturn.addAll(words);
        toReturn.addAll(phrases);
        return toReturn;
    }
}
