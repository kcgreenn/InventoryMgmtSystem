/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kylegreeninventorysystem.inputValidation;

/**
 * FXML Controller class
 *
 * @author kcgre
 */
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    private Product selectedProduct;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    private ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private ObservableList<Part> temporaryPartList = FXCollections.observableArrayList();
    
    private int totalPartPrice;
    
    @FXML
    private TableColumn<?, ?> allPartIdCol;
    @FXML
    private TableColumn<?, ?> allPartNameCol;
    @FXML
    private TableColumn<?, ?> allPartInvCol;
    @FXML
    private TableColumn<?, ?> allPartPriceCol;
    @FXML
    private TableColumn<?, ?> asscPartIdCol;
    @FXML
    private TableColumn<?, ?> asscPartNameCol;
    @FXML
    private TableColumn<?, ?> asscPartInvCol;
    @FXML
    private TableColumn<?, ?> asscPartPriceCol;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productInvField;
    @FXML
    private TextField productMinField;
    @FXML
    private TextField productMaxField;
    @FXML
    private TableView<Part> partSearchTable;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private Button addPartButton;
    @FXML
    private Button removePartButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    
    /*
    Put all of the selected part data into the text fields
    @param selectedProduct The product that will be modified
    */
    public void sendProduct(Product selectedProduct){
        this.selectedProduct = selectedProduct;
        
        productIdField.setText(String.valueOf(selectedProduct.getId()));
        productNameField.setText(selectedProduct.getName());
        productInvField.setText(String.valueOf(selectedProduct.getStock()));
        productPriceField.setText(String.valueOf(selectedProduct.getPrice()));
        productMinField.setText(String.valueOf(selectedProduct.getMin()));
        productMaxField.setText(String.valueOf(selectedProduct.getMax()));
        
        this.temporaryPartList.addAll(this.selectedProduct.getAllAssociatedParts());
        
        // Populate Table Views
        this.generateAllPartsTable();
        this.generateAsscPartsTable();
        
    }
    
    // Fill parts search tableview with parts data
    private void generateAllPartsTable(){
        partsInventory.setAll(Inventory.getAllParts());
        
        // Set PropertyValueFactory to match class members with columns
        allPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partSearchTable.setItems(partsInventory);
        partSearchTable.refresh();
    }
    
    // Fill associated parts tableview with selectedProduct data
    private void generateAsscPartsTable(){
        associatedPartsList.setAll(this.temporaryPartList);
        
        // Set PropertyValueFactory to match class members with columns
        asscPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        asscPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        asscPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asscPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));      
        
        associatedPartsTable.setItems(associatedPartsList);
        associatedPartsTable.refresh();        
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleProductNameInput(KeyEvent event) {
        kylegreeninventorysystem.Error.clearError(productNameField, warningLabel);
    }

    @FXML
    private void handleProductPriceInput(KeyEvent event) {
        // Validate Price Input
        try{
            Double.parseDouble(productPriceField.getText());
            kylegreeninventorysystem.Error.clearError(productPriceField, warningLabel);
        } catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productPriceField, warningLabel, "Price must be a number");
        }        
    }

    @FXML
    private void handleProductInvInput(KeyEvent event) {
        // Validate Inv Input
        try{
            Integer.parseInt(productInvField.getText());
            kylegreeninventorysystem.Error.clearError(productInvField, warningLabel);
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productInvField, warningLabel, "Inv must be a number");
        }
    }

    @FXML
    private void handleProductMinInput(KeyEvent event) {
        // Validate Min Input
        try{
            Integer.parseInt(productMinField.getText());
            kylegreeninventorysystem.Error.clearError(productMinField, warningLabel);
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productMinField, warningLabel, "Min must be a number");
        }        
    }

    @FXML
    private void handleProductMaxInput(KeyEvent event) {
        // Validate Max Input
        try{
            Integer.parseInt(productMaxField.getText());
            kylegreeninventorysystem.Error.clearError(productMaxField, warningLabel);
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productMaxField, warningLabel, "Max Must Be A Number");
        }        
    }

    @FXML
    private void handlePartSearchInput(KeyEvent event) {
        String searchInput = partSearchField.getText();
        // Try to search by ID if input is an integer, otherwise search by name
        try{
            partsInventory.setAll(Inventory.lookupPart(Integer.parseInt(searchInput)));
        }catch(NumberFormatException nfe){
            partsInventory.setAll(Inventory.lookupPart(searchInput));
        }

        partSearchTable.setItems(partsInventory);
        partSearchTable.refresh();        
    }

    @FXML
    private void handleAddPart(MouseEvent event) {
        // Find selected part in parts table
        Part selectedPart = partSearchTable.getSelectionModel().getSelectedItem();        
        
        // Tell User If No Part Is Selected
        if(selectedPart == null){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set("No part selected.");
            selectPartWarning.showAndWait();
            return;
        }        
        // Add selected part to temporary part list
        this.temporaryPartList.add(selectedPart);
        this.generateAsscPartsTable();
    }

    @FXML
    private void handleRemovePart(MouseEvent event) {
        // Find selected part in associated parts table
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        
        // Tell User If No Part Is Selected
        if(selectedPart == null){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set("No part selected.");
            selectPartWarning.showAndWait();
            return;
        } 
        // Remove selected part from product
        this.temporaryPartList.remove(selectedPart);
        this.generateAsscPartsTable();
    }

    @FXML
    private void handleSave(MouseEvent event) throws IOException{
        // Gather product data from text fields
        String productName = productNameField.getText();
        double productPrice;
        int productInv;
        int productMin;
        int productMax;        
        // Reset totalPartPrice if saved before
        this.totalPartPrice = 0;
        
        // Validate The Inputs Before Saving New Part
        if(productName.isEmpty()){
            kylegreeninventorysystem.Error.showError(productNameField, warningLabel, "Product Name Is Required");
            return;
        }
        try{
            productInv = Integer.parseInt(productInvField.getText());
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productInvField, warningLabel, "Inv Must Be A Number.");
            return;
        }
        try{
            productPrice = Double.parseDouble(productPriceField.getText());
        } catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productPriceField, warningLabel, "Price Must Be A Number.");
            return;
        }
        try{
            productMin = Integer.parseInt(productMinField.getText());
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productMinField, warningLabel, "Min Must Be A Number.");
            return;
        }   
        try{
            productMax = Integer.parseInt(productMaxField.getText());
        }catch(NumberFormatException nfe){
            kylegreeninventorysystem.Error.showError(productMaxField, warningLabel, "Max Must Be A Number.");
            return;
        } 
        
        // Validate total price of parts is less than price of product
        this.temporaryPartList.forEach((Part part)->{
            this.totalPartPrice += part.getPrice();
        });
        if(this.totalPartPrice > productPrice){
            kylegreeninventorysystem.Error.showError(productPriceField, warningLabel, "The product's price must be greater than the sum of the associated part's prices.");
            return;
        }
        
        // Valdate Inventory Level Is Between Min And Max
        if(!inputValidation.isValidInv(productInv, productMin, productMax)){
            kylegreeninventorysystem.Error.showError(productInvField, warningLabel, "Inventory level must be between minimum and maximum.");
            return;
        }           
        


        // Find index of selected Product and update with new information
        Product newProduct = new Product( Integer.parseInt(productIdField.getText()),productName, productPrice, productInv, productMin, productMax);
        
        this.temporaryPartList.forEach((Part part)->{
            newProduct.addAssociatedPart(part);
        });
        int index = Inventory.getAllProducts().indexOf(selectedProduct);
        Inventory.updateProduct(index, newProduct);
        
        // Return to main screen
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        stage.setScene(new Scene(scene));
        stage.show();      
    }

    @FXML
    private void handleCancel(MouseEvent event) throws IOException{
        // Confirm Cancel Action
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
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
