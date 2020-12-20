/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.checkout;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Item;
import utils.CheckValidFieldUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AddOrderInfoController implements Initializable {

    @FXML
    private Button btCancel;
    @FXML
    private TextField tfName;
    @FXML
    private Button btSubmit;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private CheckBox ckbDefault;
    @FXML
    private AnchorPane orderInfo;
    
    private HashMap<Item,Integer> items;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toCancel(ActionEvent event) {
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void toSubmit(ActionEvent event) throws IOException {
        if(tfName.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Checkout status");
            statusAlert.setContentText("invalid name.");

            statusAlert.showAndWait();
        }
        
        else if(!CheckValidFieldUtils.isValidPhone(tfPhone.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Checkout status");
            statusAlert.setContentText("Invalid phone number.");

            statusAlert.showAndWait();
        }
        
        else if(tfAddress.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Checkout status");
            statusAlert.setContentText("Invalid address.");

            statusAlert.showAndWait();
        }
        
        else {
            // create invoice here then forward to confirmOrder
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/checkout/confirmOrder.fxml"));
            Parent orderPane =  loader.load();
            ConfirmOrderController orderController = loader.getController();
            // call setter for passing argument
            orderController.setItems(items);
            orderController.setInfo(tfName.getText(), tfPhone.getText(), tfAddress.getText());
            orderController.refresh();
            Stage orderStage = new Stage();
            orderStage.setTitle("Confirm order");
            orderStage.setScene(new Scene(orderPane));
            orderStage.initModality(Modality.APPLICATION_MODAL);
            // show confirmOrder and wait for closing event
            orderStage.showAndWait();
            // end this stage too
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
    }
    
    public void setItems(HashMap<Item,Integer> itemList) {
        items = itemList;
    }
    
}
