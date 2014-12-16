package Nic.Dart.Model;
/**
* This interface describes the behaviour of a hangman type of game
* To be used in CS124 individual assignment
*/
public interface GameModelInterface {
	/**
	* this method returns what the user is allowed to see
	*/
	public String getVisible();
	/**
	* this method returns the words to guess
	*/
	public String getHidden();
	/**
	* this method tells how many guesses are left
	*/
	public int guessLeft();
	/**
	* this method returns the letters that have already been guessed
	*/
	public String getLetters();
	/**
	* this method sees if the letter is in the words
	* if it is it updates what the user can see (visible)
	* if not it removes a guess
	*
	* @param letter the letter to try
	* @return whether there is winner
	*/
	public boolean tryThis(char letter);
	/**
	* this method sees if the word guess is correct
	* if it is it updates what the user can see (visible)
	* if not it removes 5 guesses
	*
	* @param guess the word to guess
	* @return whether there is winner
	*/
	public boolean tryWord(String guess);
}