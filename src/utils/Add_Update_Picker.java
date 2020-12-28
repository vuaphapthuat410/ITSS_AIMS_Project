/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import models.Item;

/**
 *
 * @author vuaphapthuat410
 */
public class Add_Update_Picker {
    private static Integer mode;
    private static Item item;
    
    public static void setMode(Integer newMode) {
        mode = newMode;
    }
    
    public static Integer getMode() {
        return mode;
    }
    
    public static void setItem(Item anItem) {
        item = anItem;
    }
    
    public static Item getItem() {
        return item;
    }
}
