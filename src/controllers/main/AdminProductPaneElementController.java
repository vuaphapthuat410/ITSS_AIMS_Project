/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import connectors.helper.DeleteItemHelper;
import data.ControllerUtils;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Item;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AdminProductPaneElementController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label lbName;
    @FXML
    private Button btEdit;
    @FXML
    private Button btRemove;
    
    private Item item;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editItem(ActionEvent event) {
        // TO DO: 
    }

    @FXML
    private void removeItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        // TO DO: Need to check if there is some order contains this item, how can it reference to its info ?
        Alert removeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        removeAlert.setTitle("Remove item");

        removeAlert.setHeaderText("Are you sure want to remove this item from system?");
        removeAlert.setContentText(item.getTitle());

        Optional<ButtonType> option = removeAlert.showAndWait();
        
        if (option.get() == ButtonType.OK) {
            DeleteItemHelper.deleteItem(item.getId()); // because of delete cascade of MySQL, delete from item table will make delete from other inherited table
                                                        // cant check if delete done cuz Vinh not set it as boolean return type
            
            removeAlert = new Alert(Alert.AlertType.INFORMATION);
            removeAlert.setTitle("Remove item");

            removeAlert.setHeaderText("Status: ");
            removeAlert.setContentText("Delete item:" + item.getTitle() + "successful");
        }
        ControllerUtils.refreshProductOnlyForAdmin();
    }
    
    public void setItem(Item anItem) {
        item = anItem;
        imageView.setImage(new Image("data/not-bug-feature.jpg"));
        lbName.setText(item.getTitle());
    }
}
