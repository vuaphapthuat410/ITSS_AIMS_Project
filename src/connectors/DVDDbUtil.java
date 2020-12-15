package connectors;

import models.Book;
import models.DVD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DVDDbUtil {
    public static List<DVD> getAllDVD() throws ClassNotFoundException, SQLException {

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
                int dimension = rs.getInt(15);
                int weight = rs.getInt(16);
                String title = rs.getString(18);
                int value = rs.getInt(19);
                int price = rs.getInt(20);
                int unit_sale = rs.getInt(21);
                String category = rs.getString(22);




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
                        dimension,
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
}
