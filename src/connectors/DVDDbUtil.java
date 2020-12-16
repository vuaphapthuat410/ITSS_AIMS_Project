package connectors;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import connectors.helper.AddItemHelper;
import models.DVD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DVDDbUtil {
    public static List<DVD> getAllItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT `dvd`.*, `physical_good`.*, `item`.* FROM `dvd` LEFT JOIN `physical_good` ON `dvd`.`item_id` = `physical_good`.`item_id` LEFT JOIN `item` ON `physical_good`.`item_id` = `item`.`id`;";
        ArrayList<DVD> dvd = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                String type = rs.getString(2);
                String director = rs.getString(3);
                int runtime = rs.getInt(4);
                String studio = rs.getString(5);
                String language = rs.getString(6);
                String subtitle = rs.getString(7);
                String publication_date = rs.getString(8);
                String genre = rs.getString(9);
                String barcode = rs.getString(11);
                String description = rs.getString(12);
                int quantity = rs.getInt(13);
                String date = rs.getString(14);
                int dimension_x = rs.getInt(15);
                int dimension_y = rs.getInt(16);
                int dimension_z = rs.getInt(17);
                int weight = rs.getInt(18);
                String title = rs.getString(20);
                int value = rs.getInt(21);
                int price = rs.getInt(22);
                int unit_sale = rs.getInt(23);
                String category = rs.getString(24);




                DVD b = new DVD(
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
                        type,
                        director,
                        runtime,
                        studio,
                        language,
                        subtitle,
                        publication_date,
                        genre
                );

                dvd.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return dvd;
    }

    public static boolean addItem(DVD dvd) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `dvd` (`item_id`, `type`, `director`, `runtime`, `studio`, `language`, `subtitle`, `publication_date`, `genre`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        // insert to Item and PhysicalGood
        int id = AddItemHelper.insertToItemAndPhysicalGood(dvd);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(id));
            statement.setString(2, dvd.getType());
            statement.setString(3, dvd.getDirector());
            statement.setString(4, Integer.toString(dvd.getRuntime()));
            statement.setString(5, dvd.getStudio());
            statement.setString(6, dvd.getLanguage());
            statement.setString(7, dvd.getSubtitle());
            statement.setString(8, dvd.getPublication_date());
            statement.setString(9, dvd.getGenre());
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
