/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import connectors.BookDbUtil;
import data.UserInfo;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import models.Book;
import utils.CheckValidFieldUtils;
import utils.Add_Update_Picker;
/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class BookController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfPublisher;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> cbGenre;
    @FXML
    private TextField tfValue;
    @FXML
    private TextField tfPrice;
    @FXML
    private Spinner<Integer> stock;
    @FXML
    private ComboBox<String> cbLang;
    @FXML
    private TextField tfPage;
    @FXML
    private Button btCancel;
    @FXML
    private Button btCreate;
    @FXML
    private ComboBox<String> cbCover;
    @FXML
    private Label windowTitle;
    
    private Book tempBook = null;
    
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

        stock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 1, 1));
        
        if(Add_Update_Picker.getMode() == 0)
            seeDetail((Book) Add_Update_Picker.getItem());     
        
        if(UserInfo.isAdmin()) {
            if(Add_Update_Picker.getMode() == 1) {
                windowTitle.setText("Edit Book");
                btCreate.setText("Update");
                tempBook = (Book) Add_Update_Picker.getItem();
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

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(tfAuthor.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("No author entered.");

            statusAlert.showAndWait();
        }
        else if(cbCover.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("No cover selected.");

            statusAlert.showAndWait();
        }
        else if(tfPublisher.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("No publisher entered.");

            statusAlert.showAndWait();
        }
        else if(cbGenre.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("No genre selected.");

            statusAlert.showAndWait();
        }
        else if(cbLang.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("No language selected.");

            statusAlert.showAndWait();
        }
        else if(tfPage.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPage.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("Invalid page.");

            statusAlert.showAndWait();
        }
        else if(tfValue.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("Invalid value.");

            statusAlert.showAndWait();
        }
        else if(tfPrice.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPrice.getText()) || Integer.parseInt(tfPrice.getText()) < Integer.parseInt(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("Invalid price.");

            statusAlert.showAndWait();
        }
        else if(datePicker.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Book status");
            statusAlert.setContentText("Invalid date.");

            statusAlert.showAndWait();
        }
        else {
            Book newBook;
            if(Add_Update_Picker.getMode() == 0)
                newBook = new Book(tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Book", "000000", "No description", stock.getValue(), 
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE), 2, 7, 2, 1, tfAuthor.getText(), cbCover.getValue(), tfPublisher.getText(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    Integer.parseInt(tfPage.getText()), cbLang.getValue(), cbGenre.getValue());
            else
                newBook = new Book(tempBook.getId(), tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Book", "000000", "No description", stock.getValue(), 
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE), 2, 7, 2, 1, tfAuthor.getText(), cbCover.getValue(), tfPublisher.getText(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    Integer.parseInt(tfPage.getText()), cbLang.getValue(), cbGenre.getValue());
            
            try {
                boolean status = false;
                if(Add_Update_Picker.getMode() == 0)
                    status = BookDbUtil.addItem(newBook);
                else if(Add_Update_Picker.getMode() == 1) 
                    status = BookDbUtil.updateItem(newBook);
                    
                
                if(status == true) {
                    Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                    statusAlert.setTitle("Info");

                    statusAlert.setHeaderText("Add/Update Book status");
                    statusAlert.setContentText("Create/Update book sucessfully..");

                    statusAlert.showAndWait();
                }
                else {
                    Alert statusAlert = new Alert(Alert.AlertType.ERROR);
                    statusAlert.setTitle("Error");

                    statusAlert.setHeaderText("Add/Update Book status");
                    statusAlert.setContentText("Create/Update book unsucessfully..Error occurs");

                    statusAlert.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
            // close this stage
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
        
    }
    
    public void loadInfo() throws NullPointerException {
        tfTitle.setText(tempBook.getTitle());
        tfAuthor.setText(tempBook.getAuthor());
        tfPublisher.setText(tempBook.getPublisher());
        datePicker.setValue(LocalDate.parse(tempBook.getPublication_date()));
        cbGenre.setValue(tempBook.getGenre());
        tfValue.setText(String.valueOf(tempBook.getValue()));
        tfPrice.setText(String.valueOf(tempBook.getPrice()));
        stock.getValueFactory().setValue(tempBook.getQuantity());
        cbLang.setValue(tempBook.getLanguage());
        tfPage.setText(String.valueOf(tempBook.getPage()));
        cbCover.setValue(tempBook.getCover());
    }
    
    public void seeDetail(Book book) {
        tempBook = book;
        loadInfo();
        
        tfTitle.setEditable(false);
        tfAuthor.setEditable(false);
        tfPublisher.setEditable(false);
        datePicker.setEditable(false);
        cbGenre.setEditable(false);
        tfValue.setEditable(false);
        tfPrice.setEditable(false);
        stock.setEditable(false);
        cbLang.setEditable(false);
        tfPage.setEditable(false);
        cbCover.setEditable(false);
        
        btCreate.setVisible(false);
    }
}
