/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * An inventory object containing all parts and products used in a company. All members and methods are static and no Constructor is used.
 * @author KC Green
 */
public class Inventory {
    /**
     * Contains all of the parts that have been added to this Inventory
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * Contains all of the products that have been added to this Inventory
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * Tracks the current part id to ensure uniqueness
     */
    private static int currentPartId = 1;
    /**
     * Tracks the current product id to ensure uniqueness
     */
    private static int currentProductId = 2;
    
    private static boolean isAssociated = false;
    
    /**
    * Adds a  to this object's allParts member.
    * @param part The Part that will be added to this object's allParts member.
    */
    public static void addPart(Part part){
        allParts.add(part);
    }
    /**
    * Adds a Product to this object's allProducts member.
    * @param product The product that will be added to this object's allProducts member.
    */
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    /**
     * Searches this object's allParts member for a part with the matching id
     * @param partId An integer used to uniquely identify the Part
     * @return The Part with matching id
     */
    public static Part lookupPart(int partId){
        Part match = null;
        for(Part p : allParts){
            if(p.getId() == partId){
                match = p;
            }
        }
        return match;
    }
    /**
     * Searches this object's allProducts member for a product with the matching id
     * @param productId An integer used to uniquely identify the Product
     * @return The Product with matching id
     */
    public static Product lookupProduct(int productId){
        Product match = null;
        for(Product p : allProducts){
            if(p.getId() == productId){
                match = p;
            }
        }
        return match;
    }
    /**
     * Searches the object's allParts member for a Part with a name containing the search string
     * @param partName The String the user entered to search for a Part
     * @return A list of any Parts whose name contains the search String
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partMatches = FXCollections.observableArrayList();
        for(Part p : allParts){
            if(p.getName().toLowerCase().contains(partName.toLowerCase())){
                partMatches.add(p);
            }
        }
        return partMatches;
    }
    /**
     * Searches the object's allProducts member for a Product with a name containing the search string
     * @param productName The String the user entered to search for a Product
     * @return A list of any Products whose name contains the search String
     */
    public static ObservableList<Product> lookupProduct(String productName){
      ObservableList<Product> productMatches = FXCollections.observableArrayList();
      for(Product p : allProducts){
          if(p.getName().toLowerCase().contains(productName.toLowerCase())){
              productMatches.add(p);
          }
      }
      return productMatches;
    }
    /**
     * Updates the Part at the specified index with the specified Part
     * @param index The index of the Part to be updated
     * @param selectedPart The new Part that will replace the original Part
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    /**
     * Updates the Product at the specified index with the specified Product
     * @param index The index of the Product to be replaced
     * @param selectedProduct The new Product that will replace the original Product
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    /**
     * Deletes the specified Part
     * @param selectedPart The Part to be deleted
     * @return A boolean value of whether or not the delete was successful
     */
    public static boolean deletePart(Part selectedPart){
        // Check if part is associated with any product

        Inventory.getAllProducts().forEach((Product product)->{
            if(product.getAllAssociatedParts().contains(selectedPart)){
                isAssociated = true;
            }
        });
        if(isAssociated){
            return false;
        }
        return allParts.remove(selectedPart);
    }
    /**
     * Deletes the specified Product
     * @param selectedProduct The Product to be deleted
     * @return A boolean value of whether or not the delete was successful
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    /**
     * Gets a list of all the Parts in this Inventory
     * @return An ObservableList of all Parts in this Inventory
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /**
     * Gets a list of the Products in this Inventory
     * @return An ObservableList of all the Products in this Inventory
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    /**
     * Gets the current part id 
     * @return The currentPartId member
     */
    public static int getCurrentPartId(){
        return currentPartId;
    }
    /**
     * Gets the current product id
     * @return The currentProductId
     */
    public static int getCurrentProductId(){
        return currentProductId;
    }
    /**
     * Increments the currentPartId when a new Part is added
     */
    public static void incrementPartId(){
        currentPartId += 2;
    }
    /**
     * Increments the currentProductId when a new Product is added
     */
    public static void incrementProductId(){
        currentProductId += 2;
    }
}
