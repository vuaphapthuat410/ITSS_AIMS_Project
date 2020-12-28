/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import connectors.DVDDbUtil;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DVD;
import utils.Add_Update_Picker;
import utils.CheckValidFieldUtils;

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
    private ComboBox<String> cbLang;
    @FXML
    private TextField tfValue;
    @FXML
    private TextField tfPrice;
    @FXML
    private ComboBox<String> cbGenre;
    @FXML
    private ComboBox<String> cbSub;
    @FXML
    private Button btCancel;
    @FXML
    private Button btCreate;
    @FXML
    private Spinner<Integer> stock;
    
    private DVD tempDVD = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbSub.getItems().add("English");
        cbSub.getItems().add("Vietnamese");
        cbSub.getItems().add("Japanese");
        
        cbLang.getItems().add("English");
        cbLang.getItems().add("Vietnamese");
        cbLang.getItems().add("Japanese");
        
        cbGenre.getItems().add("Science fiction");
        cbGenre.getItems().add("Action");
        cbGenre.getItems().add("Drama");
        cbGenre.getItems().add("Horror");
        cbGenre.getItems().add("Detective");
        
        stock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 1, 1));
        
        if(Add_Update_Picker.getMode() == 1) {
            tempDVD = (DVD) Add_Update_Picker.getItem();
            loadInfo();
        }
    }    

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void toCreate(ActionEvent event) {
        if(tfTitle.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(tfDirector.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("No author entered.");

            statusAlert.showAndWait();
        }
        else if(tfStudio.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("No publisher entered.");

            statusAlert.showAndWait();
        }
        else if(datePicker.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("Invalid date.");

            statusAlert.showAndWait();
        }
        else if(cbSub.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("No subtitles selected.");

            statusAlert.showAndWait();
        }
        else if(cbLang.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("No language selected.");

            statusAlert.showAndWait();
        }
        else if(cbGenre.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("No genre selected.");

            statusAlert.showAndWait();
        } 
        else if(tfValue.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("Invalid value.");

            statusAlert.showAndWait();
        }
        else if(tfPrice.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPrice.getText()) || Integer.parseInt(tfPrice.getText()) < Integer.parseInt(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add DVD status");
            statusAlert.setContentText("Invalid price.");

            statusAlert.showAndWait();
        }
        else {
            DVD newDVD;
            if(Add_Update_Picker.getMode() == 0)
                newDVD = new DVD(tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "DVD", "000000", "No description", stock.getValue(), 
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE), 2, 7, 2, 1, "Series", tfDirector.getText(), 120, tfStudio.getText(), cbLang.getValue(), cbSub.getValue(), 
                    datePicker.getValue().format(DateTimeFormatter.ISO_DATE), cbGenre.getValue());
            else 
                newDVD = new DVD(tempDVD.getId(), tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "DVD", "000000", "No description", stock.getValue(), 
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE), 2, 7, 2, 1, "Series", tfDirector.getText(), 120, tfStudio.getText(), cbLang.getValue(), cbSub.getValue(), 
                    datePicker.getValue().format(DateTimeFormatter.ISO_DATE), cbGenre.getValue());
            try {
                boolean status;
                if(Add_Update_Picker.getMode() == 0) 
                    status = DVDDbUtil.addItem(newDVD);
                else 
                    status = DVDDbUtil.updateItem(newDVD);
                
                Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                statusAlert.setTitle("Info");

                statusAlert.setHeaderText("Add DVD status");
                statusAlert.setContentText("Create DVD sucessfully..");

                statusAlert.showAndWait();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
            // close this stage
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
    }
    
    public void loadInfo() throws NullPointerException {
        tfTitle.setText(tempDVD.getTitle());
        datePicker.setValue(LocalDate.parse(tempDVD.getPublication_date()));
        cbGenre.setValue(tempDVD.getGenre());
        tfValue.setText(String.valueOf(tempDVD.getValue()));
        tfPrice.setText(String.valueOf(tempDVD.getPrice()));
        stock.getValueFactory().setValue(tempDVD.getQuantity());
        cbLang.setValue(tempDVD.getLanguage());
    }
    
}
