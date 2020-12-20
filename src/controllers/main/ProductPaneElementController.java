/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

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
import models.Item;

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
    
    private Item item;
    private CartController cartController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setItem(Item anItem) {
        item = anItem;
        imageView.setImage(new Image("data/not-bug-feature.jpg"));
        lbName.setText(item.getTitle());
    }
    
    public void setCartController(CartController controller) {
        cartController = controller;
    }

    @FXML
    private void addToCart(ActionEvent event) {
        cartController.addCartElement(item);
    }
}
