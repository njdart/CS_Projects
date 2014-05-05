package Nic.Dart.View.Swing.EventListners;

import Nic.Dart.Model.GameModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by nic on 04/05/14.
 */

public class NewPhraseBookListener implements ActionListener {

    private GameModel model;
    private JFrame parent;

    public NewPhraseBookListener(GameModel model, JFrame parent) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter("json", "JSON"));
        chooser.setMultiSelectionEnabled(false);

        if(chooser.showDialog(parent, "Ok") == 1){
            try {
                model.createDict(chooser.getSelectedFile());

            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(parent, "Error whilst creating file!");
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
