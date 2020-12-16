package connectors;

import com.mysql.jdbc.PreparedStatement;
import models.PhysicalGood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItemHelper {
    public static int insertToItemAndPhysicalGood(PhysicalGood book){
        String itemQuery = "INSERT INTO `item` (`id`, `title`, `value`, `price`, `unit_sale`, `category`) VALUES (NULL, ?, ?, ?, ?, ?);";
        String physicalGoodQuery = "INSERT INTO `physical_good` (`item_id`, `barcode`, `description`, `quantity`, `date`, `dimension_x`, `dimension_y`, `dimension_z`, `weight`) VALUES (?, ?, ?, ? ,? ,? ,? ,? ,? );";
        int id = 0;

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(itemQuery,
                    Statement.RETURN_GENERATED_KEYS);

            //Insert to Item
            statement.setString(1, book.getTitle());
            statement.setString(2, Integer.toString(book.getValue()));
            statement.setString(3, Integer.toString(book.getPrice()));
            statement.setString(4, Integer.toString(book.getUnit_sale()));
            statement.setString(5, book.getCategory());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
//                System.out.println("A new item was inserted successfully!");
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
//                    System.out.println("created item's id: " + id);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            //insert to PhysicalGood
            statement = (PreparedStatement) connection.prepareStatement(physicalGoodQuery);
            statement.setString(1, Integer.toString(id));
            statement.setString(2, book.getBarcode());
            statement.setString(3, book.getDescription());
            statement.setString(4, Integer.toString(book.getQuantity()));
            statement.setString(5, book.getDate());
            statement.setString(6, Integer.toString(book.getDimension_x()));
            statement.setString(7, Integer.toString(book.getDimension_y()));
            statement.setString(8, Integer.toString(book.getDimension_z()));
            statement.setString(9, Integer.toString(book.getWeight()));
            rowsInserted = statement.executeUpdate();





        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return id;
    }



}
