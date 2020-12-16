package connectors.helper;

import java.sql.PreparedStatement;
import connectors.ConnDB;
import models.PhysicalGood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateItemHelper {
    public static boolean updateItemAndPhysicalGood(PhysicalGood item){
        String itemQuery = "UPDATE `item` SET `title` = ?, `value` = ?, `price` = ?, `unit_sale` = ?, `category` = ? WHERE `item`.`id` = ?;";
        String physicalGoodQuery = "UPDATE `physical_good` SET  `barcode` = ?, `description` = ?, `quantity` = ?, `date` = ?, `dimension_x` = ?, `dimension_y` = ?, `dimension_z` = ?, `weight` = ? WHERE `physical_good`.`item_id` = ?;";

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(itemQuery);

            //Insert to Item
            statement.setString(1, item.getTitle());
            System.out.println(item.getTitle());
            statement.setString(2, Integer.toString(item.getValue()));
            System.out.println(item.getValue());
            statement.setString(3, Integer.toString(item.getPrice()));
            System.out.println(item.getPrice());
            statement.setString(4, Integer.toString(item.getUnit_sale()));
            System.out.println(item.getUnit_sale());
            statement.setString(5, item.getCategory());
            System.out.println(item.getCategory());
            statement.setString(6, Integer.toString(item.getId()));
            System.out.println(item.getId());
            System.out.println(statement);


            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new item was updated successfully!");
            } else {
                return false;
            }

            //insert to PhysicalGood
            statement = (PreparedStatement) connection.prepareStatement(physicalGoodQuery);
            statement.setString(1, item.getBarcode());
            statement.setString(2, item.getDescription());
            statement.setString(3, Integer.toString(item.getQuantity()));
            statement.setString(4, item.getDate());
            statement.setString(5, Integer.toString(item.getDimension_x()));
            statement.setString(6, Integer.toString(item.getDimension_y()));
            statement.setString(7, Integer.toString(item.getDimension_z()));
            statement.setString(8, Integer.toString(item.getWeight()));
            statement.setString(9, Integer.toString(item.getId()));

            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new item was updated successfully!");
            } else {
                return false;
            }


        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }
        return true;
    }
}
