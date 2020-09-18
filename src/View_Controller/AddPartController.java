/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import kylegreeninventorysystem.Error;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kylegreeninventorysystem.inputValidation;

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
    @FXML
    private Label warningLabel;
    
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
        Error.clearError(partNameTextField, warningLabel);
    }

    @FXML
    private void handleInvInput(KeyEvent event) {
        // Validate Inv Input
        try{
            Integer.parseInt(partInvTextField.getText());
            Error.clearError(partInvTextField, warningLabel);
        }catch(NumberFormatException nfe){
            Error.showError(partInvTextField, warningLabel, "Inv must be a number");
        }
    }

    @FXML
    private void handlePriceInput(KeyEvent event) {
        // Validate Price Input
        try{
            Double.parseDouble(partPriceTextField.getText());
            Error.clearError(partPriceTextField, warningLabel);
        } catch(NumberFormatException nfe){
            Error.showError(partPriceTextField, warningLabel, "Price must be a number");
        }
    }

    @FXML
    private void handleMinInput(KeyEvent event) {
        // Validate Min Input
        try{
            Integer.parseInt(partMinTextField.getText());
            Error.clearError(partMinTextField, warningLabel);
        }catch(NumberFormatException nfe){
            Error.showError(partMinTextField, warningLabel, "Min must be a number");
        }
    }

    @FXML
    private void handleMaxInput(KeyEvent event) {
        // Validate Max Input
        try{
            Integer.parseInt(partMaxTextField.getText());
            Error.clearError(partMaxTextField, warningLabel);
        }catch(NumberFormatException nfe){
            Error.showError(partMinTextField, warningLabel, "Max Must Be A Number");
        }
    }

    @FXML
    private void handleVariableInput(KeyEvent event) {
        if(selectedPartType == SelectedPartType.INHOUSE){
            try{
                Integer.parseInt(variableTextField.getText());
                Error.clearError(variableTextField, warningLabel);
            }catch(NumberFormatException nfe){
                Error.showError(variableTextField, warningLabel, "Machine ID Must Be A Number.");
            }
        }else if(selectedPartType == SelectedPartType.OUTSOURCED){
            Error.clearError(variableTextField, warningLabel);
        }
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
    private void handleSave(MouseEvent event) throws IOException, Exception {
        // Gather new part data
        int newPartId = Inventory.getCurrentPartId();
        String newPartName = partNameTextField.getText();
        double newPartPrice;
        int newPartInv;
        int newPartMin;
        int newPartMax;
        
        if(newPartName.isEmpty()){
            Error.showError(partNameTextField, warningLabel, "Part Name Is Required");
            return;
        }
        
        // Validate The Inputs Before Saving New Part
        try{
            newPartInv = Integer.parseInt(partInvTextField.getText());
        }catch(NumberFormatException nfe){
            Error.showError(partInvTextField, warningLabel, "Inv Must Be A Number.");
            return;
        }
        try{
            newPartPrice = Double.parseDouble(partPriceTextField.getText());
        } catch(NumberFormatException nfe){
            Error.showError(partPriceTextField, warningLabel, "Price Must Be A Number.");
            return;
        }
        try{
            newPartMin = Integer.parseInt(partMinTextField.getText());
        }catch(NumberFormatException nfe){
            Error.showError(partMinTextField, warningLabel, "Min Must Be A Number.");
            return;
        }
        try{
            newPartMax = Integer.parseInt(partMaxTextField.getText());
        }catch(NumberFormatException nfe){
            Error.showError(partMaxTextField, warningLabel, "Max Must Be A Number.");
            return;
        }
        
        // Valdate Inventory Level Is Between Min And Max
        if(!inputValidation.isValidInv(newPartInv, newPartMin, newPartMax)){
            Error.showError(partInvTextField, warningLabel, "Inventory must be between minimum and maximum.");
            return;
        }    
        
        if(selectedPartType == SelectedPartType.INHOUSE){
            int newPartMachineId;
            try{
                newPartMachineId = Integer.parseInt(variableTextField.getText());
            }catch(NumberFormatException nfe){
                Error.showError(variableTextField, warningLabel, "Machine ID Must Be A Number.");
                return;
            }
            Inventory.addPart(new InhousePart(newPartId, newPartName, newPartPrice, newPartInv, newPartMin, newPartMax, newPartMachineId));
            Inventory.incrementPartId();
            
        } else if(selectedPartType == SelectedPartType.OUTSOURCED){
            String newPartCompanyName = variableTextField.getText();
            if(newPartCompanyName.isEmpty()){
                Error.showError(variableTextField, warningLabel, "Company Name Is Required.");
            } else{
                Inventory.addPart(new OutsourcedPart(newPartId, newPartName, newPartPrice, newPartInv, newPartMin, newPartMax, newPartCompanyName));
                Inventory.incrementPartId();
            }
            
        }
            // Return to main screen
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
            stage.setScene(new Scene(scene));
            stage.show();
    }

    @FXML
    private void handleCancel(MouseEvent event) throws IOException {
        // Confirm Cancel Action
        Alert cancelAlert = new Alert(AlertType.CONFIRMATION);
        cancelAlert.headerTextProperty().set("Are you sure you want to cancel?");
        
        Optional<ButtonType> result = cancelAlert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            // Return to main screen if user confirms cancel
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
}
