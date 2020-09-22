/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylegreeninventorysystem;


/**
 * A class to validate user input into text fields, before it is added to the inventory.
 * @author KC Green
 */
public class inputValidation {
    /*
    Validate the inventory level is between the min and max
    @param inv The number of items in stock
    @param min The minimum number of items that can be in stock
    @param max The maximum number of items that can be in stock
    @return boolean Whether the inv, min and max are logically valid
    */

    /**
     * Determines if the inv, min and max inputs are valid.
     * @param inv The current quantity in stock
     * @param min The minimum quantity required
     * @param max The maximum quantity allowed
     * @return Boolean value if the inputs are valid
     */

    public static boolean isValidInv(int inv, int min, int max){
        return inv >= min && inv <= max && min < max;        
    }
    
    /**
     * Determines if min input is less than max.
     * @param min The minimum quantity required
     * @param max The maximum quantity allowed
     * @return Boolean value if the inputs are valid
     */
    public static boolean isValidMin(int min, int max){
        return min < max;
    }
    
    /**
     * Determines if the input is a Double
     * @param input The user input into the price field
     * @return Whether the input is a double
     * @throws Exception If the input is not a double, throws NumberFormatException
     */
    public static boolean isDouble(String input) throws Exception{
        try{
            Double.parseDouble(input);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Determines if the input is an Integer
     * @param input The user input into text fields
     * @return Whether the input is an integer
     * @throws Exception If the input is not an integer, throws NumberFormatException
     */
    public static boolean isInteger(String input) throws Exception{
        try{
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
}
