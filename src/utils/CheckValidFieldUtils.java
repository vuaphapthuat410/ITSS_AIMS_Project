/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
/**
 *
 * @author vuaphapthuat410
 */
public class CheckValidFieldUtils {
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidPhone(String phoneNum) {
        boolean valid = isNumeric(phoneNum);
        return (phoneNum.length() == 10 || phoneNum.length() == 11) && valid;
    }
    
    public static boolean isValidUname(String uname) {
        return !uname.isEmpty();
    }
    
    public static boolean isValidEmail(String email) {
        return !email.isEmpty() && email.contains("@");
    }
    
    public static boolean isValidPasswd(String passwd) {
        return !passwd.isEmpty();
    }
}
