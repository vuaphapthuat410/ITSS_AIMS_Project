package connectors;

import com.mysql.jdbc.PreparedStatement;
import models.Book;
import models.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDbUtil {
    public static List<Book> getAllItem() throws ClassNotFoundException, SQLException {

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
    public static boolean addItem(Item book) throws ClassNotFoundException, SQLException {

        String itemQuery = "INSERT INTO `item` (`id`, `title`, `value`, `price`, `unit_sale`, `category`) VALUES (NULL, ?, ?, ?, ?, ?);";
        String physicalGoodQuery = "INSERT INTO `item` (`id`, `title`, `value`, `price`, `unit_sale`, `category`) VALUES (NULL, ?, ?, ?, ?, ?);";

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(itemQuery,
                                                                                        Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setString(2, Integer.toString(book.getValue()));
            statement.setString(3, Integer.toString(book.getPrice()));
            statement.setString(4, Integer.toString(book.getUnit_sale()));
            statement.setString(5, book.getCategory());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new item was inserted successfully!");
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("created item's id: " + id);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }





        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return false;
    }
}
