/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vuaphapthuat410
 */
public class User {
    private String uname;
    private String passwd;

    public User(String uname, String passwd) {
        this.uname = uname;
        this.passwd = passwd;
    }
    
    public boolean authorize(String uname, String passwd) {
        return this.uname.equals(uname) && this.passwd.equals(passwd);
    }
    
}
