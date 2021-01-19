/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.products;

import data.UserInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.AlbumTrack;
import models.Album;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class Album_detailController implements Initializable {

    @FXML
    private Button btCancel;
    @FXML
    private Label lbTitle;
    @FXML
    private Label lbArtist;
    @FXML
    private Label lbRecordLabel;
    @FXML
    private Label lbDate;
    @FXML
    private Label lbGenre;
    @FXML
    private Label lbValue;
    @FXML
    private Label lbPrice;
    @FXML
    private Label lbUnitSale;
    @FXML
    private ImageView imageView;
    @FXML
    private TableColumn<AlbumTrack, String> trackName;
    @FXML
    private TableColumn<AlbumTrack, Integer> trackTime;
    @FXML
    private TableView<AlbumTrack> trackList;
    @FXML
    private Button btPlay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // 
        trackName.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName.setCellFactory(TextFieldTableCell.<AlbumTrack> forTableColumn());
        
        trackName.setOnEditCommit((TableColumn.CellEditEvent<AlbumTrack,String> e) -> {
            TablePosition<AlbumTrack,String> pos = e.getTablePosition();
            
            String newTrackName = e.getNewValue();
            
            int row = pos.getRow();
            AlbumTrack track = e.getTableView().getItems().get(row);
            
            track.setName(newTrackName);
        });
        
        trackTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        //
        
        if(UserInfo.isAdmin())
            lbValue.setVisible(false);
        
        seeDetail((Album) Add_Update_Picker.getItem());
    }    
    
    public void seeDetail(Album album) {      
        imageView.setImage(new Image("data/images/"+Integer.toString(album.getId())+".jpg", 500, 500, false, false));
        lbTitle.setText(album.getTitle());
        lbArtist.setText(album.getArtist());
        lbRecordLabel.setText(album.getRecord_label());
        lbDate.setText(album.getPublication_date());
        lbGenre.setText(album.getGenre());
        lbValue.setText(String.valueOf(album.getValue()));
        lbPrice.setText(String.valueOf(album.getPrice()));
        lbUnitSale.setText(String.valueOf(album.getUnit_sale()));
        
        trackList.getItems().addAll(album.getTrack_list());
        trackList.setEditable(false);
    }

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void toPlay(ActionEvent event) {
    }
    
}
