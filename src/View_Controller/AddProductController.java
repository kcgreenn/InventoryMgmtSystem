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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kcgre
 */
public class AddProductController implements Initializable {

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
    private TableView<?> partSearchTable;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<?> associatedPartsTable;
    @FXML
    private Button addPartButton;
    @FXML
    private Button removePartButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleProductNameInput(KeyEvent event) {
    }

    @FXML
    private void handleProductPriceInput(KeyEvent event) {
    }

    @FXML
    private void handleProductInvInput(KeyEvent event) {
    }

    @FXML
    private void handleProductMinInput(KeyEvent event) {
    }

    @FXML
    private void handleProductMaxInput(KeyEvent event) {
    }

    @FXML
    private void handlePartSearchInput(KeyEvent event) {
    }

    @FXML
    private void handleRemovePart(MouseEvent event) {
    }

    @FXML
    private void handleSave(MouseEvent event) {
    }

    @FXML
    private void handleCancel(MouseEvent event) {
    }

    @FXML
    private void handleAddPart(MouseEvent event) {
    }
    
}
