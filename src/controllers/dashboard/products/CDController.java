/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.products;

import connectors.CDDbUtil;
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
import models.CD;
import models.Track;
import utils.CheckValidFieldUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class CDController implements Initializable {

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
    private TableColumn<Track, String> trackName;
    @FXML
    private TableColumn<Track, Integer> trackTime;
    @FXML
    private TableView<Track> trackList;
    @FXML
    private Button btAddTrack;
    @FXML
    private Button btRemoveTrack;
    
    private ArrayList<Track> tracks = new ArrayList<Track>();
    @FXML
    private Spinner<Integer> stock;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        trackName.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        
        trackName.setOnEditCommit((CellEditEvent<Track,String> e) -> {
            TablePosition<Track,String> pos = e.getTablePosition();
            
            String newTrackName = e.getNewValue();
            
            int row = pos.getRow();
            Track track = e.getTableView().getItems().get(row);
            
            track.setName(newTrackName);
        });
        
        trackTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        cbGenre.getItems().add("Bolero");
        cbGenre.getItems().add("Classical");
        cbGenre.getItems().add("Pop");
        cbGenre.getItems().add("R&B");
        cbGenre.getItems().add("Rap");
        
        stock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 1, 1));
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

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(tracks.isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("No track entered.");

            statusAlert.showAndWait();
        }
        else if(tfArtist.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("No artist entered.");

            statusAlert.showAndWait();
        }
        else if(tfRecordLabel.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("No record label entered.");

            statusAlert.showAndWait();
        }
        else if(datePicker.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("Invalid date.");

            statusAlert.showAndWait();
        }
        else if(cbGenre.getValue().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("No genre selected.");

            statusAlert.showAndWait();
        }
        else if(tfValue.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("Invalid value.");

            statusAlert.showAndWait();
        }
        else if(tfPrice.getText().isEmpty() || !CheckValidFieldUtils.isInteger(tfPrice.getText()) || Integer.parseInt(tfPrice.getText()) < Integer.parseInt(tfValue.getText())) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add CD status");
            statusAlert.setContentText("Invalid price.");

            statusAlert.showAndWait();
        }
        else {
            CD newCD = new CD(tfTitle.getText(), Integer.parseInt(tfValue.getText()), Integer.parseInt(tfPrice.getText()), 0, "CD", "000000", "No description", stock.getValue(), 
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE), 2, 7, 2, 1, tfArtist.getText(), tfRecordLabel.getText(), datePicker.getValue().format(DateTimeFormatter.ISO_DATE), 
                    cbGenre.getValue());
            try {
                boolean status = CDDbUtil.addItem(newCD);
                
                Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
                statusAlert.setTitle("Info");

                statusAlert.setHeaderText("Add CD status");
                statusAlert.setContentText("Create CD sucessfully..");

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
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/products/Track.fxml"));
        Parent root = loader.load();
        TrackController trackController = loader.getController();
        
        Stage stage = new Stage();
        stage.setTitle("Add Track");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        Track newTrack = trackController.getTrack();
        if(newTrack != null) {
            tracks.add(newTrack);
            
            // add to table
            trackList.getItems().add(newTrack);
        }
    }

    @FXML
    private void removeTrack(ActionEvent event) {
        Track delTrack = trackList.getSelectionModel().getSelectedItem();
        if(delTrack != null) {
            tracks.remove(delTrack);
            trackList.getItems().remove(delTrack);
        }
    }
    
}
