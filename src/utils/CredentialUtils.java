/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vuaphapthuat410
 */
public class CredentialUtils {
    public static void createCredentialFile(String uname, String passwd) {
        try {
            FileWriter credentFile = new FileWriter("credential");
            String user_passwd = uname + "\n" + passwd;
            credentFile.write(user_passwd);
            credentFile.close();
            System.out.println("Successfully create credential file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static ArrayList<String> getCredentialFromFile() {
        try {
            File credentFile = new File("credential");     
            if(!credentFile.exists())
                return null;
            
            Scanner fileReader = new Scanner(credentFile);      
            String uname = fileReader.nextLine();
            String passwd = fileReader.nextLine();
            
            fileReader.close();
            
            ArrayList<String> userInfo = new ArrayList<String>();
            userInfo.add(uname);
            userInfo.add(passwd);
            
            return userInfo;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        return null;
    }
    
    public static void deleteCrendentialFile() {
        File credentFile = new File("credential"); 
        if(!credentFile.exists())
                return;
        
        if (credentFile.delete()) { 
            System.out.println("Deleted the file: " + credentFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        } 
    }
}
