package connectors;

import connectors.helper.AddItemHelper;
import connectors.helper.DeleteItemHelper;
import connectors.helper.UpdateItemHelper;
import models.Album;
import models.AlbumTrack;
import models.CD;
import models.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDbUtil {
    public static ArrayList<Album> getAllItem() throws ClassNotFoundException, SQLException {

        String query = "SELECT `album`.*, `item`.* FROM `album` LEFT JOIN `item` ON `album`.`item_id` = `item`.`id`";
        ArrayList<Album> item = new ArrayList<>();

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
                String title = rs.getString(7);
                int price = rs.getInt(8);
                int value = rs.getInt(9);
                int unit_sale = rs.getInt(10);
                String category = rs.getString(11);
                ArrayList<AlbumTrack> track_list = AlbumTrackDbUtil.getAllTrack(id);




                Album b = new Album(
                        id,
                        title,
                        value,
                        price,
                        unit_sale,
                        category,
                        artists,
                        record_label,
                        publication_date,
                        genre,
                        track_list
                );

                item.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return item;
    }

    public static boolean addItem(Album item) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `album` (`item_id`, `artist`, `record_label`, `publication_date`, `genre`) VALUES (?, ?, ?, ?, ?);";
        // insert to Item and PhysicalGood
        int id = AddItemHelper.insertToItem(item);

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(id));
            statement.setString(2, item.getArtist());
            statement.setString(3, item.getRecord_label());
            statement.setString(4, item.getPublication_date());
            statement.setString(5, item.getGenre());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                return false;
            }
            AlbumTrackDbUtil.addTrackList(item.getId(), item.getTrack_list());

        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return true;
    }

    public static boolean updateItem(Album item) throws ClassNotFoundException, SQLException{


        String query = "UPDATE `album` SET `artist` = ?, `record_label` = ?, `publication_date` = ?, `genre` = ? WHERE `cd`.`item_id` = ?";
        // insert to Item and PhysicalGood
        boolean result = UpdateItemHelper.updateItem(item);
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
            if (rowsInserted == 0) {
                return false;
            }
            AlbumTrackDbUtil.updateTrackList(item.getId() ,item.getTrack_list());

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
