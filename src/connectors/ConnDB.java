/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vuaphapthuat410
 */
public class ConnDB {
    // Retrieve login info
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "aims";
        String userName = "root";
        String password = "password";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
        // Cấu trúc URL Connection dành cho Oracle
        // Ví dụ: jdbc:mysql://localhost:3306/simplehr
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
