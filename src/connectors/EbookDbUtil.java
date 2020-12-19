package connectors;

import connectors.helper.AddItemHelper;
import connectors.helper.DeleteItemHelper;
import connectors.helper.UpdateItemHelper;
import models.Book;
import models.Ebook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EbookDbUtil {

    public static ArrayList<Ebook> getAllItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT `book_content`.*, `book`.*, `item`.* FROM `book_content` LEFT JOIN `book` ON `book_content`.`item_id` = `book`.`item_id` LEFT JOIN `item` ON `book`.`item_id` = `item`.`id`;";
        ArrayList<Ebook> item = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                String content = rs.getString(2);
                String author = rs.getString(4);
                String cover = rs.getString(5);
                String publisher = rs.getString(6);
                String publication_date = rs.getString(7);
                int page = rs.getInt(8);
                String language = rs.getString(9);
                String genre = rs.getString(10);
                String title = rs.getString(12);
                int value = rs.getInt(13);
                int price = rs.getInt(14);
                int unit_sale = rs.getInt(15);
                String category = rs.getString(16);

                Ebook b = new Ebook(
                        id,
                        title,
                        value,
                        price,
                        unit_sale,
                        category,
                        author,
                        cover,
                        publisher,
                        publication_date,
                        page,
                        language,
                        genre,
                        content
                );

                item.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return item;
    }

    public static boolean addItem(Ebook item) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `book` (`item_id`, `author`, `cover`, `publisher`, `publication_date`, `page`, `language`, `genre`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        String contentQuery = "INSERT INTO `book_content` (`item_id`, `content`) VALUES (?, ?);";
        // insert to Item
        int id = AddItemHelper.insertToItem(item);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to Ebook
            statement.setString(1, Integer.toString(id));
            statement.setString(2, item.getAuthor());
            statement.setString(3, item.getCover());
            statement.setString(4, item.getPublisher());
            statement.setString(5, item.getPublication_date());
            statement.setString(6, Integer.toString(item.getPage()));
            statement.setString(7, item.getLanguage());
            statement.setString(8, item.getGenre());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                return false;
            }

            //insert Ebook content
            statement = (PreparedStatement) connection.prepareStatement(contentQuery);
            statement.setString(1, Integer.toString(id));
            statement.setString(2, item.getContent());
            rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                return false;
            }

        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return true;
    }

    public static boolean updateItem(Ebook item) throws ClassNotFoundException, SQLException{


        String query = "UPDATE `book` SET `author` = ?, `cover` = ?, `publisher` = ?, `publication_date` = ?, `page` = ?, `language` = ?, `genre` = ? WHERE `book`.`item_id` = ?";
        String contentQuery = "UPDATE `book_content` SET `content` = ? WHERE `book_content`.`item_id` = ?";
        // insert to Item and PhysicalGood
        boolean result = UpdateItemHelper.updateItem(item);
        if (!result){
            return false;
        }

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, item.getAuthor());
            statement.setString(2, item.getCover());
            statement.setString(3, item.getPublisher());
            statement.setString(4, item.getPublication_date());
            statement.setString(5, Integer.toString(item.getPage()));
            statement.setString(6, item.getLanguage());
            statement.setString(7, item.getGenre());
            statement.setString(8, Integer.toString(item.getId()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                return false;
            }

            statement = (PreparedStatement) connection.prepareStatement(contentQuery);


            //insert to PhysicalGood

            statement.setString(1, item.getContent());
            statement.setString(2, Integer.toString(item.getId()));

            rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                return false;
            }


        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }
        return true;
    }

    public static void deleteItem(int id) throws SQLException, ClassNotFoundException {

        DeleteItemHelper.deleteItem(id);

    }
}
