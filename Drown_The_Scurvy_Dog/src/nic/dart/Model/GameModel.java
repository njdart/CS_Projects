package nic.dart.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameModel extends Observable implements GameModelInterface{
	
	private String word;
	private int guesses;
	private int lives;
	private ArrayList<Character> guessedChars = new ArrayList<Character>();
	private static boolean inGame = false;
	private String visible;
    private PhraseBook phraseBook;
    private File dictionaryFile;

    /**
     * Assumes the dictionary file is located in the "root".
     * either in out/production/drown_the_scurcy_dog or
     * in the same directory as the jar file.
     */
    public GameModel(){
        try {
            dictionaryFile = getRootDictionary();
            this.phraseBook = GameDictionaryReader.readDictionary(dictionaryFile);
        } catch (FileNotFoundException e){
            System.out.println("ERROR: Dictionary could not be found!");
        }
    }


    public GameModel(String dict){
        try {
            dictionaryFile = new File(dict);
            this.phraseBook = GameDictionaryReader.readDictionary(dictionaryFile);
        } catch(FileNotFoundException e){
            System.out.println("ERROR: Not a valid dictionary file! (`" + dictionaryFile.toString() + "`)");
        }
    }

    private static File getRootDictionary() {
        File dictionaryFile;
        String runtimeLocation = SwingDrownTheScurvyDog.class.getProtectionDomain().getCodeSource().getLocation().getFile();	//backtrace fo find teh runtime path
        //System.out.println("Running from : " + runtimeLocation);
        if(runtimeLocation.contains(".jar")){
            //System.out.println("RUNNING FROM JAR!");
            //regex adapted from http://stackoverflow.com/questions/8374742/regex-last-occurrence
            runtimeLocation = runtimeLocation.replaceAll("(\\\\|/)(?:.(?!(\\\\|/)))+$", "");
            //System.out.println("New Runtime: " + runtimeLocation);
        }
        dictionaryFile = new File(runtimeLocation + "/dictionary.json");
        System.out.println("\tAssuming dictionary at : " + dictionaryFile.toString());
        return dictionaryFile;
    }

    @Override
	public String getVisible() {
		return visible;
	}
	
	private void updateVisible() {
		String newVisible = "";
		for(char w: word.toCharArray()){
			for(char g: guessedChars){
				if(g == w)
					newVisible+=w;
				else newVisible+='*';
			}
		}
		if(visible.length() != newVisible.length())
			System.out.println("We've got a problem here!");
		visible = newVisible;
	}

	@Override
	public String getHidden() {
        String wordHints = "";
        for(String w: phraseBook.getAllItems()){
            for(char c: guessedChars)
                if(w.contains(""+c)){ //There has to be a better way??!?!?!?!?
                    wordHints+=wordHints + ", ";
                    break;
                }
        }
		return null;
	}

	@Override
	public int guessLeft() {
		return lives-guesses;
	}

	@Override
	public String getLetters() {
		String guessed = "";
		for(char c: guessedChars)
			guessed+=c;
		return guessed;
	}

	@Override
	public boolean tryThis(char letter) {
		guessedChars.add(letter);
		for(char c: word.toCharArray())
			if(c == letter){
				updateVisible();
				return true;
			}
		return false;
	}

	@Override
	public boolean tryWord(String guess) {
		return guess.equals(word);
	}

    public static GameModel createDict() {
        GameDictionaryReader.createDictionary(getRootDictionary());
        return new GameModel();
    }

    @Override
    public synchronized void addObserver(Observer o){
        super.addObserver(o);//fix later!
    }

    public boolean isInGame() {
		return inGame;
	}

	public static void setInGame() {
		inGame = true;
	}

    public static void setOutOfGame() {inGame = false; }

}
