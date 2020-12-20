/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import models.Order;

/**
 *
 * @author vuaphapthuat410
 */
public class OrderDB {
    public static ArrayList<Order> getAllOrderOfUser(Integer userId) {
        String sql = "SELECT `id`, `name`, `address`, `phone`, `total`, `tracking_id`, `order_status`, `date` FROM `order_table` WHERE `order_table`.`user_id` = ?";
        ArrayList<Order> orders = new ArrayList<Order>();
        
        try {
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                Integer id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phoneNum = rs.getString(4);
                Float total = rs.getFloat(5);
                Integer trackingId = rs.getInt(6);
                Integer orderStatus = rs.getInt(7);
                Date date = rs.getDate(8);
                
                Order order = new Order(id, userId, name, address, phoneNum, total, trackingId, orderStatus, date);
                // get all item of this order
                String getItemsSQL = "SELECT `item_id`, `quantity` FROM `order_item` WHERE `order_item`.`order_id` = ?";
                PreparedStatement itemsStatement = connection.prepareStatement(getItemsSQL);
                itemsStatement.setInt(1, id);
                ResultSet itemsMap = itemsStatement.executeQuery();
                
                while(itemsMap.next()) {
                    Integer itemId = itemsMap.getInt(1);
                    Integer number = itemsMap.getInt(2);
                    
                    order.addItem(itemId, number);
                }
                // add order to order list
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return orders;
    }
}
