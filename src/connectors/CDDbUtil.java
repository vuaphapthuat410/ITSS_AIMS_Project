package connectors;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import connectors.helper.AddItemHelper;
import connectors.helper.DeleteItemHelper;
import connectors.helper.UpdateItemHelper;
import models.CD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;



public class CDDbUtil {
    public static List<CD> getAllItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT `cd`.*, `physical_good`.*, `item`.* FROM `cd` LEFT JOIN `physical_good` ON `cd`.`item_id` = `physical_good`.`item_id` LEFT JOIN `item` ON `physical_good`.`item_id` = `item`.`id`";
        ArrayList<CD> cd = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                String artists = rs.getString(2);
                String record_label = rs.getString(3);
                String publication_date = rs.getString(4);
                String genre = rs.getString(5);
                String barcode = rs.getString(7);
                String description = rs.getString(8);
                int quantity = rs.getInt(9);
                String date = rs.getString(10);
                int dimension_x = rs.getInt(11);
                int dimension_y = rs.getInt(12);
                int dimension_z = rs.getInt(13);
                int weight = rs.getInt(14);
                String title = rs.getString(16);
                int value = rs.getInt(17);
                int price = rs.getInt(18);
                int unit_sale = rs.getInt(19);
                String category = rs.getString(20);




                CD b = new CD(
                        id,
                        title,
                        value,
                        price,
                        unit_sale,
                        category,
                        barcode,
                        description,
                        quantity,
                        date,
                        dimension_x,
                        dimension_y,
                        dimension_z,
                        weight,
                        artists,
                        record_label,
                        publication_date,
                        genre
                );

                cd.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return cd;
    }


    public static boolean addItem(CD cd) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `cd` (`item_id`, `artist`, `record_label`, `publication_date`, `genre`) VALUES (?, ?, ?, ?, ?);";
        // insert to Item and PhysicalGood
        int id = AddItemHelper.insertToItemAndPhysicalGood(cd);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(id));
            statement.setString(2, cd.getArtist());
            statement.setString(3, cd.getRecord_label());
            statement.setString(4, cd.getPublication_date());
            statement.setString(5, cd.getGenre());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }





        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateItem(CD item) throws ClassNotFoundException, SQLException{


        String query = "UPDATE `cd` SET `artist` = ?, `record_label` = ?, `publication_date` = ?, `genre` = ? WHERE `cd`.`item_id` = ?";
        // insert to Item and PhysicalGood
        boolean result = UpdateItemHelper.updateItemAndPhysicalGood(item);
        if (!result){
            return false;
        }

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, item.getArtist());
            statement.setString(2, item.getRecord_label());
            statement.setString(3, item.getPublication_date());
            statement.setString(4, item.getGenre());
            statement.setString(5, Integer.toString(item.getId()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }


        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteItem(int id) throws SQLException, ClassNotFoundException {

        String query = "DELETE FROM `cd` WHERE `cd`.`item_id` = ?";

        DeleteItemHelper.deleteItemAndPhysicalGood(id);

        try {

            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, Integer.toString(id));
            int rowsDeleted = statement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
