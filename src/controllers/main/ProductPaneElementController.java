/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import com.sun.glass.ui.Cursor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import controllers.cart.CartController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class ProductPaneElementController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label lbName;
    @FXML
    private Button btAddToCart;
    @FXML
    private Label lbPrice;
    @FXML
    private Label lbDiscount;
    
    private Item item;
    private CartController cartController;
    private PromoItem discountInfo;
    @FXML
    private Label lbDiscountTag;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setItem(Item anItem) {
        item = anItem;
        imageView.setImage(new Image("data/images/"+Integer.toString(item.getId())+".jpg", 200, 200, false, false));
        lbName.setText(item.getTitle());
        lbPrice.setText(Integer.toString(item.getPrice()));
        lbDiscount.setVisible(false);
        lbDiscountTag.setVisible(false);
    }
    
    public void setPromo(PromoItem promoItem) {
        discountInfo = promoItem;
        lbDiscount.setText(Integer.toString(discountInfo.getRate()) + "%");
        lbDiscount.setVisible(true);
        lbDiscountTag.setVisible(true);
    }
    
    public void setCartController(CartController controller) {
        cartController = controller;
    }

    @FXML
    private void addToCart(ActionEvent event) {
        if(discountInfo != null)
            cartController.addCartElement(item, discountInfo);
        else
            cartController.addCartElement(item, null);
    }

    @FXML
    private void toDetail(MouseEvent event) throws IOException {
        String file_name = null;
        
        if(item instanceof Book) 
            file_name = "Book";
        else if(item instanceof CD)
            file_name = "CD";
        else if(item instanceof DVD)
            file_name = "DVD";
        else if(item instanceof LP)
            file_name= "LP";
        else if(item instanceof Ebook)
            file_name = "Ebook";
        else if(item instanceof Movie)
            file_name = "Movie";
        else if(item instanceof Album)
            file_name = "Album";
        
        if(file_name != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/products/"+file_name+"_detail.fxml"));
            Add_Update_Picker.setItem(item);
            Parent node = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Show " + file_name);
            stage.setScene(new Scene(node));
            stage.show();
        }
    }
}
