/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.profile;

import data.UserInfo;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import connectors.UserDB;
import data.UserInfo;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import models.User;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class EditProfileController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Button btSave;
    @FXML
    private Button btUpload;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private Button btCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfName.setPromptText(UserInfo.getUname());
        avatar.setImage(new Image("data/avatar.png"));
    }    

    @FXML
    private void uploadAvatar(ActionEvent event) {
        // Link file then update to DB to BLOB field
    }

    @FXML
    private void toChangePasswd(MouseEvent event) {
        try {
            Parent changePasswd = FXMLLoader.load(getClass().getClassLoader().getResource("views/profile/changePasswd.fxml"));
            //  
            Stage stage = new Stage();
            stage.setTitle("Change password screen");
            stage.setScene(new Scene(changePasswd));
            stage.initModality(Modality.APPLICATION_MODAL);
            //show edit profile screen
            stage.show();
        } catch (IOException e) {
            System.err.println("Error occurs while trying to open change password screen.");
        }
    }

    @FXML
    private void saveEdit(ActionEvent event) throws SQLException, ClassNotFoundException {
        // update to DB
        try {
            UserDB.updateUser(UserInfo.getId(), tfName.getText(), tfEmail.getText(), tfPhone.getText());
        } catch (Exception e) {
            e.printStackTrace();
            
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Update profile unsucessfully");

            statusAlert.setHeaderText("Profile update status");
            statusAlert.setContentText("Your profile has not been updated.");

            statusAlert.showAndWait();
            
            return;
        }
        
        try {
            User upadatedUser = UserDB.reloadLoginUser(UserInfo.getId());
            UserInfo.saveInfo(upadatedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
        statusAlert.setTitle("Update profile sucessfully");

        statusAlert.setHeaderText("Profile update status");
        statusAlert.setContentText("Your profile has been updated.");

        statusAlert.showAndWait();
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
