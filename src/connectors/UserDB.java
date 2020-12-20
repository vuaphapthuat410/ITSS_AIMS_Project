/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author vuaphapthuat410
 */
public class UserDB {
    public static User getLoginUser(String uname, String passwd) throws ClassNotFoundException, SQLException {
        // Lấy ra đối tượng Connection kết nối vào DB.
        Connection connection = ConnDB.getMySQLConnection();

        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();

        String sql = "Select id, username, password, name, email, phone_number, admin from user";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);

        // Duyệt trên kết quả trả về.
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
            Integer tempid = rs.getInt(1);
            String tempuname = rs.getString(2);
            String temppasswd = rs.getString(3);
            String tempname = rs.getString(4);
            String tempemail = rs.getString(5);
            String tempphone = rs.getString(6);
            Boolean role = rs.getBoolean(7);

            if(tempuname.equals(uname) && temppasswd.equals(passwd))
            {
                connection.close();
                return new User(tempid, tempuname, null, tempname, tempemail, tempphone, role);     
            }
        }
        // Đóng kết nối
        connection.close();
        return null;
    }
    
    public static User reloadLoginUser(Integer id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT `username`, `name`, `email`, `phone_number` FROM `user` WHERE `user`.`id` = ?";
        
        try {
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, id);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
                String tempuname = rs.getString(1);
                String tempname = rs.getString(2);
                String tempemail = rs.getString(3);
                String tempphone = rs.getString(4);

                connection.close();
                return new User(id, tempuname, null, tempname, tempemail, tempphone, false);  
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static boolean addUser(String uname, String passwd, String name, String email, String phone) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `user`(`username`, `password`, `name`, `email`, `phone_number`, `admin`) VALUES (?,?,?,?,?,?)";
        
        try {
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //insert to PhysicalGood
            statement.setString(1, uname);
            statement.setString(2, passwd);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setString(6, Integer.toString(0));
            int rowsInserted = statement.executeUpdate();
            
            connection.close();
            if (rowsInserted > 0) {
                return true;
            }
            
        } catch (Exception e) {
            System.out.print("Cannot add user");
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static boolean updateUser(Integer id, String name, String email, String phone) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `user` SET `name`= ? ,`email`= ? ,`phone_number`= ? WHERE `user`.`id` = ? ";
        
        try {
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //insert to PhysicalGood
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setInt(4, id);
            
            int rowsInserted = statement.executeUpdate();
            
            connection.close();
            if (rowsInserted > 0) {
                return true;
            }
            
        } catch (Exception e) {
            System.out.print("Cannot update user");
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static boolean changePasswd(Integer id, String oldPasswd, String newPasswd) {
        String sql = "UPDATE `user` SET `password`= ? WHERE `user`.`id` = ? and `user`.`password` = ?";
        
        try {
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //insert to PhysicalGood
            statement.setString(1, newPasswd);
            statement.setInt(2, id);
            statement.setString(3, oldPasswd);
            
            int rowsInserted = statement.executeUpdate();
            
            connection.close();
            if (rowsInserted > 0) {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Cannot change password.");
            e.printStackTrace();
        }
        
        System.out.println("Wrong password");
        return false;
    }
}
