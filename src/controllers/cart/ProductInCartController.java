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
    
    private Item item;
    private CartController parentController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setItem(Item anItem) {
        imageView.setImage(new Image("data/not-bug-feature.jpg"));
        name.setText(anItem.getTitle());
        category.setText(anItem.getCategory());
        price.setText(String.valueOf(anItem.getPrice()));
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
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
    
}
