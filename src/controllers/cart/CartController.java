/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            AnchorPane product1 = FXMLLoader.load(getClass().getClassLoader().getResource("views/cart/productInCart.fxml"));
            AnchorPane product2 = FXMLLoader.load(getClass().getClassLoader().getResource("views/cart/productInCart.fxml"));
            
            productList.addRow(1, product1);
            productList.addRow(2, product2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }    

    @FXML
    private void purchase(ActionEvent event) {
        try {
            Parent orderPane = FXMLLoader.load(getClass().getClassLoader().getResource("views/checkout/addOrderInfo.fxml"));
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
}
