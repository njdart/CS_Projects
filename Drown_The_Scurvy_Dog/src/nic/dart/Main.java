package nic.dart;

import nic.dart.Model.GameModel;
import nic.dart.View.CommandLine.CliView;
import nic.dart.View.PirateView;
import nic.dart.View.Swing.SwingView;

import java.io.FileNotFoundException;

public class Main {

    /**
     * main method.
     * @param args none given will use a swing UI, and ask for inputs as needed. if --no-gui is used,
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException /* from SwingDrownTheScurvyDog*/{

        PirateView view;
        GameModel model;


        //Cli or help
    	if(args.length > 0){
            view = new CliView();
            //help (me?)
            if(args[0].toLowerCase().equals("--help") ||
               args[0].toLowerCase().equals("help")) {
                System.out.println("Usage:\n" +
                        "$ java ... <--no-gui> <dictionary file | --create>\n" +
                        "\n" +
                        "\t--no-gui\tCreates without a gui present. Uses the command line\n" +
                        "\n" +
                        "\t--create\tCreates the dictionary file. Will be empty!\n" +
                        "\t\t\t\tIf no dictionary file is provided, it is assumed it will be in `out/production/Drown_the_Scurvy_Dog or\n" +
                        "\t\t\t\twith the jar file.");
                System.exit(0);
                model = null;
            //no gui
            } else if(args[0].toLowerCase().equals("--no-gui")){
                view = new CliView();
            	if(args.length > 1){
            	    //create dictionary file
                    if(args[1].toLowerCase().equals("--create")) {
                        model = new GameModel();
                        model.createDict();
                    //help
                    } else if(args[1].toLowerCase().equals("--help") ||
                              args[1].toLowerCase().equals("help")) {
                        System.out.println("Usage:\n" +
                                "$ java ... --no-gui <dictionary file | --create>\n" +
                                "\n" +
                                "\t--create\tCreates the dictionary file. Will be empty!\n" +
                                "\t\t\t\tIf no dictionary file is provided, it is assumed it will be in `out/production/Drown_the_Scurvy_Dog or\n" +
                                "\t\t\t\twith the jar file.");
                        System.exit(0);
                        model = null;
                    //assume the second arg is the file.
                    } else model = new GameModel(args[1]);
                //nogui with specified file location
            	} else model = new GameModel();
                //add the observer to the model, in this case, CLI veriant.
                view = new CliView();
            } else {
                System.out.println("Error, Unknown argument, use `--help` for instructions");
                model = null;
            }


        //else, Swing view
        } else {
            view = new SwingView();
            model = new GameModel();
        }
        view.addModel(model);
    }
}