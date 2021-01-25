package connectors;

import connectors.helper.AddItemHelper;
import models.Book;
import models.CD;
import models.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Item;

public class LogDbUtil {
    public static List<Log> getAllLog() throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM `log`";
        ArrayList<Log> log = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt(1);
                int item_id = rs.getInt(2);
                String operation = rs.getString(3);
                String time = rs.getString(4);

                Log b = new Log(
                        id,
                        item_id,
                        operation,
                        time
                );

                log.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return log;
    }

    public static boolean addLog(Log log) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO `log` (`id`, `item_id`, `operation`, `time`) VALUES (NULL, ?, ?, ?);";
        // insert to Item and PhysicalGood

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);


            //insert to PhysicalGood
            statement.setString(1, Integer.toString(log.getItem_id()));
            statement.setString(2, log.getOperation());
            statement.setString(3, log.getTime());
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
    
    public static ArrayList<Log> getAllLogByItemId(Integer Id) {

        String query = "SELECT `id`, `operation`, `time` FROM `log` WHERE `log`.`item_id` = ?";
        ArrayList<Log> log = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, Id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                int item_id = Id;
                String operation = rs.getString(2);
                String time = rs.getString(3);

                Log b = new Log(
                        id,
                        item_id,
                        operation,
                        time
                );

                log.add(b);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return log;
    }
    
    public static Log getLogInitItemByItemId(Integer Id) {

        String query = "SELECT `id`, `operation`, `time` FROM `log` WHERE `log`.`item_id` = ? AND `operation` = 'add'";
        Log newLog = null;
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, Id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                int item_id = Id;
                String operation = rs.getString(2);
                String time = rs.getString(3);

                newLog = new Log(
                        id,
                        item_id,
                        operation,
                        time
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return newLog;
    }
}
