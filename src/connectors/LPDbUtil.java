package connectors;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import connectors.helper.AddItemHelper;
import connectors.helper.UpdateItemHelper;
import models.LP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class LPDbUtil {
    public static List<LP> getAllItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT `lp`.*, `physical_good`.*, `item`.* FROM `lp` LEFT JOIN `physical_good` ON `lp`.`item_id` = `physical_good`.`item_id` LEFT JOIN `item` ON `physical_good`.`item_id` = `item`.`id`;";
        ArrayList<LP> lp = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                String artist = rs.getString(2);
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




                LP b = new LP(
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
                        artist,
                        record_label,
                        publication_date,
                        genre
                );

                lp.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return lp;
    }


    public static boolean addItem(LP lp) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `lp` (`item_id`, `artist`, `record_label`, `publication_date`, `genre`) VALUES (?, ?, ?, ?, ?);";
        // insert to Item and PhysicalGood
        int id = AddItemHelper.insertToItemAndPhysicalGood(lp);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(id));
            statement.setString(2, lp.getArtist());
            statement.setString(3, lp.getRecord_label());
            statement.setString(4, lp.getPublication_date());
            statement.setString(5, lp.getGenre());
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

    public static boolean updateItem(LP item) throws ClassNotFoundException, SQLException{


        String query = "UPDATE `lp` SET `artist` = ?, `record_label` = ?, `publication_date` = ?, `genre` = ? WHERE `lp`.`item_id` = ?";
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
}
