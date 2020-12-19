/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import connectors.BookDbUtil;
import connectors.CDDbUtil;
import connectors.DVDDbUtil;
import connectors.LPDbUtil;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Book;
import models.CD;
import models.DVD;
import models.Item;
import models.LP;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class ProductPaneController implements Initializable {

    @FXML
    private TilePane productView;
    @FXML
    private Button btNext;
    @FXML
    private Button btPrev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
    }    

    @FXML
    private void toNext(ActionEvent event) {
    }

    @FXML
    private void toPrev(ActionEvent event) {
    }
    
    private void reloadProducts(ArrayList<? extends Item> items) { // using wildcard here for polymorphism
        ObservableList<Node> products = productView.getChildren();
        for(int i = 0; i < 9; ++i) {
            GridPane aProduct = (GridPane) products.get(i);
            AnchorPane imagePane = (AnchorPane) aProduct.getChildren().get(0);
            VBox imagePaneBox = (VBox) imagePane.getChildren().get(0);
            ImageView image = (ImageView) imagePaneBox.getChildren().get(0);
            image.setImage(new Image("data/not-bug-feature.jpg"));
            Label productName = (Label) imagePaneBox.getChildren().get(1);
            
            if(i < items.size()) {
                productName.setText(items.get(i).getTitle());
                aProduct.setVisible(true); // show item in case it was hided before
            }
            else 
                aProduct.setVisible(false); // hide item if there're not anymore items
        }
    }
    
    public void getMixed() {
        // Write function that return random list of products
    }
    
    public void getBook() {
        ArrayList<Book> books = null;
        try {
            // TODO
            books = BookDbUtil.getAllItem();
            if(books == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        reloadProducts(books);
    }
    
    public void getCD() {
        ArrayList<CD> cds = null;
        try {
            // TODO
            cds = CDDbUtil.getAllItem();
            if(cds == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        reloadProducts(cds);
    } 
    
    public void getLP() {
        ArrayList<LP> lps = null;
        try {
            // TODO
            lps = LPDbUtil.getAllItem();
            if(lps == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        reloadProducts(lps);
    } 
    
    public void getDVD() {
        ArrayList<DVD> dvds = null;
        try {
            // TODO
            dvds = DVDDbUtil.getAllItem();
            if(dvds == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        reloadProducts(dvds);
    } 
    
}
