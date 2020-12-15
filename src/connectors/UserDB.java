/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author vuaphapthuat410
 */
public class UserDB {
    public static Integer getLoginUser(String uname, String passwd) throws ClassNotFoundException, SQLException {
      // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnDB.getMySQLConnection();
 
      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();
 
      String sql = "Select username, password, admin from user";
 
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      ResultSet rs = statement.executeQuery(sql);
 
      // Duyệt trên kết quả trả về.
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          String tempname = rs.getString(1);
          String temppasswd = rs.getString(2);
          Boolean role = rs.getBoolean(3);
          
          if(tempname.equals(uname) && temppasswd.equals(passwd))
          {
              connection.close();
              if(role == true) 
                  return 2; // admin
              else 
                  return 1; //user
                      
          }
      }
      // Đóng kết nối
      connection.close();
      return 0;
  }
}
