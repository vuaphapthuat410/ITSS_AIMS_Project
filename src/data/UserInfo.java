/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import models.User;
/**
 *
 * @author vuaphapthuat410
 */
public class UserInfo {
    private static User loginUser;
    
    public static void saveInfo(String uname) {
        loginUser = new User(uname);
    }
    
    public static String getUname() {
        return loginUser.getUname();
    }
}
