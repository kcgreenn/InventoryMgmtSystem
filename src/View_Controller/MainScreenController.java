/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kcgre
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Generate tables on initialize
        generatePartsTable();
        generateProductsTable();
    }    
    
    // Fill parts tableview with parts data
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
    
    // Fill products table view with product data
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

    @FXML
    private void handleAddPart(MouseEvent event) {
        // TODO open add part scene
        
    }

    @FXML
    private void handleModifyPart(MouseEvent event) {
        // TODO open modify part scene
    }

    @FXML
    private void handleDeletePart(MouseEvent event) {
        // Find selected part and delete
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart);
        
        // Refresh Table
        partsInventory.setAll(Inventory.getAllParts());
        partsTable.setItems(partsInventory);
        partsTable.refresh();
    }

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

    @FXML
    private void handleAddProduct(MouseEvent event) {
        // TODO open add product scene
    }

    @FXML
    private void handleModifyProduct(MouseEvent event) {
        // TODO open modify product scene
    }

    @FXML
    private void handleDeleteProduct(MouseEvent event) {
        // Find and delete selected product
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(selectedProduct);
        // Refresh table
        productsInventory.setAll(Inventory.getAllProducts());
        productsTable.setItems(productsInventory);
        productsTable.refresh();
    }

    @FXML
    private void handleExitApp(MouseEvent event) {
        Platform.exit();
    }
    
}
