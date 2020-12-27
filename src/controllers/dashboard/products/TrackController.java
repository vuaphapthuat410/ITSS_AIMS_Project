/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Track;
import utils.CheckValidFieldUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class TrackController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDuration;
    @FXML
    private Button btCancel;
    @FXML
    private Button btAdd;
    
    private Track track = null;
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
    private void addTrack(ActionEvent event) {
        if(tfName.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Track status");
            statusAlert.setContentText("No name entered.");

            statusAlert.showAndWait();
        }
        else if(tfDuration.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfDuration.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Track status");
            statusAlert.setContentText("Invalid duration.");

            statusAlert.showAndWait();
        }
        else {
            track = new Track(tfName.getText(), Integer.parseInt(tfDuration.getText()));
            
            Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
            statusAlert.setTitle("Sucessful");

            statusAlert.setHeaderText("Add Track status");
            statusAlert.setContentText("Add track sucessfully.");

            statusAlert.showAndWait();
            
            // close this stage
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
    }
    
    public Track getTrack() {
        return this.track;
    }
    
}
