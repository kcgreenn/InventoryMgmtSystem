/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product is the class for all products that will be in the Inventory System. A Product object contains the information necessary for company operations.
 * <p>This information includes:</p>
 * <ul>
 * <li>The unique ID number of the product</li>
 * <li>The name of the product</li>
 * <li>The price of the product</li>
 * <li>The quantity of this product currently on hand</li>
 * <li>The minimum quantity needed for this product</li>
 * <li>The maximum quantity allowed for this product</li>
 * </ul>
 * @author KC Green
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    /**
    * <p>Class Constructor</p>
    * Creates a Product with the specified details
    * @param id The unique integer used to identify the product
    * @param name The name used to refer to the product
    * @param price The price per unit
    * @param stock The quantity currently on hand
    * @param min The minimum number of units required
    * @param max The maximum number of units allowed
    */    
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public Product(){}
    /**
     * Sets this Product's unique identification number
     * @param id The unique identification number of this product
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Sets this Product's name
     * @param name The name of this Product
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Sets the price of this Product
     * @param price The price of this Product
     */
    public void setPrice(double price){
        this.price = price;
    }
    /**
     * Sets the current quantity of this Product
     * @param stock The current quantity of this Product
     */
    public void setStock(int stock){
        this.stock = stock;
    }
    /**
     * Sets the required minimum quantity of this Product
     * @param min The required minimum quantity of this Product
     */
    public void setMin(int min){
        this.min = min;
    }
    /**
     * Sets the maximum allowed quantity of this Product
     * @param max The maximum allowed quantity of this Product
     */
    public void setMax(int max){
        this.max = max;
    }
    /**
     * Gets the unique identification number of this Product
     * @return The Product ID
     */
    public int getId(){
        return this.id;
    }
    /**
     * Gets the name of this Product
     * @return The Product name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Gets the price of this Product
     * @return The Product price
     */
    public double getPrice(){
        return this.price;
    }
    /**
     * Gets the current quantity on hand of this Product
     * @return The Product stock
     */
    public int getStock(){
        return this.stock;
    }
    /**
     * Gets the required minimum quantity of this Product
     * @return The Product min
     */
    public int getMin(){
        return this.min;
    }
    /**
     * Gets the maximum allowed quantity of this Product
     * @return The Product max
     */
    public int getMax(){
        return this.max;
    }
    /**
     * Adds a Part to this Product's associatedParts member
     * @param part The Part to be associated with this Product
     */
    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }
    /**
     * Removes a Part from this Product's associatedParts member
     * @param selectedPart The Part to be removed
     * @return A boolean value of the success of the deletion
     */
    public boolean deleteAssociatedPart(Part selectedPart){
        return this.associatedParts.remove(selectedPart);
    }
    /**
     * Gets a list of all Parts that have been associated with this Product
     * @return An ObservableList of Parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}
