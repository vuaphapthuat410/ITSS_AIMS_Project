package connectors;

import models.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDbUtil {
    public static List<Book> getAllBook() throws ClassNotFoundException, SQLException {

        String query = "SELECT `item`.*, `physical_good`.*, `book`.* FROM `item` LEFT JOIN `physical_good` ON `physical_good`.`item_id` = `item`.`id` LEFT JOIN `book` ON `book`.`item_id` = `physical_good`.`item_id`";
        ArrayList<Book> book = new ArrayList<>();

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
                String description = rs.getString(8);
                String barcode = rs.getString(9);
                int quantity = rs.getInt(10);
                String date = rs.getString(11);
                int dimension = rs.getInt(12);
                int weight = rs.getInt(13);
                String author = rs.getString(15);
                String cover = rs.getString(16);
                String publisher = rs.getString(17);
                String publication_date = rs.getString(18);
                int page = rs.getInt(19);
                String language = rs.getString(20);
                String genre = rs.getString(21);


                Book b = new Book(
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
                        author,
                        cover,
                        publisher,
                        publication_date,
                        page,
                        language,
                        genre
                );

                book.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return book;
    }
}
