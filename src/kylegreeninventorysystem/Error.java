/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylegreeninventorysystem;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kcgre
 */
public class Error {
        
    public static void showError(TextField input, Label warningLabel, String message){
        input.setStyle("-fx-border-color: #f00");
        warningLabel.setText(message);
    }
    
    public static void clearError(TextField input, Label warningLabel){
        input.setStyle("-fx-border-color: #000");
        warningLabel.setText("");
    }
}
