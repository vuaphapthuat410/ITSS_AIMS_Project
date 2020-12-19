/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Item;

/**
 *
 * @author vuaphapthuat410
 */
public class ItemDB {
    public static ArrayList<Item> getMixedItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT * from `item`;";
        ArrayList<Item> items = new ArrayList<Item>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                int value = rs.getInt(3);
                int price = rs.getInt(4);
                int unit_sale = rs.getInt(5);
                String category = rs.getString(6);

               
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return items;
    }
}
