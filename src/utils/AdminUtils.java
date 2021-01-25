/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import models.Item;

/**
 *
 * @author vuaphapthuat410
 */
public class AdminUtils {
    private static ArrayList<Item> items = null;

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        AdminUtils.items = items;
    }
    
    public static Item getItemById(Integer id) {
        for(Item item : items) {
            if(item.getId() == id)
                return item;
        }
        
        return null;
    }
}
