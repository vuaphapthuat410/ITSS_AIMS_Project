/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.products;

import data.UserInfo;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Book;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class Book_detailController implements Initializable {

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
    private Label lbStock;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(UserInfo.isAdmin())
            lbValue.setVisible(false);
        
        seeDetail((Book) Add_Update_Picker.getItem());
    }    
    
    public void seeDetail(Book book) {      
        imageView.setImage(new Image("data/images/"+Integer.toString(book.getId())+".jpg", 500, 500, false, false));
        lbTitle.setText(book.getTitle());
        lbAuthor.setText(book.getAuthor());
        lbPublisher.setText(book.getPublisher());
        lbDate.setText(book.getPublication_date());
        lbGenre.setText(book.getGenre());
        lbValue.setText(String.valueOf(book.getValue()));
        lbPrice.setText(String.valueOf(book.getPrice()));
        lbStock.setText(String.valueOf(book.getQuantity()));
        lbLang.setText(book.getLanguage());
        lbPages.setText(String.valueOf(book.getPage()));
        lbCover.setText(book.getCover());
        lbUnitSale.setText(String.valueOf(book.getUnit_sale()));
    }

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }
    
}
