/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aims_project;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import models.Book;
import models.BookDbUtil;
import models.PhysicalGood;
import models.PhysicalItemDbUtil;

/**
 *
 * @author vuaphapthuat410
 */
public class Aims extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {   
                List<Book> bookList = BookDbUtil.getBookList();
                System.out.print("Title: " + bookList.get(0).getTitle() + "\n");

                Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                stage.setTitle("AIMS PROJECT");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        } catch(IOException e) { 
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
