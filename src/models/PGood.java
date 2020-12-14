/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author vuaphapthuat410
 */
public abstract class PGood extends Item{
    protected String barCode;
    protected String desc;
    protected Integer quantity;
    protected Date date;
    protected Integer dimX;
    protected Integer dimY;
    protected Integer weight;

    public PGood(String barCode, String desc, Integer quantity, Date date, Integer dimX, Integer dimY, Integer weight, String itemId, String title, Integer value, Integer price, Integer unitSale) {
        super(itemId, title, value, price, unitSale);
        this.barCode = barCode;
        this.desc = desc;
        this.quantity = quantity;
        this.date = date;
        this.dimX = dimX;
        this.dimY = dimY;
        this.weight = weight;
    }

    public String getBarCode() {
        return barCode;
    }

    protected void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDesc() {
        return desc;
    }

    protected void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    protected void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    protected void setDate(Date date) {
        this.date = date;
    }

    public Integer getDimX() {
        return dimX;
    }

    protected void setDimX(Integer dimX) {
        this.dimX = dimX;
    }

    public Integer getDimY() {
        return dimY;
    }

    protected void setDimY(Integer dimY) {
        this.dimY = dimY;
    }

    public Integer getWeight() {
        return weight;
    }

    protected void setWeight(Integer weight) {
        this.weight = weight;
    }
    
}
