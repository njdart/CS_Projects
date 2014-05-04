package Nic.Dart.Tests;

import Nic.Dart.Model.GameModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameModelTest {

    @Test
    public void testGetVisible() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("a right skallywag");

        assertEquals("getVisable not returning whole word", "a right skallywag", model.getVisible());
    }

    @Test
    public void testGetHiddenNoGuessesNoSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("arrrrrrgh!");

        assertEquals("getHidden not hiding properly with no guesses", "**********", model.getHidden());
    }

    @Test
    public void testGetHiddenNoGuessesWithSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("antiscurvy machine");

        assertEquals("getHidden not displaying spaces", "********** *******", model.getHidden());
    }

    @Test
    public void testGetHiddenWithGuessesNoSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("starbord!");
        model.tryThis('s');
        model.tryThis('r');

        assertEquals("getHidden not displaying properly with guesses", "s**r**r**", model.getHidden());
    }

    @Test
    public void testGetHiddenWithGuessesWithSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("livin' and dead alike shall bow before the great cap'n freeman");

        model.tryThis('a');
        model.tryThis('b');
        model.tryThis('c');

        assertEquals("getHidden not working wFith spaces and guesses",
                "****** a** **a* a**** **a** b** b***** *** ***a* ca*** *****a*",
                model.getHidden());
    }

    @Test
    public void testGuessLeft() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("land lubber");

        model.tryThis('a');
        model.tryThis('b');

        model.tryThis('c');

        assertEquals("Incorrect guesses left", 9, model.guessLeft());
    }

    @Test
    public void testGetLetters() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("antiscurvy machine");

        model.tryThis('a');
        model.tryThis('b');

        model.tryThis('c');
        model.tryThis('d');

        model.tryThis('a');

        assertEquals("Incorrect guesses left", "a,b,c,d,", model.getLetters());
    }

    @Test
    public void testTryThisLowercase() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("ahoythar");

        assertTrue("Could not guess with lowercase letter ", model.tryThis('a'));

    }

    @Test
    public void testTryThisUppercase() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("meharties");

        assertTrue("Could not guess with uppercase letter ", model.tryThis('H'));
    }

    @Test
    public void testTryThisSpecialChars() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("arrrrrrgh!");

        assertTrue("Could not guess with uppercase letter ", model.tryThis('!'));
    }

    @Test
    public void testTryThisSpecialCharsNonExistant() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("arrrrrrgh!");

        assertFalse("Could not guess with uppercase letter ", model.tryThis('%'));
    }


    @Test
    public void testTryWordLowercaseWithoutSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("freeman");

        assertTrue("Could not guess with lowercase word without spaces", model.tryWord("freeman"));
    }

    @Test
    public void testTryWordLowercaseWithSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("livin' and dead alike shall bow before the great Cap'n Freeman");

        assertTrue("Could not guess with lowercase word with spaces",
                model.tryWord("livin' and dead alike shall bow before the great cap'n freeman"));
    }

    @Test
    public void testTryWordUppercaseWithoutSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("starbord");

        assertTrue("Could not guess with uppercase word without spaces", model.tryWord("STARBORD"));
    }

    @Test
    public void testTryWordUppercaseWithSpaces() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("antiscurvy machine");

        assertTrue("Could not guess with uppercase word with spaces",
                model.tryWord("AnTiScUrVy MaChInE"));
    }

    @Test
    public void testIsCompletedWordWithTryWord() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("ahoy");

        model.tryWord("ahoy");
        assertTrue("Word should be competed with tryWord", model.isCompletedWord());
    }

    @Test
    public void testIsCompletedWordWithTryThis() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("ye");

        model.tryThis('y');
        model.tryThis('e');
        assertTrue("Word should be competed with tryThis", model.isCompletedWord());
    }

    @Test
    public void testIsCompletedWordWithWrongTryWord() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("aft");

        model.tryWord("asdf");
        assertFalse("Word should not be competed with tryWord", model.isCompletedWord());
    }

    @Test
    public void testIsCompletedWordWithWrongTryThis() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("peglegg");

        model.tryThis('p');
        model.tryThis('e');
        assertFalse("Word should not be competed with tryThis", model.isCompletedWord());
    }

    @Test
    public void testIsGameOver() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("xyz");

        for(int i = 0; i < 10; i++)
            model.tryThis((char)(65+i));
        assertTrue("Game should be over", model.isGameOver());
    }

    @Test
    public void testIsGameNotOverChar() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("wheresTheRumGone?");

        model.tryThis('p');
        assertFalse("Game should be over", model.isCompletedWord());
    }

    @Test
    public void testIsGameNotOverWord() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("isTheRumGone?");

        model.tryWord("theRumsGoneJack!");
        assertFalse("Game should be over", model.isCompletedWord());
    }

    @Test
    public void testGetFails() throws Exception {
        GameModel model = new GameModel();
        model.setInGame(true);
        model.setWord("well blow me down!");

        model.tryThis('p');
        model.tryWord("blow me down");
        assertEquals("Incorrect failed guessed", 2, model.getFails());
    }
}