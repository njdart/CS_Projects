package nic.dart.DrownTheScurvyDog;

public class GameModel implements GameModelInterface {
	
	private String word;
	private int guesses;
	private int lives;
	
	
	@Override
	public String getVisible() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHidden() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int guessLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLetters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryThis(char letter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryWord(String guess) {
		// TODO Auto-generated method stub
		return false;
	}

}
