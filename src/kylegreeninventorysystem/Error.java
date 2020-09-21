/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylegreeninventorysystem;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * An object containing methods to display error messages in a GUI. All members are static, and no constructor is used.
 * @author KC Green
 */
public class Error {
       
    /**
    * Displays the user input error message in the GUI.
    * @param input - The text field containing the incorrect user input.
    * @param warningLabel - The label where the error message will be displayed.
    * @param message - The error message that will be displayed in the GUI
    */
    public static void showError(TextField input, Label warningLabel, String message){
        input.setStyle("-fx-border-color: #f00");
        warningLabel.setText(message);
    }
    /**
    * Clears a previously displayed error from the GUI.
    * @param input - The text field that contained the incorrect user input.
    * @param warningLabel - The label that contained the error message.
    */
    public static void clearError(TextField input, Label warningLabel){
        input.setStyle("-fx-border-color: #000");
        warningLabel.setText("");
    }
}
