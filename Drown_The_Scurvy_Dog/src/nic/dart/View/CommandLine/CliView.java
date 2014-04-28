package nic.dart.View.CommandLine;

import nic.dart.Model.GameModel;
import nic.dart.View.PirateView;

/**
 * Created by nic on 04/04/14.
 */
public class CliView implements PirateView{

    private GameModel model;


    @Override
    public void addModel(GameModel model) {
        this.model = model;
    }
}
