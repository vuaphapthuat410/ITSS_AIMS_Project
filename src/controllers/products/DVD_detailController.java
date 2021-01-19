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
import models.DVD;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class DVD_detailController implements Initializable {

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
    private Label lbStock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(UserInfo.isAdmin())
            lbValue.setVisible(false);
        
        seeDetail((DVD) Add_Update_Picker.getItem());
    }    
    
    public void seeDetail(DVD dvd) {      
        imageView.setImage(new Image("data/images/"+Integer.toString(dvd.getId())+".jpg", 500, 500, false, false));
        lbTitle.setText(dvd.getTitle());
        lbDirector.setText(dvd.getDirector());
        lbStudio.setText(dvd.getStudio());
        lbDate.setText(dvd.getPublication_date());
        lbGenre.setText(dvd.getGenre());
        lbValue.setText(String.valueOf(dvd.getValue()));
        lbPrice.setText(String.valueOf(dvd.getPrice()));
        lbStock.setText(String.valueOf(dvd.getQuantity()));
        lbLang.setText(dvd.getLanguage());
        lbSub.setText(dvd.getSubtitle());
        lbUnitSale.setText(String.valueOf(dvd.getUnit_sale()));
    }

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }
    
}
