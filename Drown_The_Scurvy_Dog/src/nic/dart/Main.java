package nic.dart;

import nic.dart.Model.CliDrownTheScurvyDog;
import nic.dart.Model.GameModel;
import nic.dart.Model.SwingDrownTheScurvyDog;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        GameModel model = null;

    	if(args.length > 0){
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
            } else if(args[0].toLowerCase().equals("--no-gui")){
            	if(args.length > 1){
            	    if(args[1].toLowerCase().equals("--create")) {
                        model = GameModel.createDict();
                    } else if(args[1].toLowerCase().equals("--help") ||
                              args[1].toLowerCase().equals("help")){
                        System.out.println("Usage:\n" +
                                "$ java ... --no-gui <dictionary file | --create>\n" +
                                "\n" +
                                "\t--create\tCreates the dictionary file. Will be empty!\n" +
                                "\t\t\t\tIf no dictionary file is provided, it is assumed it will be in `out/production/Drown_the_Scurvy_Dog or\n" +
                                "\t\t\t\twith the jar file.");
                        System.exit(0);
                    } else{
                        model = new GameModel(args[1]);
                    }
            	} else model = new GameModel();
                model.addObserver(new CliDrownTheScurvyDog());
            }
        } //else model = new SwingDrownTheScurvyDog().getModel();


    }
}