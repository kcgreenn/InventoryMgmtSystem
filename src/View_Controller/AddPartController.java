/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kcgre
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton inhouseRadioButton;
    @FXML
    private ToggleGroup partType;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private TextField addPartIdTextField;
    @FXML
    private TextField addPartNameTextField;
    @FXML
    private TextField addPartInvTextField;
    @FXML
    private TextField addPartPriceTextField;
    @FXML
    private TextField addPartMinTextField;
    @FXML
    private TextField addPartMaxTextField;
    @FXML
    private Button newPartSaveButton;
    @FXML
    private Button newPartCancelButton;
    @FXML
    private Label variableAddPartLabel;
    @FXML
    private TextField variableAddPartInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAddPartInhouseSelect(MouseEvent event) {
    }

    @FXML
    private void handleAddPartOutsourcedSelect(MouseEvent event) {
    }

    @FXML
    private void handleAddPartNameInput(KeyEvent event) {
    }

    @FXML
    private void handleAddPartInvInput(KeyEvent event) {
    }

    @FXML
    private void handleAddPartPriceInput(KeyEvent event) {
    }

    @FXML
    private void handleAddPartMinInput(KeyEvent event) {
    }

    @FXML
    private void handleAddPartMaxInput(KeyEvent event) {
    }


    @FXML
    private void handleAddPartSave(MouseEvent event) {
    }

    @FXML
    private void handleAddPartCancel(MouseEvent event) {
    }

    @FXML
    private void handleAddPartVariableInput(KeyEvent event) {
    }
    
}
