package connectors;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import models.Book;
import models.Item;
import models.PhysicalGood;

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
                int dimension_x = rs.getInt(14);
                int dimension_y = rs.getInt(15);
                int dimension_z = rs.getInt(16);
                int weight = rs.getInt(17);
                String title = rs.getString(19);
                int value = rs.getInt(20);
                int price = rs.getInt(21);
                int unit_sale = rs.getInt(22);
                String category = rs.getString(23);




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
                        dimension_x,
                        dimension_y,
                        dimension_z,
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
    public static boolean addItem(Book book) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `book` (`item_id`, `author`, `cover`, `publisher`, `publication_date`, `page`, `language`, `genre`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        // insert to Item and PhysicalGood
        int id = AddItemHelper.insertToItemAndPhysicalGood(book);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(id));
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCover());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getPublication_date());
            statement.setString(6, Integer.toString(book.getPage()));
            statement.setString(7, book.getLanguage());
            statement.setString(8, book.getGenre());
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
