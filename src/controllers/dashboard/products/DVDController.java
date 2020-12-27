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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class DVDController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDirector;
    @FXML
    private TextField tfStudio;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<?> cbLang;
    @FXML
    private TextField tfValue;
    @FXML
    private TextField tfPrice;
    @FXML
    private Spinner<?> unitSale;
    @FXML
    private ComboBox<?> cbGenre;
    @FXML
    private ComboBox<?> cbSub;
    @FXML
    private Button btCancel;
    @FXML
    private Button btCreate;

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
    private void toCreate(ActionEvent event) {
    }
    
}