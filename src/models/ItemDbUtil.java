/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh
 */
public class ItemDbUtil {
    public static List<Item> getItemList() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/aims";
        String userName = "root";
        String password = "";


        ArrayList<Item> item = new ArrayList<>();



        try{
            //load mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //get the connection
            Connection con = DriverManager.getConnection(url, userName, password);

            System.out.print("Database connected!\n");

            //create a statement

            Statement stmt = con.createStatement();

            //execute the stmt and loop over the result set
            ResultSet rs = stmt.executeQuery("select * from item");

            //test return data
            //            ResultSetMetaData rsmd = rs.getMetaData();
            //            int columnsNumber = rsmd.getColumnCount();
            //            while (rs.next()) {
            //                for (int i = 1; i <= columnsNumber; i++) {
            //                    if (i > 1) System.out.print(",  ");
            //                    String columnValue = rs.getString(i);
            //                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
            //                }
            //                System.out.println("");
            //            }

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                int value = rs.getInt(3);
                int price = rs.getInt(4);
                int unit_sale = rs.getInt(5);
                String category = rs.getString(6);
                Item i = new Item(id, title, value, price, unit_sale, category);
                item.add(i);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return item;
    }
}
