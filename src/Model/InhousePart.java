/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * InhousePart inherits from the Part base class. InhouseParts contain all of the Part data members plus a machineID, representing the ID of the machine used to make the part.
 * @see Part
 * @author kcgre
 */
public class InhousePart extends Part {
    private int machineId;

    /**
    * <p>Class Constructor</p>
    * Creates an OutsourcedPart with the specified details
    * @param id The unique integer used to identify the part
    * @param name The name used to refer to the part
    * @param price The price per unit
    * @param stock The quantity currently on hand
    * @param min The minimum number of units required
    * @param max The maximum number of units allowed
    * @param machineId The ID number of the machine used to build the part
    */          
    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /**
     * Sets the id number of the machine used to build the part
     * @param machineId The id number of the machine used to build the part
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    /**
     * Returns the id number of the machine used to build the part
     * @return The id number of the machine used to build the part
     */
    public int getMachineId(){
        return this.machineId;
    }
    
}
