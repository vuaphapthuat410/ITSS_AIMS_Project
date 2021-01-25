/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.checkout;

import connectors.OrderDB;
import controllers.order.CreditCardAPI;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.checkOutUtils;

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
    private String city;
    private Float total = 0f;
    @FXML
    private TextField tfCardID;
    @FXML
    private TextField tfPass;
    @FXML
    private Label lbtotalCost;
    
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
        if(tfCardID.getText().isEmpty() || !tfCardID.getText().equals("123456abc") || tfPass.getText().isEmpty() || !tfPass.getText().equals("123456")) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Checkout status");
            statusAlert.setContentText("Invalid card id/pass.");

            statusAlert.showAndWait();
            
            return;
        }
        
        Order order = new Order(0, UserInfo.getId(), name, address, phone, total, generateUUID(total.intValue()), 1, getDate());
        boolean status = OrderDB.createOrder(order, items);
        if(status == true) {
            System.out.println("Create order sucessfully");
            CreditCardAPI card = new CreditCardAPI();
            card.charge("123456abc", "123456", total.intValue());
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
    
    public void setInfo(String aName, String aPhoneNum, String anAdress, String aCity) {
        name = aName;
        phone = aPhoneNum;
        address = anAdress;
        city = aCity;
        
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
        HashMap<Item,Float> totalPrice = checkOutUtils.getTotalPrice();
        if(totalPrice.isEmpty())
            System.out.println("No total price");
        
        for(Map.Entry<Item, Float> item : totalPrice.entrySet()) {
            total = total + item.getValue();
        }
        
        total = total + calShipFee();
        
        lbName.setText(name);
        lbPhone.setText(phone);
        lbAddress.setText(address);
        lbTotal.setText(total.toString());
        lbShipFee.setText(String.valueOf(calShipFee()));
    }
    
    public Integer calShipFee() {
        if(city.equals("HaNoi"))
            return 10;
        else if(city.equals("TPHCM"))
            return 20;
        else if(city.equals("ThaiBinh"))
            return 15;
        else if(city.equals("HaiPhong"))
            return 12;
        
        return 30;
    }
        
    
}
