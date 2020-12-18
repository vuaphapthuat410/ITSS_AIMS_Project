package connectors.helper;

import connectors.ConnDB;
import connectors.LogDbUtil;
import models.Log;
import models.PhysicalGood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteItemHelper {
    public static void deleteItem (int id) throws SQLException, ClassNotFoundException {
        String itemQuery = "DELETE FROM `item` WHERE `item`.`id` = ?";
        try {

            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(itemQuery);

            statement.setString(1, Integer.toString(id));
            int rowsDeleted = statement.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        }

        //create log
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Log log = new Log(id, "delete", dtf.format(now));
        LogDbUtil.addLog((log));

    }
}
