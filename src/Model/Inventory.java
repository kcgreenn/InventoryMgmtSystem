/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kcgre
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int currentPartId = 1;
    private static int currentProductId = 2;
    
    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public static Part lookupPart(int partId){
        Part match = null;
        for(Part p : allParts){
            if(p.getId() == partId){
                match = p;
            }
        }
        return match;
    }
    public static Product lookupProduct(int productId){
        Product match = null;
        for(Product p : allProducts){
            if(p.getId() == productId){
                match = p;
            }
        }
        return match;
    }
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partMatches = FXCollections.observableArrayList();
        for(Part p : allParts){
            if(p.getName().toLowerCase().contains(partName.toLowerCase())){
                partMatches.add(p);
            }
        }
        return partMatches;
    }
    public static ObservableList<Product> lookupProduct(String productName){
      ObservableList<Product> productMatches = FXCollections.observableArrayList();
      for(Product p : allProducts){
          if(p.getName().toLowerCase().contains(productName.toLowerCase())){
              productMatches.add(p);
          }
      }
      return productMatches;
    }
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    public static int getCurrentPartId(){
        return currentPartId;
    }
    public static int getCurrentProductId(){
        return currentProductId;
    }
    public static void incrementPartId(){
        currentPartId += 2;
    }
    public static void incrementProductId(){
        currentProductId += 2;
    }
    /*
    Validate the inventory level is between the min and max
    @param inv The number of items in stock
    @param min The minimum number of items that can be in stock
    @param max The maximum number of items that can be in stock
    @return Whether the inv is valid
    */
    public static boolean validateInvLevel(int inv, int min, int max){
        return inv > min && inv < max;
    }
}
