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
public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton inhouseRadioButton;
    @FXML
    private ToggleGroup partType;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private TextField modifyPartIdTextField;
    @FXML
    private TextField modifyPartNameTextField;
    @FXML
    private TextField modifyPartInvTextField;
    @FXML
    private TextField modifyPartPriceTextField;
    @FXML
    private TextField modifyPartMinTextField;
    @FXML
    private TextField modifyPartMaxTextField;
    @FXML
    private Label variableModifyPartLabel;
    @FXML
    private TextField variableModifyPartInput;
    @FXML
    private Button modifyPartSaveButton;
    @FXML
    private Button modifyPartCancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleModifyPartInhouseSelect(MouseEvent event) {
    }

    @FXML
    private void handleModifyPartOutsourcedSelect(MouseEvent event) {
    }

    @FXML
    private void handleModifyPartNameInput(KeyEvent event) {
    }

    @FXML
    private void handleModifyPartInvInput(KeyEvent event) {
    }

    @FXML
    private void handleModifyPartPriceInput(KeyEvent event) {
    }

    @FXML
    private void handleModifyPartMinInput(KeyEvent event) {
    }

    @FXML
    private void handleModifyPartMaxInput(KeyEvent event) {
    }

    @FXML
    private void handleModifyPartVariableInput(KeyEvent event) {
    }

    @FXML
    private void handleModifyPartSave(MouseEvent event) {
    }

    @FXML
    private void handleModifyPartCancel(MouseEvent event) {
    }
    
}
