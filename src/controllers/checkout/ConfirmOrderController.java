/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.checkout;

import connectors.OrderDB;
import data.ControllerUtils;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Item;
import models.Order;
import data.UserInfo;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class ConfirmOrderController implements Initializable {

    @FXML
    private Button btBack;
    @FXML
    private Button btSubmit;
    @FXML
    private Label lbName;
    @FXML
    private Label lbPhone;
    @FXML
    private Text lbAddress;
    @FXML
    private Label lbShipFee;
    @FXML
    private Label lbTotal;
    
    private HashMap<Item,Integer> items;
    private String name;
    private String phone;
    private String address;
    private Integer total = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void toBack(ActionEvent event) {
        Stage stage = (Stage) btBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void submit(ActionEvent event) throws ClassNotFoundException, SQLException {
        Order order = new Order(0, UserInfo.getId(), name, address, phone, total.floatValue(), generateUUID(total), 1, getDate());
        boolean status = OrderDB.createOrder(order, items);
        if(status == true) {
            System.out.println("Create order sucessfully");
        }
        else 
            System.out.println("Can't create order.");
        // exit
        Stage stage = (Stage) btBack.getScene().getWindow();
        stage.close();
        ControllerUtils.refresh();
    }
    
    public void setItems(HashMap<Item,Integer> itemList) {
        items = itemList;
    }
    
    public void setInfo(String aName, String aPhoneNum, String anAdress) {
        name = aName;
        phone = aPhoneNum;
        address = anAdress;
    }
    
    public Integer generateUUID(Integer key) {
        return name.hashCode() + address.hashCode() + phone.hashCode() + key;
    }
    
    public Date getDate() {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        return date;
    }
    
    public void refresh() {
        for(Map.Entry<Item,Integer> item : items.entrySet()) {
            total = total + (item.getKey().getPrice())*(item.getValue());
        }
        lbName.setText(name);
        lbPhone.setText(phone);
        lbAddress.setText(address);
        lbTotal.setText(total.toString());
    }
    
}
