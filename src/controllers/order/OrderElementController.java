/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.order;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import models.Order;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class OrderElementController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label lbPrice;
    @FXML
    private Button btCancel;
    @FXML
    private Label lbStatus;
    @FXML
    private Label lbDate;
    
    private Order order;
    @FXML
    private Label lbOrderId;
    @FXML
    private Label lbTrackId;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void cancelOrder(ActionEvent event) {
        // TO DO; change order status then update to DB
    }
    
    private String getStatus(Integer statusNum) {
        if(statusNum == 1) 
            return "Preparing";
        else if(statusNum == 2)
            return "Delivering";
        else if(statusNum == 3)
            return "Complete";
        else
            return "Cancel";
    }
    
    public void setOrder(Order anOrder) {
        imageView.setImage(new Image("data/not-bug-feature.jpg"));
        lbOrderId.setText(anOrder.getId().toString());
        lbTrackId.setText(anOrder.getTrackingId().toString());
        lbPrice.setText(anOrder.getTotal().toString());
        lbStatus.setText(getStatus(order.getOrderStatus()));
        lbDate.setText(order.getDate().toString());
    }
    
}
