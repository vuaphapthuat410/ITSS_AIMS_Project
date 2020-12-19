/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.profile;

import connectors.UserDB;
import data.UserInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class ChangePasswdController implements Initializable {

    @FXML
    private Button btCancel;
    @FXML
    private Button btSubmit;
    @FXML
    private PasswordField pfNewPasswd;
    @FXML
    private PasswordField pfRePasswd;
    @FXML
    private PasswordField pfOldPasswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toCancel(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void toSubmit(ActionEvent event) {
        try {
            Boolean status = false;
            
            if(pfNewPasswd.getText().equals(pfRePasswd.getText())) {
                status = UserDB.changePasswd(UserInfo.getId(), pfOldPasswd.getText(), pfNewPasswd.getText());
            
                if(status == false) {
                    Alert statusAlert = new Alert(Alert.AlertType.ERROR);
                    statusAlert.setTitle("Change password unsucessfully");

                    statusAlert.setHeaderText("Password change status");
                    statusAlert.setContentText("You enter wrong old password.");

                    statusAlert.showAndWait();
                }
                else {
                    Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                    statusAlert.setTitle("Status");

                    statusAlert.setHeaderText("Password change status");
                    statusAlert.setContentText("Your password has been updated successful.");

                    statusAlert.showAndWait();
                }
            }
            else {
                Alert statusAlert = new Alert(Alert.AlertType.ERROR);
                statusAlert.setTitle("Error");

                statusAlert.setHeaderText("Password change error");
                statusAlert.setContentText("Password is not match.");

                statusAlert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    
}
