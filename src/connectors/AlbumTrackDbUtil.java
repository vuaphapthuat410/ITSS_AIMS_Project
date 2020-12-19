package connectors;

import models.AlbumTrack;
import models.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumTrackDbUtil {
    public static ArrayList<AlbumTrack> getAllTrack(int id) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM `album_track` WHERE `album_track`.`item_id` = ?";
        ArrayList<AlbumTrack> track = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(id));
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int item_id = rs.getInt(2);
                String name = rs.getString(3);
                int duration = rs.getInt(4);


                AlbumTrack b = new AlbumTrack(
                        item_id,
                        name,
                        duration
                );

                track.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return track;
    }

    public static boolean addTrack(AlbumTrack track) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `album_track` (`id`, `item_id`, `name`, `duration`) VALUES (NULL, ?, ?, ?);";

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);

            statement.setString(1, Integer.toString(track.getItem_id()));
            statement.setString(2, track.getName());
            statement.setString(3, Integer.toString(track.getDuration()));

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

    public static void addTrackList(int id, ArrayList<AlbumTrack> track_list) throws ClassNotFoundException, SQLException {

        for (int i = 0; i < track_list.size(); i++){
            track_list.get(i).setItem_id(id);
            addTrack(track_list.get(i));
        }

    }

    public static void updateTrackList(int id, ArrayList<AlbumTrack> track_list){
        String itemQuery = "DELETE FROM `album_track` WHERE `album_track`.`item_id` = ?";
        try {

            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(itemQuery);
            statement.setString(1, Integer.toString(id));
            // remove current track list
            int rowsDeleted = statement.executeUpdate();

            //add new track list
            addTrackList(id, track_list);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
