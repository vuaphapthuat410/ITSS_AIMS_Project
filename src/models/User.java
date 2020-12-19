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
    private Integer id;
    private String uname;
    private String passwd;
    private String name;
    private String email;
    private String phone;
    private boolean isAdmin;
    
    public User() {
    }

    public User(Integer id, String uname, String passwd, String name, String email, String phone, boolean isAdmin) {
        this.id = id;
        this.uname = uname;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }
    
    
    public boolean authorize(String uname, String passwd) {
        return this.uname.equals(uname) && this.passwd.equals(passwd);
    }

    public Integer getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    
    
}
