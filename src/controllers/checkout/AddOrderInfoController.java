/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.checkout;

import java.io.IOException;
import java.net.URL;
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
            Parent orderPane = FXMLLoader.load(getClass().getClassLoader().getResource("views/checkout/confirmOrder.fxml"));
            Stage orderStage = new Stage();
            orderStage.setTitle("Confirm order");
            orderStage.setScene(new Scene(orderPane));
            orderStage.initModality(Modality.APPLICATION_MODAL);
            // show confirmOrder and wait for closing event
            orderStage.showAndWait();
        }
    }
    
}
