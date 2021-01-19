/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import connectors.EbookDbUtil;
import data.UserInfo;
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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Ebook;
import utils.Add_Update_Picker;
import utils.CheckValidFieldUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class EbookController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private ComboBox<String> cbGenre;
    @FXML
    private TextField tfValue;
    @FXML
    private TextField tfPrice;
    @FXML
    private ComboBox<String> cbLang;
    @FXML
    private TextField tfPage;
    @FXML
    private TextField tfContent;
    @FXML
    private Button btCancel;
    @FXML
    private Button btCreate;
    @FXML
    private ComboBox<String> cbCover;
    @FXML
    private TextField tfPublisher;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label windowTitle;
    
    private Ebook tempEbook = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbCover.getItems().add("Paperback");
        cbCover.getItems().add("Hardcover");
        cbCover.getItems().add("Mass Market");
        
        cbLang.getItems().add("English");
        cbLang.getItems().add("Vietnamese");
        cbLang.getItems().add("Japanese");
        
        cbGenre.getItems().add("Science fiction");
        cbGenre.getItems().add("Action");
        cbGenre.getItems().add("Drama");
        cbGenre.getItems().add("Horror");
        cbGenre.getItems().add("Detective");
        
        if(UserInfo.isAdmin()) {
            if(Add_Update_Picker.getMode() == 1) {
                windowTitle.setText("Edit Ebook");
                btCreate.setText("Update");
                tempEbook = (Ebook) Add_Update_Picker.getItem();
                loadInfo();
            }
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

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(tfAuthor.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("No author entered.");

            statusAlert.showAndWait();
        }
        else if(cbCover.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("No cover selected.");

            statusAlert.showAndWait();
        }
        else if(tfPublisher.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("No publisher entered.");

            statusAlert.showAndWait();
        }
        else if(cbGenre.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("No genre selected.");

            statusAlert.showAndWait();
        }
        else if(cbLang.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("No language selected.");

            statusAlert.showAndWait();
        }
        else if(tfPage.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPage.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("Invalid page.");

            statusAlert.showAndWait();
        }
        else if(tfValue.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("Invalid value.");

            statusAlert.showAndWait();
        }
        else if(tfPrice.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPrice.getText()) || Integer.parseInt(tfPrice.getText()) < Integer.parseInt(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("Invalid price.");

            statusAlert.showAndWait();
        }
        else if(datePicker.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("Invalid date.");

            statusAlert.showAndWait();
        }
        else if(tfContent.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Ebook status");
            statusAlert.setContentText("Empty content.");

            statusAlert.showAndWait();
        }
        else {
            Ebook newEbook;
            if(Add_Update_Picker.getMode() == 0) 
                newEbook = new Ebook(tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Ebook", tfAuthor.getText(), cbCover.getValue(), tfPublisher.getText(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    Integer.parseInt(tfPage.getText()), cbLang.getValue(), cbGenre.getValue(), tfContent.getText());
            else
                newEbook = new Ebook(tempEbook.getId(), tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Ebook", tfAuthor.getText(), cbCover.getValue(), tfPublisher.getText(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    Integer.parseInt(tfPage.getText()), cbLang.getValue(), cbGenre.getValue(), tfContent.getText());
            try {
                boolean status;
                if(Add_Update_Picker.getMode() == 0) 
                    status = EbookDbUtil.addItem(newEbook);
                else 
                    status = EbookDbUtil.updateItem(newEbook);
                
                Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                statusAlert.setTitle("Info");

                statusAlert.setHeaderText("Add Ebook status");
                statusAlert.setContentText("Create ebook sucessfully..");

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
        tfTitle.setText(tempEbook.getTitle());
        tfAuthor.setText(tempEbook.getAuthor());
        cbCover.setValue(tempEbook.getCover());
        tfPublisher.setText(tempEbook.getPublisher());
        datePicker.setValue(LocalDate.parse(tempEbook.getPublication_date()));
        cbGenre.setValue(tempEbook.getGenre());
        cbLang.setValue(tempEbook.getLanguage());
        tfPage.setText(String.valueOf(tempEbook.getPage()));
        tfValue.setText(String.valueOf(tempEbook.getValue()));
        tfPrice.setText(String.valueOf(tempEbook.getPrice()));      
        tfContent.setText(tempEbook.getContent());
    }
    
    public void seeDetail(Ebook ebook) {
        tempEbook = ebook;
        loadInfo();
        
        tfTitle.setEditable(false);
        tfAuthor.setEditable(false);
        cbCover.setEditable(false);
        tfPublisher.setEditable(false);
        datePicker.setEditable(false);
        cbGenre.setEditable(false);
        cbLang.setEditable(false);
        tfPage.setEditable(false);
        tfValue.setEditable(false);
        tfPrice.setEditable(false);
        tfContent.setEditable(false);
        
        btCreate.setVisible(false);
    }
}
