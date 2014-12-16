package Nic.Dart.View;

import Nic.Dart.Model.GameModel;

/**
 * Created by Nic on 14/04/14.
 */
public interface PirateView {

    /**
     * Add the model to the view. This is the only method (other than the constructor)
     * that is called on the view.
     *
     * All views should implement this interface, and be added to the main class.
     * @param model
     */
    public void addModel(GameModel model);
}
