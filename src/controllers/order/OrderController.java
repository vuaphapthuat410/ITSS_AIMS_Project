/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.order;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import controllers.order.OrderElementController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import models.Order;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class OrderController implements Initializable {

    @FXML
    private ComboBox<?> cbxSort;
    @FXML
    private GridPane orderList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void addOrderElement(Order order) {
        Integer index = orderList.getChildren().size();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/order/orderElement.fxml"));
            AnchorPane orderElement = loader.load();
            OrderElementController orderElementController= loader.getController();
            orderElementController.setOrder(order);
            orderList.addRow(index, orderElement);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
