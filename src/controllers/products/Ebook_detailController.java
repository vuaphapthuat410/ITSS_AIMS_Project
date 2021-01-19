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
import models.Ebook;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class Ebook_detailController implements Initializable {

    @FXML
    private Button btCancel;
    @FXML
    private Label lbTitle;
    @FXML
    private Label lbAuthor;
    @FXML
    private Label lbCover;
    @FXML
    private Label lbPublisher;
    @FXML
    private Label lbDate;
    @FXML
    private Label lbGenre;
    @FXML
    private Label lbLang;
    @FXML
    private Label lbPages;
    @FXML
    private Label lbValue;
    @FXML
    private Label lbPrice;
    @FXML
    private Label lbUnitSale;
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
        
        seeDetail((Ebook) Add_Update_Picker.getItem());
    }    
    
    public void seeDetail(Ebook ebook) {      
        imageView.setImage(new Image("data/images/"+Integer.toString(ebook.getId())+".jpg", 500, 500, false, false));
        lbTitle.setText(ebook.getTitle());
        lbAuthor.setText(ebook.getAuthor());
        lbPublisher.setText(ebook.getPublisher());
        lbDate.setText(ebook.getPublication_date());
        lbGenre.setText(ebook.getGenre());
        lbValue.setText(String.valueOf(ebook.getValue()));
        lbPrice.setText(String.valueOf(ebook.getPrice()));
        lbLang.setText(ebook.getLanguage());
        lbPages.setText(String.valueOf(ebook.getPage()));
        lbCover.setText(ebook.getCover());
        lbUnitSale.setText(String.valueOf(ebook.getUnit_sale()));
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
