package connectors;

import connectors.helper.AddItemHelper;
import connectors.helper.DeleteItemHelper;
import connectors.helper.UpdateItemHelper;
import models.DVD;
import models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieDbUtil {
    public static List<Movie> getAllItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT `movie`.*, `item`.* FROM `movie` LEFT JOIN `item` ON `movie`.`item_id` = `item`.`id`";
        ArrayList<Movie> item = new ArrayList<>();

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

                String actorList = rs.getString(10);
                actorList = actorList.substring( 1, actorList.length() - 1 );
                List<String> actors = Arrays.asList(actorList.split(","));
                String writerList = rs.getString(11);
                writerList = writerList.substring( 1, writerList.length() - 1 );
                List<String> writers = Arrays.asList(writerList.split(","));

                String title = rs.getString(13);
                int value = rs.getInt(14);
                int price = rs.getInt(15);
                int unit_sale = rs.getInt(16);
                String category = rs.getString(17);






                Movie b = new Movie(
                        id,
                        title,
                        value,
                        price,
                        unit_sale,
                        category,
                        type,
                        director,
                        runtime,
                        studio,
                        language,
                        subtitle,
                        publication_date,
                        genre,
                        actors,
                        writers
                );

                item.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return item;
    }

    public static boolean addItem(Movie item) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `movie` (`item_id`, `type`, `director`, `runtime`, `studio`, `language`, `subtitle`, `publication_date`, `genre`, `actors`, `writers`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        // insert to Item and PhysicalGood
        int id = AddItemHelper.insertToItem(item);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(id));
            statement.setString(2, item.getType());
            statement.setString(3, item.getDirector());
            statement.setString(4, Integer.toString(item.getRuntime()));
            statement.setString(5, item.getStudio());
            statement.setString(6, item.getLanguage());
            statement.setString(7, item.getSubtitle());
            statement.setString(8, item.getPublication_date());
            statement.setString(9, item.getGenre());
            statement.setString(10, item.getActors().toString());
            statement.setString(11, item.getWriters().toString());
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

    public static boolean updateItem(Movie item) throws ClassNotFoundException, SQLException{


        String query = "UPDATE `movie` SET `type` = ?, `director` = ?, `runtime` = ?, `studio` = ?, `language` = ?, `subtitle` = ?, `publication_date` = ?, `genre` = ? WHERE `movie`.`item_id` = ?";
        // insert to Item and PhysicalGood
        boolean result = UpdateItemHelper.updateItem(item);
        if (!result){
            return false;
        }

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, item.getType());
            statement.setString(2, item.getDirector());
            statement.setString(3, Integer.toString(item.getRuntime()));
            statement.setString(4, item.getStudio());
            statement.setString(5, item.getLanguage());
            statement.setString(6, item.getSubtitle());
            statement.setString(7, item.getPublication_date());
            statement.setString(8, item.getGenre());
            statement.setString(9, Integer.toString(item.getId()));

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

        DeleteItemHelper.deleteItem(id);

    }
}
