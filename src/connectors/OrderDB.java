/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import models.Item;
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
    
    public static boolean createOrder(Order order, HashMap<Item, Integer> items) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO `order_table`(`user_id`, `name`, `address`, `phone`, `total`, `tracking_id`, `order_status`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        String query2 = "INSERT INTO `order_item`(`order_id`, `item_id`, `quantity`) VALUES (?,?,?)";
        // insert to Item and PhysicalGood
        int id = 0;
        
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getName());
            statement.setString(3, order.getAddress());
            statement.setString(4, order.getPhoneNum());
            statement.setFloat(5, order.getTotal());
            statement.setInt(6, order.getTrackingId());
            statement.setInt(7, order.getOrderStatus());
            statement.setDate(8, order.getDate());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if(keys.next())
                    id = keys.getInt(1);
                else {
                    throw new SQLException("Cannot create record order_item");
                }                
            }
            else 
                return false;
            
            for(Map.Entry<Item,Integer> item : items.entrySet()) {
                PreparedStatement otherPreparedStatement = connection.prepareStatement(query2);
                otherPreparedStatement.setInt(1, id);
                otherPreparedStatement.setInt(2, item.getKey().getId());
                otherPreparedStatement.setInt(3, item.getValue());
                int row = otherPreparedStatement.executeUpdate();
                if(row < 0)
                    return false;
            }
            
            return true;
            
        } catch (Exception e) {
            System.out.print("Cant create order");
            e.printStackTrace();
        }

        return false;
    }
    
    public static boolean deleteOrder(Integer orderId) {
        String query = "DELETE FROM `order_table` WHERE `id` = ?";
        String query2 = "DELETE FROM `order_item` WHERE `order_id` = ?";
        
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);

            statement.setInt(1, orderId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted < 0) 
                return false;
            
            PreparedStatement other = connection.prepareStatement(query2);
            
            other.setInt(1, orderId);
            rowsDeleted = other.executeUpdate();
            if (rowsDeleted > 0) 
                return true;
            
        } catch (Exception e) {
            System.out.print("Cant delete order");
            e.printStackTrace();
        }

        return false;
    } 
    
    public static boolean cancelOrder(Integer orderId) {
        String query = "UPDATE `order_table` SET `order_status`= 0 WHERE `order_table`.`id` = ?";
        
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);

            statement.setInt(1, orderId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) 
                return true;
            
        } catch (Exception e) {
            System.out.print("Cant cancel order");
            e.printStackTrace();
        }

        return false;
    } 
}
