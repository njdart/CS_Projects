package Nic.Dart.Model;

import Nic.Dart.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class GameModel implements GameModelInterface{
	
	private String word;
	private int fails = 0;
	private final int lives = 10;
	private TreeSet<Character> guessedChars = new TreeSet<Character>();
	private boolean inGame = false;
	private String visible;
    private PhraseBook phraseBook;
    private File dictionaryFile;
    private int guesses = 0;
    private boolean isComplete = false;

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
            //System.out.println("Assuming dictionary file is with .jar or in bin/");
            this.phraseBook = GameDictionaryReader.readDictionary(getRootDictionary());
        } catch (FileNotFoundException e){
            System.out.println("ERROR: Dictionary could not be found!");
            System.exit(1);
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
     * tries to get the location of the runtime
     * @return Absolute file of the dictionary file
     */
    private File getRootDictionary() {
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
        //System.out.println("\tAssuming dictionary at : " + dictionaryFile.toString());
        return dictionaryFile;
    }

    public int getLives() {
        return lives;
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
        for(char w : word.toLowerCase().toCharArray()) {
            found = false;
            if(w == ' '){
                wordHints+=w;
            } else {
                for (char c : guessedChars) {
                    if (w == Character.toLowerCase(c)) {
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
        if(guessLeft() <= 0) return false;
        letter = Character.toLowerCase(letter);
        ++guesses;
		if(guessedChars.add(letter)) {
            boolean toReturn = false;
            for (char c : word.toCharArray())
                if (Character.toLowerCase(c) == letter) {
                    toReturn = true;
                    break;
                }

            boolean unknownChars = false;
            for (char c : getHidden().toCharArray()) {
                if (c == '*') {
                    unknownChars = true;
                    break;
                }
            }
            if (!unknownChars)
                isComplete = true;

            if (!toReturn)
                ++fails;
            return toReturn;
        } return false;
	}

	@Override
	public boolean tryWord(String guess) {
        if(guessLeft() <= 0) return false;
        ++guesses;
        if(word.toLowerCase().equals(guess.toLowerCase())){
            isComplete = true;
            return true;
        } else {
            ++fails;
            return false;
        }
	}

    public void createDict() {
        dictionaryFile = getRootDictionary();
        try {
            GameDictionaryReader.createDictionary(dictionaryFile);
        } catch (FileNotFoundException e){
            System.out.println("Error, file not found!");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean state) {
		inGame = state;
        if(state) {
            //System.out.println("Starting game with word " + word);
            //reset for a new game
            word = "";
            fails = 0;
            guessedChars = new TreeSet<Character>();
            visible = "";
            guesses = 0;
            isComplete = false;

            word = phraseBook.getAllItems().get(new Random().nextInt(phraseBook.length()));
        }
	}

    public boolean getGameState() { return inGame; }

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
        return isComplete;
    }

    public void setWord(String word){
        //System.out.println("WARNING, THE MODELS' WORD HAS BEEN CHANGED!");
        this.word = word;   //Only used in testing.
    }

    public boolean isGameOver() {
        return guessLeft() <=0;
    }

    public int getFails() {
        return fails;
    }

    public void createDict(File file) throws FileNotFoundException, IOException{
        phraseBook = GameDictionaryReader.createDictionary(file);
    }

    public Character giveHint() {
        LinkedList<Character> unusedChars = new LinkedList<Character>();

        for(Character c: word.toLowerCase().toCharArray())
            if(!guessedChars.contains(c))
                unusedChars.add(c);

        if(unusedChars.size() > 0) {
            ++fails;
            return unusedChars.get(new Random().nextInt(unusedChars.size()));
        } else return null;
    }
}
