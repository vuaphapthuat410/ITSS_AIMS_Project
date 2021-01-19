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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Movie;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class Movie_detailController implements Initializable {

    @FXML
    private Button btCancel;
    @FXML
    private Label lbTitle;
    @FXML
    private Label lbDirector;
    @FXML
    private Label lbStudio;
    @FXML
    private Label lbDate;
    @FXML
    private Label lbLang;
    @FXML
    private Label lbGenre;
    @FXML
    private Label lbSub;
    @FXML
    private Label lbPrice;
    @FXML
    private Label lbUnitSale;
    @FXML
    private Label lbValue;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btPlay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(UserInfo.isAdmin())
            lbValue.setVisible(false);
        
        seeDetail((Movie) Add_Update_Picker.getItem());
    }    
    
    public void seeDetail(Movie movie) {      
        imageView.setImage(new Image("data/images/"+Integer.toString(movie.getId())+".jpg", 500, 500, false, false));
        lbTitle.setText(movie.getTitle());
        lbDirector.setText(movie.getDirector());
        lbStudio.setText(movie.getStudio());
        lbDate.setText(movie.getPublication_date());
        lbGenre.setText(movie.getGenre());
        lbValue.setText(String.valueOf(movie.getValue()));
        lbPrice.setText(String.valueOf(movie.getPrice()));
        lbLang.setText(movie.getLanguage());
        lbSub.setText(movie.getSubtitle());
        lbUnitSale.setText(String.valueOf(movie.getUnit_sale()));
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
