package nic.dart;

import nic.dart.Model.CliDrownTheScurvyDog;
import nic.dart.Model.GameModel;
import nic.dart.Model.SwingDrownTheScurvyDog;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    	
    	GameModel model;
    	
        if(args.length > 0){
            if (args[0].toLowerCase().equals("--no-gui")){
            	if(args[1] != null)
            		model = new CliDrownTheScurvyDog(args[1]).getModel();
            	else System.out.println("ERROR, No Dictionary provided!");
            }
        } else model = new SwingDrownTheScurvyDog().getModel();
    }
}