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
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KC Green
 */
public class MainScreenController implements Initializable {

    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private Button partAddButton;
    @FXML
    private Button partModifyButton;
    @FXML
    private Button partDeleteButton;
    @FXML
    private TextField productSearchField;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private Button productAddButton;
    @FXML
    private Button productModifyButton;
    @FXML
    private Button productDeleteButton;
    @FXML
    private Button appExitButton;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInvCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    
    private ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productsInventory = FXCollections.observableArrayList();
    
    Stage stage;
    Parent scene;
    Part selectedPart;
    Product selectedProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Generate tables on initialize
        generatePartsTable();
        generateProductsTable();
        // Prevent default focus on part search field
        partSearchField.setFocusTraversable(false);
    }    
    
    /**
     * Fills the Parts TableView with all Parts in the Inventory class
     */
    private void generatePartsTable(){
        partsInventory.setAll(Inventory.getAllParts());
        
        // Set PropertyValueFactory to match class members with columns
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable.setItems(partsInventory);
        partsTable.refresh();
    }
    
    /**
     * Fills the Products TableView with all Products in the Inventory class
     */
    private void generateProductsTable(){
        productsInventory.setAll(Inventory.getAllProducts());
        
        // Set PropertyValueFactory to match class members with columns
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTable.setItems(productsInventory);
        productsTable.refresh();
    }
    
    /**
     * Searches for a matching Part when the user types in the part search TextField.
     * <p>Uses the overloaded lookupPart method in the Inventory class, based on the type of data the user enters.</p>
     * @param event The keyPressed event
     */
    @FXML
    private void handleSearchPart(KeyEvent event) {
        String searchInput = partSearchField.getText();
        // Try to search by ID if input is an integer, otherwise search by name
        try{
            partsInventory.setAll(Inventory.lookupPart(Integer.parseInt(searchInput)));
        }catch(NumberFormatException nfe){
            partsInventory.setAll(Inventory.lookupPart(searchInput));
        }

        partsTable.setItems(partsInventory);
        partsTable.refresh();
    }
    /**
     * Opens the AddPart screen, allowing the user to enter the information of a new Part
     * @param event The mouseClicked event
     * @throws IOException 
     */
    @FXML
    private void handleAddPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        
        stage.setScene(new Scene(scene));
        stage.show();      
    }

    /**
     * Opens the ModifyPart screen, allowing the modification of the part selected from the Part TableView
     * @param event The mouseClicked event
     * @throws IOException 
     */
    @FXML
    private void handleModifyPart(MouseEvent event) throws IOException{
        // Pass the selected part to the Modify Part Screen
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        
        // Tell User If No Part Is Selected
        if(selectedPart == null){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set("No part selected.");
            selectPartWarning.showAndWait();
            return;
        }
        
        // Open Modify Part Screen for Selected Part
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyPart.fxml"));
        loader.load();
        
        ModifyPartController MPController = loader.getController();
        MPController.sendPart(selectedPart);
                
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes the Part selected from the Part TableView
     * @param event The mouseClicked event
     */
    @FXML
    private void handleDeletePart(MouseEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        // Tell User If No Part Is Selected
        if(selectedPart == null){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set("No part selected.");
            selectPartWarning.showAndWait();
            return;
        }
        // Confirm Delete with User
        Alert deletePartAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deletePartAlert.headerTextProperty().set("Are you sure you want to permanently delete " + selectedPart.getName() + "?");
        Optional<ButtonType> result = deletePartAlert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            // Find selected part and delete
            Inventory.deletePart(selectedPart);
        
            // Refresh Table
            partsInventory.setAll(Inventory.getAllParts());
            partsTable.setItems(partsInventory);
            partsTable.refresh();
        }
    }
    /**
     * Searches for a matching Product when the user types in the product search TextField.
     * <p>Uses the overloaded lookupProduct method in the Inventory class, based on the type of data the user enters.</p>
     * @param event The keyPressed event
     */
    @FXML
    private void handleSearchProduct(KeyEvent event) {
        String searchInput = productSearchField.getText();
        // Try to search by ID if input is an integer, otherwise search by name
        try{            
            productsInventory.setAll(Inventory.lookupProduct(Integer.parseInt(searchInput)));
        }catch(NumberFormatException nfe){
            productsInventory.setAll(Inventory.lookupProduct(searchInput));
        }
        
        productsTable.setItems(productsInventory);
        productsTable.refresh();
    }
    /**
     * Opens the AddProduct screen, allowing the user to enter the information of a new Product
     * @param event The mouseClicked event
     * @throws IOException 
     */
    @FXML
    private void handleAddProduct(MouseEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * Opens the ModifyProduct screen, allowing the modification of the part selected from the Product TableView
     * @param event The mouseClicked event
     * @throws IOException 
     */
    @FXML
    private void handleModifyProduct(MouseEvent event) throws IOException{
        // Pass the selected part to the Modify Part Screen
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        
        // Tell User If No Part Is Selected
        if(selectedProduct == null){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set("No product selected.");
            selectPartWarning.showAndWait();
            return;
        }
        
        // Open Modify Part Screen for Selected Part
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
        loader.load();
        
        ModifyProductController MPController = loader.getController();
        MPController.sendProduct(selectedProduct);
                
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * Deletes the Product selected from the Product TableView
     * @param event The mouseClicked event
     */
    @FXML
    private void handleDeleteProduct(MouseEvent event) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        // Tell User If No Part Is Selected
        if(selectedProduct == null){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set("No product selected.");
            selectPartWarning.showAndWait();
            return;
        }
        // Confirm Delete with User
        Alert deleteProductAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteProductAlert.headerTextProperty().set("Are you sure you want to permanently delete " + selectedPart.getName() + "?");
        Optional<ButtonType> result = deleteProductAlert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            // Find selected part and delete
            Inventory.deleteProduct(selectedProduct);
        
            // Refresh Table
            productsInventory.setAll(Inventory.getAllProducts());
            productsTable.setItems(productsInventory);
            productsTable.refresh();
        }
    }

    /**
     * Closes the Java application
     * @param event The mouseClicked event
     */
    @FXML
    private void handleExitApp(MouseEvent event) {
        Platform.exit();
    }
    
}
