/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
<<<<<<< HEAD
 * @author Vinh
 */
public class Item {
    private int id;
    private String title;
    private int value;
    private int price;
    private int unit_sale;
    private String category;

    public Item(int id, String title, int value, int price, int unit_sale, String category) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.price = price;
        this.unit_sale = unit_sale;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnit_sale() {
        return unit_sale;
    }

    public void setUnit_sale(int unit_sale) {
        this.unit_sale = unit_sale;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
