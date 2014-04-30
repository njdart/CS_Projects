package nic.Dart.View.CommandLine;

import nic.Dart.Model.GameModel;
import nic.Dart.View.PirateView;

import java.util.Scanner;

/**
 * Created by nic on 04/04/14.
 */
public class CliView implements PirateView{

    private GameModel model;
    private boolean exit = false;
    Scanner in = new Scanner(System.in);

    @Override
    public void addModel(GameModel model) {
        this.model = model;

        //this is considered the main method for this view.

        System.out.println("You are playing 'Drown the Scurvy Dog!\n" +
                "type 'help' for help or 'new' for a new game");

        while(!exit){
            System.out.print("\n>");

                switch(in.nextLine().toLowerCase()) {
                    case "save":
                        model.save();
                        System.out.println("Saving the dictionary file:\n" + model.getDict().toString());
                        break;
                    case "reload":
                        model.reload();
                        System.out.println("Reloading the dictionary file:\n" + model.getDict().toString());
                        break;
                    case "list":
                        listWords();
                        break;
                    case "new":
                        doGame();
                        break;
                    case "add":
                        add();
                        break;
                    case "remove":
                        remove();
                        break;
                    case "exit":
                        System.out.println("Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("\thelp\tShows this help message\n" +
                            "\tsave\tSave the dictionary file (defaults to dictionary.json)\n" +
                            "\treload\tReloads the current dictionary (note, to use a different dictionary file,\n" +
                            "\t\t\trestart the program using that file)\n" +
                            "\tlist\tLists all words and phrases\n" +
                            "\tnew\t\tStarts a new game\n" +
                            "\tadd\t\tAdds a word or phrase\n" +
                            "\tremove\tRemoves a word or phrase");
                        break;
                }
            }
        }

    private void listWords() {
        System.out.println("\n\tWORDS:");
        for(String word : model.getPhraseBook().getWordsAsList())
            System.out.println(word);
        System.out.println("\n\tPHRASES:");
        for(String phrase : model.getPhraseBook().getPhrasesAsList())
            System.out.println(phrase);
    }

    private void remove() {
        System.out.println("Enter a word or phrase to remove\n>");
        if(model.getPhraseBook().remove(in.nextLine()))
            System.out.println("Removed Successfully");
        else System.out.println("Could not remove word or phrase");
    }

    private void add() {
        System.out.println("Enter a word or phrase to add\n>");
        if(model.getPhraseBook().add(in.nextLine()))
            System.out.println("Added Successfully");
        else System.out.println("Could not add word or phrase, it may already exist");
    }

    private void doGame() {
        boolean inGame = true;
        String guess = "";
        model.setInGame(true);
        while(inGame){
            System.out.println(" " + model.guessLeft() + " Guesses Left\t" + model.getLetters() + "\n" +
                    "\t" + model.getHidden());
            System.out.print("Make a guess (letter or word)\n>");
            guess = in.nextLine().trim();
            if(guess.length() > 1)
                model.tryWord(guess);
            else model.tryThis(guess.charAt(0));

            if(model.isGameOver()) {
                System.out.println("\nGAME OVER!\nThe word was '" + model.getVisible() + "'");
                inGame = false;
            } else if(model.isCompletedWord()) {
                System.out.println("\nCongratulations! You completed it in " + model.getGuesses() + " guesses\n("
                    + model.getFails() + " wrong tries)");
                inGame = false;
            }
        }

        model.setInGame(false);
    }
}
