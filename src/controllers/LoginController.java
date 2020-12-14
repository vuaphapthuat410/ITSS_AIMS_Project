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
import javafx.stage.Modality;
import javafx.stage.Stage;

import models.User;
import data.UserInfo;
import java.sql.ResultSet;
import connectors.UserDB;
import java.sql.SQLException;

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
    private Button btCancel;
    @FXML
    private Button btForget;
    @FXML
    private Button btSignup;
    @FXML
    private PasswordField pfPasswd;
    @FXML
    private CheckBox cdRemember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) throws ClassNotFoundException, SQLException {
        // TODO change this to authorize user in DB using user.authorize(), now just for testing
        Integer loginstatus = UserDB.getLoginUser(tfUname.getText(), pfPasswd.getText());
        if(loginstatus != 0) {
            Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
            statusAlert.setTitle("Success");

            statusAlert.setHeaderText("Login status");
            statusAlert.setContentText("Login successful. Redirecting ...");

            statusAlert.showAndWait();
            
            // Save login info
            UserInfo.saveInfo(tfUname.getText());
            
            // TO DO
            
            try {
                Parent homepage;
                // TO DO : change this
                if(loginstatus == 2)     // admin
                    homepage = FXMLLoader.load(getClass().getClassLoader().getResource("views/adminHome.fxml"));
                else  // 1 means user 
                    homepage = FXMLLoader.load(getClass().getClassLoader().getResource("views/userHome.fxml"));
                //  
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Home screen");
                primaryStage.setScene(new Scene(homepage));
                primaryStage.show();
                
                // Hide login pane
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                System.err.println("Error occurs while trying to  open home screen.");
            }
            
            // TO DO : check radio button remember password
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}
