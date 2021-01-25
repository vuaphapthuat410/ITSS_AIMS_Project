/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;
import models.Item;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author vuaphapthuat410
 */
public class ItemDbUtil {
    public static Item getItemByID(Integer id) throws ClassNotFoundException, SQLException {

        String query = "SELECT `id`, `title`, `value`, `price`, `unit_sale`, `category` FROM `item` WHERE `id` = ?";

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
            
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
                String tempTitle = rs.getString(2);
                Integer tempValue = rs.getInt(3);
                Integer tempPrice = rs.getInt(4);
                Integer tempUnitSale = rs.getInt(5);
                String tempCat = rs.getString(6);

                connection.close();
                return new Item(tempTitle, tempValue, tempPrice, tempUnitSale, tempCat);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return null;
    }
}
