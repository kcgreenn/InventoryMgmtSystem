/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
public class ModifyPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    enum SelectedPartType {
        INHOUSE,
        OUTSOURCED
    };
    SelectedPartType selectedPartType;
    
    private Part selectedPart;

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
   
    /*
    Put all of the selected part data into the text fields
    @param selectedPart The part that will be modified
    */

    /**
     * Passes the selected part information into the modify part screen
     * @param selectedPart The part that will be modified
     */

    public void sendPart(Part selectedPart){
        this.selectedPart = selectedPart;
        
        partIdTextField.setText(String.valueOf(selectedPart.getId()));
        partNameTextField.setText(selectedPart.getName());
        partInvTextField.setText(String.valueOf(selectedPart.getStock()));
        partPriceTextField.setText(String.valueOf(selectedPart.getPrice()));
        partMinTextField.setText(String.valueOf(selectedPart.getMin()));
        partMaxTextField.setText(String.valueOf(selectedPart.getMax()));
        
        // Change form for each part type
        if(selectedPart instanceof InhousePart){
            this.selectedPartType = SelectedPartType.INHOUSE;
            inhouseRadioButton.setSelected(true);
            variableTextField.setText(String.valueOf(((InhousePart) selectedPart).getMachineId()));
            variableLabel.setText("Machine ID");
        } else if(selectedPart instanceof OutsourcedPart){
            this.selectedPartType = SelectedPartType.OUTSOURCED;
            outsourcedRadioButton.setSelected(true);
            variableTextField.setText(((OutsourcedPart) selectedPart).getCompanyName());
            variableLabel.setText("Company Name");
        }
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void handleIdInput(KeyEvent event) {
    }

    @FXML
    private void handleNameInput(KeyEvent event) {
        kylegreeninventorysystem.Error.clearError(partNameTextField, warningLabel);        
    }

    @FXML
    private void handleInvInput(KeyEvent event) {
        // Validate Inv Input
        try{
            Integer.parseInt(partInvTextField.getText());
            kylegreeninventorysystem.Error.clearError(partInvTextField, warningLabel);
        }catch(NumberFormatException nfe){
            if(partInvTextField.getText().length()>0){
                kylegreeninventorysystem.Error.showError(partInvTextField, warningLabel, "Inv must be a number");
            }
        }        
    }

    @FXML
    private void handlePriceInput(KeyEvent event) {
        // Validate Price Input
        try{
            Double.parseDouble(partPriceTextField.getText());
            kylegreeninventorysystem.Error.clearError(partPriceTextField, warningLabel);
        } catch(NumberFormatException nfe){
            if(partPriceTextField.getText().length()>0){
                kylegreeninventorysystem.Error.showError(partPriceTextField, warningLabel, "Price must be a number");
            }
        }          
    }

    @FXML
    private void handleMinInput(KeyEvent event) {
        // Validate Min Input
        try{
            Integer.parseInt(partMinTextField.getText());
            kylegreeninventorysystem.Error.clearError(partMinTextField, warningLabel);
        }catch(NumberFormatException nfe){
            if(partMinTextField.getText().length()>0){
                kylegreeninventorysystem.Error.showError(partMinTextField, warningLabel, "Min must be a number");
            }
        }          
    }

    @FXML
    private void handleMaxInput(KeyEvent event) {
        // Validate Max Input
        try{
            Integer.parseInt(partMaxTextField.getText());
            kylegreeninventorysystem.Error.clearError(partMaxTextField, warningLabel);
        }catch(NumberFormatException nfe){
            if(partMaxTextField.getText().length()>0){
                kylegreeninventorysystem.Error.showError(partMaxTextField, warningLabel, "Max Must Be A Number");
            }
        }          
    }

    @FXML
    private void handleVariableInput(KeyEvent event) {
        if(selectedPartType == ModifyPartController.SelectedPartType.INHOUSE){
            try{
                Integer.parseInt(variableTextField.getText());
                kylegreeninventorysystem.Error.clearError(variableTextField, warningLabel);
            }catch(NumberFormatException nfe){
                kylegreeninventorysystem.Error.showError(variableTextField, warningLabel, "Machine ID Must Be A Number.");
            }
        }else if(selectedPartType == ModifyPartController.SelectedPartType.OUTSOURCED){
            kylegreeninventorysystem.Error.clearError(variableTextField, warningLabel);
        }        
    }

    @FXML
    private void handleInhouseSelect(MouseEvent event) {
        variableLabel.setText("Machine ID");
        this.selectedPartType = SelectedPartType.INHOUSE;
        partNameTextField.requestFocus();     
    }

    @FXML
    private void handleOutsourcedSelect(MouseEvent event) {
        variableLabel.setText("Company Name");
        this.selectedPartType = SelectedPartType.OUTSOURCED;
        partNameTextField.requestFocus();        
    }

    @FXML
    private void handleSave(MouseEvent event) throws IOException, Exception {
        // Gather new part data
        int newPartId = Integer.parseInt(partIdTextField.getText());
        String newPartName = partNameTextField.getText();
        double newPartPrice;
        int newPartInv;
        int newPartMin;
        int newPartMax;
        
        if(newPartName.isEmpty()){
            kylegreeninventorysystem.Error.showError(partNameTextField, warningLabel, "Part Name Is Required");
            return;
        }
        
        // Validate The Inputs Before Saving New Part
        try{
            newPartInv = Integer.parseInt(partInvTextField.getText());
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(partInvTextField, warningLabel, "Inv Must Be A Number.");
            return;
        }
        try{
            newPartPrice = Double.parseDouble(partPriceTextField.getText());
        } catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(partPriceTextField, warningLabel, "Price Must Be A Number.");
            return;
        }
        try{
            newPartMin = Integer.parseInt(partMinTextField.getText());
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(partMinTextField, warningLabel, "Min Must Be A Number.");
            return;
        }
        try{
            newPartMax = Integer.parseInt(partMaxTextField.getText());
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(partMaxTextField, warningLabel, "Max Must Be A Number.");
            return;
        }
        if(!inputValidation.isValidMin(newPartMin, newPartMax)){
            kylegreeninventorysystem.Error.showError(partMinTextField, warningLabel, "Min must be less than max.");
            return;
        }        
        // Valdate Inventory Level Is Between Min And Max
        if(!inputValidation.isValidInv(newPartInv, newPartMin, newPartMax)){
            kylegreeninventorysystem.Error.showError(partInvTextField, warningLabel, "Inventory must be between minimum and maximum.");
            return;
        }    
        
        if(this.selectedPartType == ModifyPartController.SelectedPartType.INHOUSE){
            int newPartMachineId;

            try{
                newPartMachineId = Integer.parseInt(variableTextField.getText());
            }catch(NumberFormatException nfe){
                kylegreeninventorysystem.Error.showError(variableTextField, warningLabel, "Machine ID Must Be A Number.");
                return;
            }
            int index = Inventory.getAllParts().indexOf(this.selectedPart);
            Inventory.updatePart(index, 
                    new InhousePart(newPartId, newPartName, newPartPrice, newPartInv, newPartMin, newPartMax, newPartMachineId));
            
        } else if(this.selectedPartType == ModifyPartController.SelectedPartType.OUTSOURCED){
            String newPartCompanyName = variableTextField.getText();
            if(newPartCompanyName.isEmpty()){
                kylegreeninventorysystem.Error.showError(variableTextField, warningLabel, "Company Name Is Required.");
            } else{
                int index = Inventory.getAllParts().indexOf(this.selectedPart);
                Inventory.updatePart(index, 
                    new OutsourcedPart(newPartId, newPartName, newPartPrice, newPartInv, newPartMin, newPartMax, newPartCompanyName));
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
