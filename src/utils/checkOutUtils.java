/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.Item;

/**
 *
 * @author vuaphapthuat410
 */
public class checkOutUtils {
    private static ArrayList<String> cities = new ArrayList<String>();
    public static HashMap<Item,Float> totalPrice = new HashMap<>();
    
    public static void generateCity() {
        cities.add("HaNoi");
        cities.add("TPHCM");
        cities.add("ThaiBinh");
        cities.add("HaiPhong");
    }

    public static ArrayList<String> getCities() {
        return cities;
    }

    public static void setCities(ArrayList<String> cities) {
        checkOutUtils.cities = cities;
    }

    public static HashMap<Item, Float> getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(HashMap<Item, Float> ttprice) {
       totalPrice = ttprice;
    }
    
    public static Float getTotalPriceInFloat() {
        Float total = 0f;
        for(Map.Entry<Item, Float> item : totalPrice.entrySet()) {
            total = total + item.getValue();
        }
        return total;
    }
    
}
