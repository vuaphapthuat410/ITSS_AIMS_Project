/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author vuaphapthuat410
 */
public class Order {
    private Integer id;
    private Integer userId;
    private String name;
    private String address;
    private String phoneNum;
    private Float total;
    private Integer trackingId;
    private Integer orderStatus;
    private Date date;
    private HashMap<Integer, Integer> items;

    public Order(Integer id, Integer userId, String name, String address, String phoneNum, Float total, Integer trackingId, Integer orderStatus, Date date) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.total = total;
        this.trackingId = trackingId;
        this.orderStatus = orderStatus;
        this.date = date;
        this.items = new HashMap<Integer, Integer>();
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Float getTotal() {
        return total;
    }

    public Integer getTrackingId() {
        return trackingId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public HashMap<Integer, Integer> getItems() {
        return items;
    }
    
    public void addItem(Integer itemId, Integer number) {
        items.put(itemId, number);
    }
    
}


