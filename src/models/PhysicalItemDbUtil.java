package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhysicalItemDbUtil {
    public static List<PhysicalGood> getPhysicalItemList() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/aims";
        String userName = "root";
        String password = "";


        ArrayList<PhysicalGood> physicalItem = new ArrayList<>();



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


            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                int value = rs.getInt(3);
                int price = rs.getInt(4);
                int unit_sale = rs.getInt(5);
                String category = rs.getString(6);

//                PhysicalGood p = new PhysicalGood(id, title, value, price, unit_sale, category);
//                physicalItem.add(p);
            }
        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return physicalItem;
    }
}
