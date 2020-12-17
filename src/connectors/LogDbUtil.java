package connectors;

import models.Book;
import models.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
}
