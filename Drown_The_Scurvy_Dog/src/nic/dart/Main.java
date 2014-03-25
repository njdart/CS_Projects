package nic.dart;

import nic.dart.Model.SwingDrownTheScurvyDog;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        if(args.length > 0) {
            if (args[0].toLowerCase().equals("--no-gui"))
                System.out.println("No GUI!");
        } else new SwingDrownTheScurvyDog();
    }
}
