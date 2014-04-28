package nic.dart.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable implements GameModelInterface{
	
	private static String word;
	private int guesses;
	private int lives;
	private ArrayList<Character> guessedChars = new ArrayList<Character>();
	private static boolean inGame = false;
	private String visible;
    private static PhraseBook phraseBook;
    private File dictionaryFile;

    /**
     * The game model, contains methods for searching strings,
     * represenging a phrase/word as known and unknown chars,
     * guess methods.
     *
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

    /**
     * Assumes the dict is at a location specified by the param `dict`
     * @param dict `location as a string`
     */
    public GameModel(String dict){
        try {
            dictionaryFile = new File(dict);
            this.phraseBook = GameDictionaryReader.readDictionary(dictionaryFile);
        } catch(FileNotFoundException e){
            System.out.println("ERROR: Not a valid dictionary file! (`" + dictionaryFile.toString() + "`)");
            System.exit(1);
        }
    }

    /**
     * Takes a file as the dictionary
     * @param dict
     */
    public GameModel(File dict){ this.dictionaryFile = dict; }

    /**
     * tries to get the location of the runtime
     * @return
     */
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

    public boolean getInGame() {
        return inGame;
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
        if(visible != null) {
            if (visible.length() != newVisible.length())
                System.out.println("We've got a problem here!");
            visible = newVisible;
        }
	}

	@Override
	public String getHidden() {
        String wordHints = "";
        boolean found = false;
        for(char w : word.toCharArray()) {
            found = false;
            for (char c : guessedChars) {
                if (w == c) {
                    wordHints += w;
                    found = true;
                }
            }
            if(!found) wordHints += "*";
        }
		return wordHints;
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

    public void createDict() {
        dictionaryFile = getRootDictionary();
        GameDictionaryReader.createDictionary(dictionaryFile);
    }

    public boolean isInGame() {
		return inGame;
	}

	public static void setInGame(boolean state) {
		inGame = state;
        word = phraseBook.getAllItems().get(new Random().nextInt(phraseBook.length()));
        System.out.println("Starting game with word " + word);
	}

    public static boolean getGameState() { return inGame; }

    public PhraseBook getPhraseBook() {
        return phraseBook;
    }

    public boolean load(String fileAbsolute) {
        try{
            PhraseBook tempPhraseBook = null;
            if(new File(fileAbsolute).exists()) {
                dictionaryFile = new File(fileAbsolute);
                tempPhraseBook = GameDictionaryReader.readDictionary(dictionaryFile);

            }
            if(tempPhraseBook != null) {
                phraseBook = tempPhraseBook;
                return true;
            }
            return false;
        } catch (FileNotFoundException e){
            return false;
        }

    }

    public boolean reload() {
        try {
            GameDictionaryReader.readDictionary(dictionaryFile);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public String getDict() {
        return dictionaryFile.toString();
    }

    public boolean save() {
        try{
            GameDictionaryReader.writeDictionary(phraseBook, dictionaryFile);
            return true;
        } catch (IOException e){
            return false;
        }
    }
}
