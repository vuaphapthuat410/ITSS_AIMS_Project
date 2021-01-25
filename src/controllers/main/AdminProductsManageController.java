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
import connectors.PromoDbUtil;
import controllers.cart.CartController;
import controllers.dashboard.products.ProductController;
import controllers.main.ProductPaneElementController;
import controllers.main.AdminProductPaneElementController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Album;
import models.Book;
import models.CD;
import models.DVD;
import models.Ebook;
import models.Item;
import models.LP;
import models.Movie;
import models.PromoItem;
import utils.AdminUtils;

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
    private ComboBox<String> cbSort;
    @FXML
    private Button btNew;
    @FXML
    private TextField tfSearch;
    
    Integer page = 0;
    ArrayList<? extends Item> itemsList; //this for viewing as page
    ArrayList<? extends Item> savedItems; // this for searching and many things else
    HashMap<Integer, PromoItem> promoItems = new HashMap<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbSort.getItems().add("A to Z");
        cbSort.getItems().add("Z to A");
        cbSort.getItems().add("Ascending price");
        cbSort.getItems().add("Descending price");
        cbSort.getItems().add("Best seller");
        
        cbSort.setValue("A to Z"); //default sort
    }    

    @FXML
    private void createNew(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/products/Product.fxml"));
        Parent node = loader.load();
        ProductController addProductController = loader.getController();
        
        Stage stage = new Stage();
        stage.setTitle("Add product");
        stage.setScene(new Scene(node));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
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
        if(page < 0) { //prev for nothing
            this.page++;
            return;
        } 
        
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
                if(!promoItems.isEmpty()) { //if there are any promo
                    if(promoItems.containsKey(items.get(j).getId())) {
                        itemControl.setPromo(promoItems.get(items.get(j).getId())); // we use id of item as key for promo item value
                    }
                }
                
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
        
        // get all promo items
        try {
            ArrayList<PromoItem> promoItemList = PromoDbUtil.getAllPromoItem();
            
            for(PromoItem item : promoItemList) {
                promoItems.put(item.getItem_id(), item); // use item id as key for item value
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        page = 0;
        itemsList = items; //for viewing
        savedItems = items; // for searching and many thing, only refresh if getMixed()
        AdminUtils.setItems(items); // for promo, trick
        
        reloadProducts(items, 0);
    }
    
    public void getBook() {
        ArrayList<Book> books = new ArrayList<Book>();
        for(Item item : savedItems) {
            if(item instanceof Book)
                books.add((Book) item);
        }
        
        page = 0;
        itemsList = books;
        reloadProducts(books, 0);
    }
    
    public void getCD() {
        ArrayList<CD> cds = new ArrayList<CD>();
        for(Item item : savedItems) {
            if(item instanceof CD)
                cds.add((CD) item);
        }
        
        page = 0;
        itemsList = cds;
        reloadProducts(cds, 0);
    } 
    
    public void getLP() {
        ArrayList<LP> lps = new ArrayList<LP>();
        for(Item item : savedItems) {
            if(item instanceof LP)
                lps.add((LP) item);
        }
        
        page = 0;
        itemsList = lps;
        reloadProducts(lps, 0);
    } 
    
    public void getDVD() {
        ArrayList<DVD> dvds = new ArrayList<DVD>();
        for(Item item : savedItems) {
            if(item instanceof DVD)
                dvds.add((DVD) item);
        }
        
        page = 0;
        itemsList = dvds;
        reloadProducts(dvds, 0);
    } 
    
    public void getEbook() {
        ArrayList<Ebook> ebooks = new ArrayList<Ebook>();
        for(Item item : savedItems) {
            if(item instanceof Ebook)
                ebooks.add((Ebook) item);
        }
        
        page = 0;
        itemsList = ebooks;
        reloadProducts(ebooks, 0);
    }
    
    public void getMovie() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for(Item item : savedItems) {
            if(item instanceof Movie)
                movies.add((Movie) item);
        }
        
        page = 0;
        itemsList = movies;
        reloadProducts(movies, 0);
    }
    
    public void getAlbum() {
        ArrayList<Album> albums = new ArrayList<Album>();
        for(Item item : savedItems) {
            if(item instanceof Album)
                albums.add((Album) item);
        }
        
        page = 0;
        itemsList = albums;
        reloadProducts(albums, 0);
    }
    
    public void getSearchList(String keyword) {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item item : savedItems) {
            if(item.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                searchList.add(item);
        }
        
        page = 0;
        itemsList = searchList;
        reloadProducts(searchList, 0);
    }

    @FXML
    private void toSort(ActionEvent event) {
        switch(cbSort.getSelectionModel().getSelectedItem()) {
            case "A to Z":
                itemsList.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item t1, Item t2) {  
                        return t1.getTitle().compareToIgnoreCase(t2.getTitle()); // from A -> Z
                    }
                });
                break;
            case "Z to A":
                itemsList.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item t1, Item t2) {  
                        return t2.getTitle().compareToIgnoreCase(t1.getTitle()); // reverse order of t1 vs t2 to get Z -> A
                    }
                });
                break;
            case "Ascending price":
                itemsList.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item t1, Item t2) {  
                        if(t1.getPrice() < t2.getPrice())
                            return -1;
                        else if (t1.getPrice() > t2.getPrice())
                            return 1;
                        else
                            return 0;
                    }
                });
                break;
            case "Descending price":
                itemsList.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item t1, Item t2) {  //reverse 1 vs -1 of ascending price sort
                        if(t1.getPrice() < t2.getPrice())
                            return 1;
                        else if (t1.getPrice() > t2.getPrice())
                            return -1;
                        else
                            return 0;
                    }
                });
                break;
            case "Best seller":
                itemsList.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item t1, Item t2) {  //reverse 1 vs -1 of ascending price sort
                        if(t1.getUnit_sale() < t2.getUnit_sale())
                            return 1;
                        else if (t1.getUnit_sale() > t2.getUnit_sale())
                            return -1;
                        else
                            return 0;
                    }
                });
                break;
            default:
                break;
        }
        
        page = 0;
        reloadProducts(itemsList, 0);
    }
}
