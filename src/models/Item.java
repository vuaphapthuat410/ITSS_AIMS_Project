/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vuaphapthuat410
 */
public abstract class Item {
    protected String itemId;
    protected String title;
    protected Integer value;
    protected Integer price;
    protected Integer unitSale;

    public Item(String itemId, String title, Integer value, Integer price, Integer unitSale) {
        this.itemId = itemId;
        this.title = title;
        this.value = value;
        this.price = price;
        this.unitSale = unitSale;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    protected void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPrice() {
        return price;
    }

    protected void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getUnitSale() {
        return unitSale;
    }

    protected void setUnitSale(Integer unitSale) {
        this.unitSale = unitSale;
    }
    
}
