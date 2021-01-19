/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import connectors.AlbumDbUtil;
import data.UserInfo;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Album;
import models.AlbumTrack;
import utils.Add_Update_Picker;
import utils.CheckValidFieldUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AlbumController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfArtist;
    @FXML
    private TextField tfRecordLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> cbGenre;
    @FXML
    private TextField tfValue;
    @FXML
    private TextField tfPrice;
    @FXML
    private Button btCancel;
    @FXML
    private Button btCreate;
    @FXML
    private TableColumn<AlbumTrack, String> trackName;
    @FXML
    private TableColumn<AlbumTrack, Integer> trackTime;
    @FXML
    private TableView<AlbumTrack> trackList;
    @FXML
    private Button btAddTrack;
    @FXML
    private Button btRemoveTrack;
    @FXML
    private Label windowTitle;
    
    private ArrayList<AlbumTrack> tracks = new ArrayList<AlbumTrack>();
    
    private Album tempAlbum = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        trackName.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName.setCellFactory(TextFieldTableCell.<AlbumTrack> forTableColumn());
        
        trackName.setOnEditCommit((CellEditEvent<AlbumTrack,String> e) -> {
            TablePosition<AlbumTrack,String> pos = e.getTablePosition();
            
            String newTrackName = e.getNewValue();
            
            int row = pos.getRow();
            AlbumTrack track = e.getTableView().getItems().get(row);
            
            track.setName(newTrackName);
        });
        
        trackTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        cbGenre.getItems().add("Bolero");
        cbGenre.getItems().add("Classical");
        cbGenre.getItems().add("Pop");
        cbGenre.getItems().add("R&B");
        cbGenre.getItems().add("Rap");
        
        if(UserInfo.isAdmin()) {
            if(Add_Update_Picker.getMode() == 1) {
                windowTitle.setText("Edit Album");
                btCreate.setText("Update");
                tempAlbum = (Album) Add_Update_Picker.getItem();
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

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(tracks.isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("No track entered.");

            statusAlert.showAndWait();
        }
        else if(tfArtist.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("No artist entered.");

            statusAlert.showAndWait();
        }
        else if(tfRecordLabel.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("No record label entered.");

            statusAlert.showAndWait();
        }
        else if(datePicker.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("Invalid date.");

            statusAlert.showAndWait();
        }
        else if(cbGenre.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("No genre selected.");

            statusAlert.showAndWait();
        }
        else if(tfValue.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("Invalid value.");

            statusAlert.showAndWait();
        }
        else if(tfPrice.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPrice.getText()) || Integer.parseInt(tfPrice.getText()) < Integer.parseInt(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Album status");
            statusAlert.setContentText("Invalid price.");

            statusAlert.showAndWait();
        }
        else {
            Album newAlbum;
            if(Add_Update_Picker.getMode() == 0) 
                newAlbum = new Album(tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Album", tfArtist.getText(), tfRecordLabel.getText(), 
                    datePicker.getValue().format(DateTimeFormatter.ISO_DATE), cbGenre.getValue(), tracks);
            else 
                newAlbum = new Album(tempAlbum.getId(), tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "Album", tfArtist.getText(), tfRecordLabel.getText(), 
                    datePicker.getValue().format(DateTimeFormatter.ISO_DATE), cbGenre.getValue(), tracks);
            try {
                boolean status;
                if(Add_Update_Picker.getMode() == 0) 
                    status = AlbumDbUtil.addItem(newAlbum);
                else
                    status = AlbumDbUtil.updateItem(newAlbum);
                
                Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                statusAlert.setTitle("Info");

                statusAlert.setHeaderText("Add Album status");
                statusAlert.setContentText("Create Album sucessfully..");

                statusAlert.showAndWait();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
            // close this stage
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void addTrack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/products/AlbumTrack.fxml"));
        Parent root = loader.load();
        AlbumTrackController trackController = loader.getController();
        
        Stage stage = new Stage();
        stage.setTitle("Add AlbumTrack");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        AlbumTrack newTrack = trackController.getTrack();
        if(newTrack != null) {
            tracks.add(newTrack);
            
            // add to table
            trackList.getItems().add(newTrack);
        }
    }

    @FXML
    private void removeTrack(ActionEvent event) {
        AlbumTrack delTrack = trackList.getSelectionModel().getSelectedItem();
        if(delTrack != null) {
            tracks.remove(delTrack);
            trackList.getItems().remove(delTrack);
        }
    }
    
    public void loadInfo() throws NullPointerException {
        tracks = tempAlbum.getTrack_list();
        
        tfTitle.setText(tempAlbum.getTitle());
        trackList.getItems().addAll(tracks);
        tfArtist.setText(tempAlbum.getArtist());
        tfRecordLabel.setText(tempAlbum.getRecord_label());
        datePicker.setValue(LocalDate.parse(tempAlbum.getPublication_date()));
        cbGenre.setValue(tempAlbum.getGenre());
        tfValue.setText(String.valueOf(tempAlbum.getValue()));
        tfPrice.setText(String.valueOf(tempAlbum.getPrice()));
    }
    
    public void seeDetail(Album album) {
        tempAlbum = album;
        loadInfo();
        
        tfTitle.setEditable(false);
        trackList.setEditable(false);
        tfArtist.setEditable(false);
        tfRecordLabel.setEditable(false);
        datePicker.setEditable(false);
        cbGenre.setEditable(false);
        tfValue.setEditable(false);
        tfPrice.setEditable(false);
        
        btCreate.setVisible(false);
        btAddTrack.setVisible(false);
        btRemoveTrack.setVisible(false);
    }
}
