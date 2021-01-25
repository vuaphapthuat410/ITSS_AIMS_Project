/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.promos;

import connectors.ItemDbUtil;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Item;
import models.PhysicalGood;
import models.PromoItem;
import utils.CheckValidFieldUtils;
import utils.AdminUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class PromoItemController implements Initializable {
    @FXML
    private TextField tfID;
    @FXML
    private Button btCancel;
    @FXML
    private Button btAdd;
    @FXML
    private Label lbTitle;
    @FXML
    private Label lbValid;
    @FXML
    private Button btCheck;
    @FXML
    private TextField tfLimit;
    
    private PromoItem item;
    private boolean valid = false;
    private String validID = null;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addItem(ActionEvent event) {
        if(valid != true || !validID.equals(tfID.getText())) { // not check for valid or change id after validate
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add PromoItem");
            statusAlert.setContentText("Invalid item.");

            statusAlert.showAndWait();
            // reset condition
            valid = false;
            validID = null;
            item = null;
        }
        else {            
            Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
            statusAlert.setTitle("Sucessful");

            statusAlert.setHeaderText("Add PromoItem status");
            statusAlert.setContentText("Add item sucessfully.");

            statusAlert.showAndWait();
            
            // close this stage
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
        
    }

    @FXML
    private void toCheck(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(tfID.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfID.getText())) { // not enter id or invalid input
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add PromoItem");
            statusAlert.setContentText("Invalid input ID.");

            statusAlert.showAndWait();
            
            lbTitle.setText("Not found");
            lbValid.setText("Invalid");
        }
        else if(tfLimit.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfLimit.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add PromoItem");
            statusAlert.setContentText("Invalid limit.");

            statusAlert.showAndWait();
            
            lbTitle.setText("Not found");
            lbValid.setText("Invalid");
        }
        else {
            Item tempItem = AdminUtils.getItemById(Integer.parseInt(tfID.getText()));
            if(tempItem != null) {
                if(tempItem instanceof PhysicalGood) {
                    if(Integer.valueOf(tfLimit.getText()) > ((PhysicalGood) tempItem).getQuantity()) {
                        Alert statusAlert = new Alert(Alert.AlertType.ERROR);
                        statusAlert.setTitle("Error");

                        statusAlert.setHeaderText("Add PromoItem");
                        statusAlert.setContentText("Not enough for promotion.");

                        statusAlert.showAndWait();
                        
                        lbTitle.setText("Not found");
                        lbValid.setText("Invalid");
                        return;
                    }
                }
                
                lbTitle.setText(tempItem.getTitle());
                lbValid.setText("Valid");
                valid = true;
                validID = String.valueOf(tempItem.getId());
                item = new PromoItem(tempItem.getId(), tempItem.getTitle(), tempItem.getValue(), tempItem.getPrice(), 0, Integer.parseInt(tfLimit.getText()), 0); // remeber to change 3 last value
            }
            else {
                lbTitle.setText("Not found");
                lbValid.setText("Invalid");
            }
        }
        
    }
    
        
    public PromoItem getItem() {
        return this.item;
    } 
}
