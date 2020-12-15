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

        String query = "SELECT `book`.*, `physical_good`.*, `item`.* FROM `book` LEFT JOIN `physical_good` ON `book`.`item_id` = `physical_good`.`item_id` LEFT JOIN `item` ON `physical_good`.`item_id` = `item`.`id`;";
        ArrayList<Book> book = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                String author = rs.getString(2);
                String cover = rs.getString(3);
                String publisher = rs.getString(4);
                String publication_date = rs.getString(5);
                int page = rs.getInt(6);
                String language = rs.getString(7);
                String genre = rs.getString(8);
                String barcode = rs.getString(10);
                String description = rs.getString(11);
                int quantity = rs.getInt(12);
                String date = rs.getString(13);
                int dimension = rs.getInt(14);
                int weight = rs.getInt(15);
                String title = rs.getString(17);
                int value = rs.getInt(18);
                int price = rs.getInt(19);
                int unit_sale = rs.getInt(20);
                String category = rs.getString(21);




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
