/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;

import models.User;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class SignupController implements Initializable {

    @FXML
    private PasswordField pfPasswd;
    @FXML
    private Button btSignup;
    @FXML
    private TextField tfUname;
    @FXML
    private TextField tfPhoneNum;
    @FXML
    private Button btCancel;
    @FXML
    private Button btLogin;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pfRePasswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) {
        if(!pfPasswd.getText().equals(pfRePasswd.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Signup status");
            statusAlert.setContentText("Password is not match. ");

            statusAlert.showAndWait();
        }
        
        // TO DO: create user in DB
        
        // TO DO: back to login pane if true
        //this.back(event);
    }

    @FXML
    private void back(ActionEvent event) {
        // TO DO : this simple hide this pane
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void login(ActionEvent event) {
        // TO DO : this too
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
