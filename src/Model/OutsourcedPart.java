/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * OutsourcedPart inherits from the Part base class. OutsourcedParts contain all of the base data members of the Part class, plus the name of the company that built the part.
 * @see Part
 * @author KC Green
 */
public class OutsourcedPart extends Part{
    private String companyName;

    /**
    * <p>Class Constructor</p>
    * Creates an OutsourcedPart with the specified details
    * @param id The unique integer used to identify the part
    * @param name The name used to refer to the part
    * @param price The price per unit
    * @param stock The quantity currently on hand
    * @param min The minimum number of units required
    * @param max The maximum number of units allowed
    * @param companyName The name of the company that built the part
    */    
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /**
     * Sets that name of the company that built the outsourced part
     * @param companyName The name of the company that built the outsourced part
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    /**
     * Returns the name of the company that built the outsourced part
     * @return The name of the company that built the outsourced part
     */
    public String getCompanyName(){
        return this.companyName;
    }
}
