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
    
    public static void saveInfo(User user) {
        loginUser = user;
    }
    
    public static Integer getId() {
        return loginUser.getId();
    }

    public static String getUname() {
        return loginUser.getUname();
    }

    public static String getName() {
        return loginUser.getName();
    }

    public static String getEmail() {
        return loginUser.getEmail();
    }

    public static String getPhone() {
        return loginUser.getPhone();
    }

    public static boolean isAdmin() {
        return loginUser.isAdmin();
    }
}
