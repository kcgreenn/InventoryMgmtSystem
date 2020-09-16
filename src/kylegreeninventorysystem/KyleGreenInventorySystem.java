/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylegreeninventorysystem;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kcgre
 */
public class KyleGreenInventorySystem extends Application {
    
    @Override
    public void init() throws Exception {
        
    // Generate test data
        addTestData();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    // Generate Data for Program
    void addTestData(){
        
        // Add Inhouse parts
         InhousePart a1 = new InhousePart(Inventory.getCurrentPartId(), "Part A1", 2.99, 10, 5, 100, 101);
         Inventory.incrementPartId();
         InhousePart a2 = new InhousePart(Inventory.getCurrentPartId(), "Part A2", 4.99, 10, 12, 100, 102);
         Inventory.incrementPartId();
         InhousePart a3 = new InhousePart(Inventory.getCurrentPartId(), "Part A3", 7.99, 15, 5, 10, 103);
         Inventory.incrementPartId();
         
         Inventory.addPart(a1);
         Inventory.addPart(a2);
         Inventory.addPart(a3);
         
         Inventory.addPart(new InhousePart(Inventory.getCurrentPartId(), "Part A4", 3.99, 8, 5, 10, 102));
         Inventory.incrementPartId();
         Inventory.addPart(new InhousePart(Inventory.getCurrentPartId(), "Part A5", 11.99, 10, 15, 25, 104));
         Inventory.incrementPartId();
        
        // Add Outsourced Parts
        OutsourcedPart b1 = new OutsourcedPart(Inventory.getCurrentPartId(), "Part B1", 1.99, 15, 5, 100, "ACME");
        Inventory.incrementPartId();
        OutsourcedPart b2 = new OutsourcedPart(Inventory.getCurrentPartId(), "Part B2", 8.99, 20, 5, 100, "GE");
        Inventory.incrementPartId();
        OutsourcedPart b3 = new OutsourcedPart(Inventory.getCurrentPartId(), "Part B3", 13.99, 50, 5, 30, "TSMC");
        Inventory.incrementPartId();
        
        // Add Parts to Inventory
        Inventory.addPart(b1);
        Inventory.addPart(b2);
        Inventory.addPart(b3);
        
        Inventory.addPart(new OutsourcedPart(Inventory.getCurrentPartId(), "Part B4", 0.99, 10, 5, 20, "ACME"));
        Inventory.incrementPartId();
        Inventory.addPart(new OutsourcedPart(Inventory.getCurrentPartId(), "Part B5", 1.49, 10, 1, 25, "GE"));
        Inventory.incrementPartId();
        
        // Add Products
        Product c1 = new Product(Inventory.getCurrentProductId(), "Product C1", 13.99, 10, 5, 100);
        Inventory.incrementProductId();
        Product c2 = new Product(Inventory.getCurrentProductId(), "Product C2", 3.99, 10, 5, 100);
        Inventory.incrementProductId();
        Product c3 = new Product(Inventory.getCurrentProductId(), "Product C3", 23.99, 4, 5, 100);
        Inventory.incrementProductId();
        
        // Add Associated Parts to Products
        c1.addAssociatedPart(a1);
        c1.addAssociatedPart(a2);
        c2.addAssociatedPart(a3);
        c3.addAssociatedPart(b2);

        // Add Products to Inventory
        Inventory.addProduct(c1);
        Inventory.addProduct(c2);
        Inventory.addProduct(c3);
        Inventory.addProduct(new Product(Inventory.getCurrentProductId(), "Product C4", 12.99, 10, 5, 90));
        Inventory.incrementProductId();
        Inventory.addProduct(new Product(Inventory.getCurrentProductId(), "Product C5", 22.49, 96, 5, 90));        
        Inventory.incrementProductId();
    }
    
}
