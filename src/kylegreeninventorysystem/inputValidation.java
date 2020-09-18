/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylegreeninventorysystem;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kcgre
 */
public class inputValidation {
    /*
    Validate the inventory level is between the min and max
    @param inv The number of items in stock
    @param min The minimum number of items that can be in stock
    @param max The maximum number of items that can be in stock
    @return boolean Whether the inv, min and max are logically valid
    */
    public static boolean isValidInv(int inv, int min, int max){
        return inv >= min && inv <= max && min < max;        
    }
    
    public static boolean isDouble(String input) throws Exception{
        try{
            Double.parseDouble(input);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    public static boolean isInteger(String input) throws Exception{
        try{
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
}
