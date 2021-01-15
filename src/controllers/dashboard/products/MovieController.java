/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import connectors.MovieDbUtil;
import data.UserInfo;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import models.Movie;
import utils.Add_Update_Picker;
import utils.CheckValidFieldUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class MovieController implements Initializable {

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
    private TextField tfActors;
    @FXML
    private TextField tfWriters;
    @FXML
    private Label windowTitle;
    
    private Movie tempMovie = null;
    
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
        
        if(Add_Update_Picker.getMode() == 0)
            seeDetail((Movie) Add_Update_Picker.getItem());
        
        if(UserInfo.isAdmin()) {
            if(Add_Update_Picker.getMode() == 1) {
                windowTitle.setText("Edit Movie");
                btCreate.setText("Update");
                tempMovie = (Movie) Add_Update_Picker.getItem();
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

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(tfDirector.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("No director entered.");

            statusAlert.showAndWait();
        }
        else if(tfStudio.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("No studio entered.");

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

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("No language selected.");

            statusAlert.showAndWait();
        }
        else if(cbGenre.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("No genre selected.");

            statusAlert.showAndWait();
        }       
        else if(tfValue.getText().isEmpty() || !CheckValidFieldUtils.isNumeric(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("Invalid value.");

            statusAlert.showAndWait();
        }
        else if(tfPrice.getText().isEmpty() || !CheckValidFieldUtils.isNumeric(tfPrice.getText()) || Integer.parseInt(tfPrice.getText()) < Integer.parseInt(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("Invalid price.");

            statusAlert.showAndWait();
        }
        else if(datePicker.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Movie status");
            statusAlert.setContentText("Invalid date.");

            statusAlert.showAndWait();
        }
        else {
            Movie newMovie;
            if(Add_Update_Picker.getMode() == 0) 
                newMovie = new Movie(tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Movie", "Series", tfDirector.getText(), 120, tfStudio.getText(), cbLang.getValue(), cbSub.getValue(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    cbGenre.getValue(), new ArrayList<String>(Arrays.asList(tfActors.getText().split(","))), new ArrayList<String>(Arrays.asList(tfWriters.getText().split(","))));
            else
                newMovie = new Movie(tempMovie.getId(), tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Movie", "Series", tfDirector.getText(), 120, tfStudio.getText(), cbLang.getValue(), cbSub.getValue(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    cbGenre.getValue(), new ArrayList<String>(Arrays.asList(tfActors.getText().split(","))), new ArrayList<String>(Arrays.asList(tfWriters.getText().split(","))));
            try {
                boolean status;
                if(Add_Update_Picker.getMode() == 0) 
                    status = MovieDbUtil.addItem(newMovie);
                else
                    status = MovieDbUtil.updateItem(newMovie);
                
                Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                statusAlert.setTitle("Info");

                statusAlert.setHeaderText("Add Movie status");
                statusAlert.setContentText("Create movie sucessfully..");

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
        tfTitle.setText(tempMovie.getTitle());
        tfDirector.setText(tempMovie.getDirector());
        tfStudio.setText(tempMovie.getStudio());
        datePicker.setValue(LocalDate.parse(tempMovie.getPublication_date()));
        cbSub.setValue(tempMovie.getSubtitle());
        cbLang.setValue(tempMovie.getLanguage());
        cbGenre.setValue(tempMovie.getGenre());
        tfValue.setText(String.valueOf(tempMovie.getValue()));
        tfPrice.setText(String.valueOf(tempMovie.getPrice()));
        tfActors.setText(String.join(",", tempMovie.getActors()));
        tfWriters.setText(String.join(",", tempMovie.getWriters()));
    }
    
    public void seeDetail(Movie movie) {
        tempMovie = movie;
        loadInfo();
        
        tfTitle.setEditable(false);
        tfDirector.setEditable(false);
        tfStudio.setEditable(false);
        datePicker.setEditable(false);
        cbSub.setEditable(false);
        cbLang.setEditable(false);
        cbGenre.setEditable(false);
        tfValue.setEditable(false);
        tfPrice.setEditable(false);
        tfActors.setEditable(false);
        tfWriters.setEditable(false);
        
        btCreate.setVisible(false);
    }
}
