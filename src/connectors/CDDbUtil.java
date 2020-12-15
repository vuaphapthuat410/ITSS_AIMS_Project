package connectors;

import models.CD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CDDbUtil {
    public static List<CD> getAllCD() throws ClassNotFoundException, SQLException {

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
                int dimension = rs.getInt(11);
                int weight = rs.getInt(12);
                String title = rs.getString(14);
                int value = rs.getInt(15);
                int price = rs.getInt(16);
                int unit_sale = rs.getInt(17);
                String category = rs.getString(18);




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
                        dimension,
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
}
