/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import connectors.*;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vinh
 */
public class test {
    public static void doTest() throws SQLException, ClassNotFoundException {
        List<Book> bookList = BookDbUtil.getAllItem();
        System.out.print("Book Title: " + bookList.get(0).getTitle() + "\n");
        List<CD> cdList = CDDbUtil.getAllItem();
        System.out.print("CD Title: " + cdList.get(0).getTitle() + "\n");
        List<DVD> dvdList = DVDDbUtil.getAllItem();
        System.out.print("DVD Title: " + dvdList.get(2).getTitle() + "\n");
        List<LP> lpList = LPDbUtil.getAllItem();
        System.out.print("LP Title: " + lpList.get(0).getTitle() + "\n");
        List<Log> logList = LogDbUtil.getAllLog();
        System.out.print("Log: " + logList.get(1).getOperation() + "\n");



//         add book
//        Book item = new Book("One Piece", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "JK Rowling", "Hard", "Kim Dong", "1990-10-01", 123, "English", "fiction");
//        boolean result = BookDbUtil.addItem(item);
//        item.setTitle("Bleach");
//        item.setAuthor("Mario Puzo");
//        result = BookDbUtil.updateItem(item);

////         add cd
//        CD item = new CD("test-cd", 120, 123, 0, "test", "xyz", "This is a test", 123,  "2020-02-22", 123,123, 123, 50, "Hiroyuki Sawano", "Springer", "2020-12-09", "test");
//        boolean result = CDDbUtil.addItem(item);
//        item.setArtist("Beethoven");
//        result = CDDbUtil.updateItem(item);

//        add dvd
//        DVD item = new DVD("test-dvd", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "anime", "me", 5000, "disney", "English", "English", "2020-12-09", "test");
//        boolean result = DVDDbUtil.addItem(item);
//        item.setLanguage("Vietnamese");
//        result = DVDDbUtil.updateItem(item);

//        add lp
//        LP item = new LP("test-lp", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "Mozard", "Springer", "2020-12-09", "classical");
//        boolean result = LPDbUtil.addItem(item);
//        item.setArtist("Beethoven");
//        item.setTitle("LP title update");
//        item.setDimension_x(1000);
//        result = LPDbUtil.updateItem(item);
//          check result
        //test delete
        BookDbUtil.deleteItem(53);
//        if (result){
//            System.out.println("Operation success");
//        } else {
//            System.out.println("Operation fail");
//        }
//        PhysicalGood item = new PhysicalGood("test1", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 50);
//        boolean result = BookDbUtil.addItem(item);

//        Item item = new Item("test1", 120, 123, 0, "test");
//        boolean result = BookDbUtil.addItem(item);
    }
}
