package nic.Dart.Model;

import nic.Dart.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.TreeSet;

public class GameModel implements GameModelInterface{
	
	private static String word;
	private static int fails = 0;
	private static final int lives = 10;
	private static TreeSet<Character> guessedChars = new TreeSet<Character>();
	private static boolean inGame = false;
	private static String visible;
    private static PhraseBook phraseBook;
    private static File dictionaryFile;
    private static int guesses = 0;

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
        String runtimeLocation = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();	//backtrace fo find teh runtime path
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

    public int getGuesses() {
        return guesses;
    }

    public boolean getInGame() {
        return inGame;
    }

    @Override
	public String getVisible() {
		return word;
	}

	@Override
	public String getHidden() {
        String wordHints = "";
        boolean found;
        for(char w : word.toCharArray()) {
            found = false;
            if(w == ' '){
                wordHints+=w;
            } else {
                for (char c : guessedChars) {
                    if (w == c) {
                        wordHints += w;
                        found = true;
                    }
                }
                if (!found) wordHints += "*";
            }
        }
		return wordHints;
	}

	@Override
	public int guessLeft() {
		return lives- fails;
	}

	@Override
	public String getLetters() {
		String guessed = "";
		for(char c: guessedChars)
			guessed+=c+",";
		return guessed;
	}

	@Override
	public boolean tryThis(char letter) {
        ++guesses;
        if(guessLeft()<= 0) return false;
		guessedChars.add(letter);
		for(char c: word.toCharArray())
			if(c == letter){
				return true;
			}

        ++fails;
		return false;
	}

	@Override
	public boolean tryWord(String guess) {
        ++guesses;
        if(guessLeft() <= 0) return false;
        if(guess.equals(word)){

            return true;
        } else {
            ++fails;
            return false;
        }
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
        if(state) {
            word = phraseBook.getAllItems().get(new Random().nextInt(phraseBook.length()));
            System.out.println("Starting game with word " + word);
        } else {
            //reset for a new game
            word = "";
            fails = 0;
            guessedChars = new TreeSet<Character>();
            visible = "";
            guesses = 0;
        }
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

    public boolean isCompletedWord(){
        boolean containsUnknowns = false;

        for(char c : getHidden().toCharArray()) {
            if(c == '*') {
                containsUnknowns = true;
                break;
            }
        }

        return !containsUnknowns;
    }

    public boolean isGameOver() {
        return guessLeft() <=0;
    }

    public int getFails() {
        return fails;
    }
}
