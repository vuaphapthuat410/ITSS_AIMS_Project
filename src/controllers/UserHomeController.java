/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import data.UserInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class UserHomeController implements Initializable {

    @FXML
    private Label lbOrder;
    @FXML
    private Label lbCart;
    @FXML
    private Label lbUname;
    @FXML
    private Button btLogOut;
    @FXML
    private TextArea taSearch;
    @FXML
    private RadioButton rbEGood;
    @FXML
    private RadioButton rbPGood;
    @FXML
    private Label lbEbook;
    @FXML
    private ImageView avatar;
    @FXML
    private StackPane mainview;
    
    private ScrollPane orderPane = null;
    private ScrollPane cartPane = null;
    private ScrollPane recommendPane = null;      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbUname.setText(UserInfo.getUname());
        avatar.setImage(new Image("data/avatar.png"));
        // generate recommend pane
        try {
            recommendPane = FXMLLoader.load(getClass().getClassLoader().getResource("views/recommend.fxml"));
            mainview.getChildren().add(recommendPane);
        } catch (IOException ex) {
        }      
    }    

    @FXML
    private void toOrder(MouseEvent event) throws IOException {
        if(orderPane != null)
            orderPane.toFront();
        else {
            orderPane = FXMLLoader.load(getClass().getClassLoader().getResource("views/order.fxml"));
            mainview.getChildren().add(orderPane);
        }
    }

    @FXML
    private void toCart(MouseEvent event) throws IOException {
        if(cartPane != null)
            cartPane.toFront();
        else {
            cartPane = FXMLLoader.load(getClass().getClassLoader().getResource("views/cart.fxml"));
            mainview.getChildren().add(cartPane);
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Signup Form");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.show();
            
            // Hide home screen
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}
