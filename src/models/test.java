/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import connectors.BookDbUtil;
import connectors.CDDbUtil;
import connectors.DVDDbUtil;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vinh
 */
public class test {
    public static void doTest() throws SQLException, ClassNotFoundException {
        List<Book> bookList = BookDbUtil.getAllBook();
        System.out.print("Title: " + bookList.get(0).getTitle() + "\n");
        List<CD> cdList = CDDbUtil.getAllCD();
        System.out.print("Title: " + cdList.get(0).getTitle() + "\n");
        List<DVD> dvdList = DVDDbUtil.getAllDVD();
        System.out.print("Title: " + dvdList.get(0).getTitle() + "\n");
    }
}
