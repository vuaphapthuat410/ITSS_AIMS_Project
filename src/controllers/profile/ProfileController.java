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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Text lbEmail;
    @FXML
    private Text lbPhone;
    @FXML
    private Text lbAdress;
    @FXML
    private Button btEdit;
    @FXML
    private AnchorPane profileInfo;
    @FXML
    private Text lbName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbName.setText(UserInfo.getName());
        lbEmail.setText(UserInfo.getEmail());
        lbPhone.setText(UserInfo.getPhone());
        // Not set address
        
        
        
        
        avatar.setImage(new Image("data/avatar.png"));
    }    
    
    public void refresh() {
        // TODO
        lbName.setText(UserInfo.getName());
        lbEmail.setText(UserInfo.getEmail());
        lbPhone.setText(UserInfo.getPhone());
        // Not set address
        
        
        
        
        avatar.setImage(new Image("data/avatar.png"));
    }

    @FXML
    private void toEditProfile(ActionEvent event) {
        try {
            Parent editProfile = FXMLLoader.load(getClass().getClassLoader().getResource("views/profile/editProfile.fxml"));
            //  
            Stage stage = new Stage();
            stage.setTitle("Edit Profile screen");
            stage.setScene(new Scene(editProfile));
            stage.initModality(Modality.APPLICATION_MODAL);
            //show edit profile screen
            stage.showAndWait();
            refresh();
        } catch (IOException e) {
            System.err.println("Error occurs while trying to open edit profile screen.");
        }
    }
    
}
