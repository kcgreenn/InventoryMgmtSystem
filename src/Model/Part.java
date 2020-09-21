/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Part is the abstract base class for all parts that will be in the Inventory System. A Part object contains the information necessary for company operations.
 * The InhousePart and OutsourcedPart classes inherit from the Part class.
 * <p>This information includes:</p>
 * <ul>
 * <li>The unique ID number of the part</li>
 * <li>The name of the part</li>
 * <li>The price of the part</li>
 * <li>The quantity of this part currently on hand</li>
 * <li>The minimum quantity needed for this part</li>
 * <li>The maximum quantity allowed for this part</li>
 * </ul>
 * @see InhousePart
 * @see OutsourcedPart
 * @author KC Green
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    /**
    * <p>Class Constructor</p>
    * Creates a Part with the specified details
    * @param id The unique integer used to identify the part
    * @param name The name used to refer to the part
    * @param price The price per unit
    * @param stock The quantity currently on hand
    * @param min The minimum number of units required
    * @param max The maximum number of units allowed
    */
    public Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**
     * Sets this Part's id
     * @param id The unique identifier of this Part
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Returns the unique identifier of this Part
     * @return The unique ID of this Part
     */
    public int getId(){
        return this.id;
    }
    /**
     * Sets this Part's name
     * @param name The name used for this Part
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Return this Part's name
     * @return This Part's name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Sets the price of this Part
     * @param price The price of this Part
     */
    public void setPrice(double price){
        this.price = price;
    }
    /**
     * Returns the price of this Part
     * @return the price of this Part
     */
    public double getPrice(){
        return this.price;
    }
    /**
     * Sets the quantity of this Part currently in stock
     * @param stock The quantity of this Part currently in stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }
    /**
     * Return the number of units of this Part currently in stock
     * @return The quantity currently on hand of this Part
     */
    public int getStock(){
        return this.stock;
    }
    /**
     * Sets the required minimum quantity of this Part
     * @param min The minimum required quantity of this Part
     */    
    public void setMin(int min){
        this.min = min;
    }
    /**
     * Return the required minimum quantity of this Part
     * @return the required minimum quantity of this Part
     */
    public int getMin(){
        return this.min;
    }
    /**
     * Sets the maximum allowed quantity of this Part
     * @param max The maximum allowed quantity of this Part
     */
    public void setMax(int max){
        this.max = max;
    }
    /**
     * Returns the maximum allowed quantity of this Part
     * @return The maximum allowed quantity of this Part
     */
    public int getMax(){
        return this.max;
    }
}
