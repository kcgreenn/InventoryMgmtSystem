/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TextField partIdTextField;
    @FXML
    private TextField partNameTextField;
    @FXML
    private TextField partInvTextField;
    @FXML
    private TextField partPriceTextField;
    @FXML
    private TextField partMinTextField;
    @FXML
    private TextField partMaxTextField;
    @FXML
    private Label variableLabel;
    @FXML
    private TextField variableTextField;
    @FXML
    private Button addPartSaveButton;
    @FXML
    private Button addPartCancelButton;
    
    Stage stage;
    Parent scene;
    enum SelectedPartType {
        INHOUSE,
        OUTSOURCED
    };
    SelectedPartType selectedPartType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPartType = SelectedPartType.INHOUSE;
        inhouseRadioButton.selectedProperty().setValue(Boolean.TRUE);
        partIdTextField.setFocusTraversable(false);
    }

    @FXML
    private void handleIdInput(KeyEvent event) {
    }

    @FXML
    private void handleNameInput(KeyEvent event) {
    }

    @FXML
    private void handleInvInput(KeyEvent event) {
    }

    @FXML
    private void handlePriceInput(KeyEvent event) {
    }

    @FXML
    private void handleMinInput(KeyEvent event) {
    }

    @FXML
    private void handleMaxInput(KeyEvent event) {
    }

    @FXML
    private void handleVariableInput(KeyEvent event) {
    }
    @FXML
    private void handleMachineIdInput(KeyEvent event){
        
    }
    @FXML
    private void handleCompanyNameInput(KeyEvent event){
        
    }


    @FXML
    private void handleInhouseSelect(MouseEvent event) {
        variableLabel.setText("Machine ID");
        selectedPartType = SelectedPartType.INHOUSE;
        partNameTextField.requestFocus();
    }

    @FXML
    private void handleOutsourcedSelect(MouseEvent event) {
        variableLabel.setText("Company Name");
        selectedPartType = SelectedPartType.OUTSOURCED;
    }

    @FXML
    private void handleSave(MouseEvent event) throws IOException {
        // Gather new part data
        int newPartId = Inventory.getCurrentPartId();
        String newPartName = partNameTextField.getText();
        double newPartPrice = Double.parseDouble(partPriceTextField.getText());
        int newPartInv = Integer.parseInt(partInvTextField.getText());
        int newPartMin = Integer.parseInt(partMinTextField.getText());
        int newPartMax = Integer.parseInt(partMaxTextField.getText());
        // Validate inventory level before adding part to inventory
//        System.out.print(Inventory.validateInvLevel(newPartInv, newPartMin, newPartMax));
//        if(Inventory.validateInvLevel(newPartInv, newPartMin, newPartMax)){
            if(selectedPartType == SelectedPartType.INHOUSE){
                int newPartMachineId = Integer.parseInt(variableTextField.getText());
                Inventory.addPart(new InhousePart(newPartId, newPartName, newPartPrice, newPartInv, newPartMin, newPartMax, newPartMachineId));
            } else if(selectedPartType == SelectedPartType.OUTSOURCED){
                String newPartCompanyName = variableTextField.getText();
                Inventory.addPart(new OutsourcedPart(newPartId, newPartName, newPartPrice, newPartInv, newPartMin, newPartMax, newPartCompanyName));
            }
            Inventory.incrementPartId();
//        } else{
//            System.out.print("Error: Inventory level must be between the minimum and maximum.");
//        }

        // Return to main screen
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void handleCancel(MouseEvent event) throws IOException {
        // Return to main screen
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
