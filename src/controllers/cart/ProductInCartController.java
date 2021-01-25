/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Item;
import controllers.cart.CartController;
import models.Album;
import models.Ebook;
import models.Movie;
import models.PromoItem;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class ProductInCartController implements Initializable {

    @FXML
    private Button btRemove;
    @FXML
    private ImageView imageView;
    @FXML
    private Label name;
    @FXML
    private Label category;
    @FXML
    private Label price;
    @FXML
    private Spinner<Integer> quantity;
    @FXML
    private Label totalPrice;
    @FXML
    private Label lbQuantity;
    @FXML
    private Label lbRate;
    
    
    private Item item;
    private CartController parentController;
    int total;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setItem(Item anItem, PromoItem promoItem) {
        imageView.setImage(new Image("data/images/"+Integer.toString(anItem.getId())+".jpg", 200, 200, false, false));
        name.setText(anItem.getTitle());
        category.setText(anItem.getCategory());
        price.setText(String.valueOf(anItem.getPrice()));
        if(promoItem != null) 
            lbRate.setText(Integer.toString(promoItem.getRate())+"%");
        else 
            lbRate.setText("0%");
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
        quantity.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if(oldValue != newValue) {
                parentController.updateItemQuantity(anItem, Integer.parseInt(newValue));
                totalPrice.setText(calTotal(anItem.getPrice(), quantity.getValue(), promoItem));
                parentController.addTotalPrice(anItem, Float.parseFloat(totalPrice.getText()));
            }
        });
        totalPrice.setText(calTotal(anItem.getPrice(), quantity.getValue(), promoItem));
        parentController.addTotalPrice(anItem, Float.parseFloat(totalPrice.getText()));
        
        if(anItem instanceof Ebook || anItem instanceof Movie || anItem instanceof Album) {
            lbQuantity.setVisible(false);
            quantity.setVisible(false);
        }
            
        item = anItem;
    }
    
    public void setParentControl(CartController controller) {
        parentController = controller;
    }
    
    @FXML
    private void remove(ActionEvent event) {
        GridPane grid = (GridPane) btRemove.getParent().getParent();
        grid.getChildren().remove(btRemove.getParent());
        parentController.removeItemFromCart(item);
    }
    
    public String calTotal(Integer price, Integer quantity, PromoItem promo) {
        if(promo != null) {
            int quantitySaleItem = promo.getLimit_quantity() - promo.getSold();
            if(quantity < quantitySaleItem)
                total = price*quantity*(100-promo.getRate())/100;
            else 
                total = price*quantitySaleItem*(100-promo.getRate())/100 + price*(quantity - quantitySaleItem);
        }
        else 
            total = price*quantity;

        return String.valueOf(total);
    }
    
}
