/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.products;

import data.UserInfo;
import java.net.URL;
import java.util.ArrayList;
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
import models.LP;
import models.Track;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class LP_detailController implements Initializable {

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
    private TableColumn<Track, String> trackName;
    @FXML
    private TableColumn<Track, Integer> trackTime;
    @FXML
    private TableView<Track> trackList;
    @FXML
    private Label lbStock;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // 
        trackName.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        
        trackName.setOnEditCommit((TableColumn.CellEditEvent<Track,String> e) -> {
            TablePosition<Track,String> pos = e.getTablePosition();
            
            String newTrackName = e.getNewValue();
            
            int row = pos.getRow();
            Track track = e.getTableView().getItems().get(row);
            
            track.setName(newTrackName);
        });
        
        trackTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        //
        
        if(UserInfo.isAdmin())
            lbValue.setVisible(false);
        
        seeDetail((LP) Add_Update_Picker.getItem());
    }    
    
    public void seeDetail(LP lp) {      
        imageView.setImage(new Image("data/images/"+Integer.toString(lp.getId())+".jpg", 500, 500, false, false));
        lbTitle.setText(lp.getTitle());
        lbArtist.setText(lp.getArtist());
        lbRecordLabel.setText(lp.getRecord_label());
        lbDate.setText(lp.getPublication_date());
        lbGenre.setText(lp.getGenre());
        lbValue.setText(String.valueOf(lp.getValue()));
        lbPrice.setText(String.valueOf(lp.getPrice()));
        lbStock.setText(String.valueOf(lp.getQuantity()));
        lbUnitSale.setText(String.valueOf(lp.getUnit_sale()));
        
        trackList.getItems().addAll(lp.getTrack_list());
        trackList.setEditable(false);
    }

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }
    
}
