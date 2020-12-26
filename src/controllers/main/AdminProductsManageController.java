/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import connectors.AlbumDbUtil;
import connectors.BookDbUtil;
import connectors.CDDbUtil;
import connectors.DVDDbUtil;
import connectors.EbookDbUtil;
import connectors.LPDbUtil;
import connectors.MovieDbUtil;
import controllers.cart.CartController;
import controllers.main.ProductPaneElementController;
import controllers.main.AdminProductPaneElementController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import models.Album;
import models.Book;
import models.CD;
import models.DVD;
import models.Ebook;
import models.Item;
import models.LP;
import models.Movie;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AdminProductsManageController implements Initializable {

    @FXML
    private TilePane productView;
    @FXML
    private Button btNext;
    @FXML
    private Button btPrev;
    @FXML
    private ComboBox<?> cbSort;
    @FXML
    private Button btNew;
    @FXML
    private TextField tfSearch;
    
    Integer page = 0;
    ArrayList<? extends Item> itemsList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createNew(ActionEvent event) {
    }
    
    @FXML
    private void toNext(ActionEvent event) {
        reloadProducts(itemsList, ++page);
    }

    @FXML
    private void toPrev(ActionEvent event) {
        reloadProducts(itemsList, --page);
    }
    
    private void reloadProducts(ArrayList<? extends Item> items, Integer page) { // using wildcard here for polymorphism
        ObservableList<Node> products = productView.getChildren();
        products.clear();   //clear list before get new one

        for(int i = 0; i < 20; ++i) {
            int j = 20*page + i; // this is item index  - in need change this code and the above code , change 9 to change the max number of element in TilePane
            if(j < items.size()) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainview/adminProductPaneElement.fxml"));
                GridPane anItem = null;
                try {
                    anItem = loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    
                    continue;
                }
                AdminProductPaneElementController itemControl = loader.getController();
                itemControl.setItem(items.get(j));
                products.add(anItem);
            }
        } 
    }
    
    public void getMixed() {
        // Write function that return random list of products
        ArrayList<Item> items = new ArrayList<Item>();
        
        ArrayList<Book> books = null;
        ArrayList<CD> cds = null;
        ArrayList<LP> lps = null;
        ArrayList<DVD> dvds = null;
        
        ArrayList<Ebook> ebooks = null;
        ArrayList<Movie> movies = null;
        ArrayList<Album> albums = null;
        
        try {
            books = BookDbUtil.getAllItem();
            cds = CDDbUtil.getAllItem();
            lps = LPDbUtil.getAllItem();
            dvds = DVDDbUtil.getAllItem();
            
            ebooks = EbookDbUtil.getAllItem();
            movies = MovieDbUtil.getAllItem();
            albums = AlbumDbUtil.getAllItem();
            
            items.addAll(books);
            items.addAll(cds);
            items.addAll(lps);
            items.addAll(dvds);
            
            items.addAll(ebooks);
            items.addAll(movies);
            items.addAll(albums);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        items.removeIf(Objects::isNull); // Java 8 code for remove all null element from list
        ArrayList<Item> removedList = new ArrayList<Item>();    // contains element that have title is null
        for(Item item : items) {
            if(item.getTitle() == null)
                removedList.add(item);
        }
        items.removeAll(removedList);   // remove all element have null title
        Collections.sort(items);    // sort by title
        
        page = 0;
        itemsList = items;
        reloadProducts(items, 0);
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
        
        page = 0;
        itemsList = books;
        reloadProducts(books, 0);
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
        
        page = 0;
        itemsList = cds;
        reloadProducts(cds, 0);
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
        
        page = 0;
        itemsList = lps;
        reloadProducts(lps, 0);
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
        
        page = 0;
        itemsList = dvds;
        reloadProducts(dvds, 0);
    } 
    
    public void getEbook() {
        ArrayList<Ebook> ebooks = null;
        try {
            // TODO
            ebooks = EbookDbUtil.getAllItem();
            if(ebooks == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        page = 0;
        itemsList = ebooks;
        reloadProducts(ebooks, 0);
    }
    
    public void getMovie() {
        ArrayList<Movie> movies = null;
        try {
            // TODO
            movies = MovieDbUtil.getAllItem();
            if(movies == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        page = 0;
        itemsList = movies;
        reloadProducts(movies, 0);
    }
    
    public void getAlbum() {
        ArrayList<Album> albums = null;
        try {
            // TODO
            albums = AlbumDbUtil.getAllItem();
            if(albums == null) return;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        page = 0;
        itemsList = albums;
        reloadProducts(albums, 0);
    }
}
