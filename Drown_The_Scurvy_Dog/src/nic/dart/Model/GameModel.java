package nic.dart.Model;

import java.util.ArrayList;

public class GameModel implements GameModelInterface {
	
	private String word;
	private int guesses;
	private int lives;
	private ArrayList<Character> guessedChars = new ArrayList<Character>();
	private boolean inGame = false;
	private String visible;
	
	
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
		// TODO Auto-generated method stub
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

	public boolean isInGame() {
		return inGame;
	}

}
