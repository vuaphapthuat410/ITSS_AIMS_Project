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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import models.User;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class LoginController implements Initializable {

    @FXML
    private Button btLogin;
    @FXML
    private TextField tfUname;
    @FXML
    private CheckBox cbConfirm;
    @FXML
    private Button btCancel;
    @FXML
    private Button btForget;
    @FXML
    private Button btSignup;
    @FXML
    private PasswordField pfPasswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) {
        // TODO change this to authorise user in DB using user.authorize(), now just for testing
        if(tfUname.getText().equals("vuaphapthuat410") && pfPasswd.getText().equals("manhto99") && cbConfirm.isSelected()) {
            Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
            statusAlert.setTitle("Success");

            statusAlert.setHeaderText("Login status");
            statusAlert.setContentText("Login successful. Redirecting ...");

            statusAlert.showAndWait();
            // TO DO
        }
        else {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Login status");
            statusAlert.setContentText("Login unsuccessful. Please try again");

            statusAlert.showAndWait();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void signup(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/signup.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Signup Form");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}
