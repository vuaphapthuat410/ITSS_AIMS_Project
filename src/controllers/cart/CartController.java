/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import controllers.checkout.AddOrderInfoController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Item;
import models.PromoItem;
import utils.checkOutUtils;
import static utils.checkOutUtils.totalPrice;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class CartController implements Initializable {

    @FXML
    private Button btPurchase;
    @FXML
    private ComboBox<?> cbSortType;
    @FXML
    private GridPane productList;
    
    private HashMap<Item,Integer> items = new HashMap<Item,Integer>();
    private HashMap<Item,Float> totalPrice = new HashMap<Item,Float>();
    @FXML
    private Label lbTotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void addCartElement(Item item, PromoItem promoItem) {
        Integer index = productList.getChildren().size();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/cart/productInCart.fxml"));
            AnchorPane cartElement = loader.load();
            ProductInCartController cartElementController= loader.getController();
            cartElementController.setParentControl(this);   // to have privilege to invoke remove item from cart
            cartElementController.setItem(item, promoItem);
            productList.addRow(index, cartElement);
            items.put(item, 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        lbTotal.setText(String.valueOf(calTotalPrice()));
    }
    
    public void removeItemFromCart(Item item) {
        items.remove(item);
        totalPrice.remove(item);
        
        lbTotal.setText(String.valueOf(calTotalPrice()));
    }
    
    public void addTotalPrice(Item item, Float price) {
        if(totalPrice.containsKey(item))
            totalPrice.replace(item, price);
        else 
            totalPrice.put(item, price);
        
        System.out.println(totalPrice.get(item));
        lbTotal.setText(String.valueOf(calTotalPrice()));
    }
    
    @FXML
    private void purchase(ActionEvent event) {
        if(items.isEmpty())
            return;
        
        checkOutUtils.setTotalPrice(totalPrice);
        
        try {
            //checkOutUtils.setTotalPrice(totalPrice);
            
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/checkout/addOrderInfo.fxml"));
            Parent orderPane  = loader.load();
            AddOrderInfoController invoiceController = loader.getController();
            invoiceController.setItems(items);
            
            Stage orderStage = new Stage();
            orderStage.setTitle("Order info");
            orderStage.setScene(new Scene(orderPane));
            // show confirmOrder and wait for closing event
            orderStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot open purchase screen.");
        }
    }
    
    public void updateItemQuantity(Item item, Integer quantity) {
        items.replace(item, quantity);
    }
    
    public void clear() {
        productList.getChildren().clear();
        items.clear();
    }
    
    public Float calTotalPrice() {
        Float total = 0f;
        for(Map.Entry<Item, Float> item : totalPrice.entrySet()) {
            total = total + item.getValue();
        }
        return total;
    }
}
