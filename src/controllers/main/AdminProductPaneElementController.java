/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import connectors.LogDbUtil;
import connectors.helper.DeleteItemHelper;
import data.ControllerUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Album;
import models.Book;
import models.CD;
import models.DVD;
import models.Ebook;
import models.Item;
import models.LP;
import models.Log;
import models.Movie;
import models.PromoItem;
import utils.Add_Update_Picker;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AdminProductPaneElementController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label lbName;
    @FXML
    private Button btEdit;
    @FXML
    private Button btRemove;
    @FXML
    private Label lbPrice;
    @FXML
    private Label lbDiscountTag;
    @FXML
    private Label lbDiscount;
    
    private Item item;
    private PromoItem discountInfo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editItem(ActionEvent event) throws IOException {
        // TO DO: 
        String file_name = null;
        
        ArrayList<Log> logs = LogDbUtil.getAllLogByItemId(item.getId());
        for(Log log : logs) {
            // Vinh cause error in logs because he didn't generate all "add" log for all items
        }
            
        if(item instanceof Book) 
            file_name = "Book";
        else if(item instanceof CD)
            file_name = "CD";
        else if(item instanceof DVD)
            file_name = "DVD";
        else if(item instanceof LP)
            file_name= "LP";
        else if(item instanceof Ebook)
            file_name = "Ebook";
        else if(item instanceof Movie)
            file_name = "Movie";
        else if(item instanceof Album)
            file_name = "Album";
        
        if(file_name != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/products/"+file_name+".fxml"));
            Add_Update_Picker.setMode(1); // set mode to update item
            Add_Update_Picker.setItem(item);
            Parent node = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit " + file_name);
            stage.setScene(new Scene(node));
            stage.show();
        }
    }

    @FXML
    private void removeItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        // TO DO: Need to check if there is some order contains this item, how can it reference to its info ?
        Alert removeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        removeAlert.setTitle("Remove item");

        removeAlert.setHeaderText("Are you sure want to remove this item from system?");
        removeAlert.setContentText(item.getTitle());

        Optional<ButtonType> option = removeAlert.showAndWait();
        
        if (option.get() == ButtonType.OK) {
            DeleteItemHelper.deleteItem(item.getId()); // because of delete cascade of MySQL, delete from item table will make delete from other inherited table
                                                        // cant check if delete done cuz Vinh not set it as boolean return type
            
            removeAlert = new Alert(Alert.AlertType.INFORMATION);
            removeAlert.setTitle("Remove item");

            removeAlert.setHeaderText("Status: ");
            removeAlert.setContentText("Delete item:" + item.getTitle() + "successful");
        }
        ControllerUtils.refreshProductOnlyForAdmin();
    }
    
    public void setItem(Item anItem) {
        item = anItem;
        imageView.setImage(new Image("data/images/"+Integer.toString(item.getId())+".jpg", 200, 200, false, false));
        lbName.setText(item.getTitle());
        lbPrice.setText(Integer.toString(item.getPrice()));
        lbDiscount.setVisible(false);
        lbDiscountTag.setVisible(false);
    }
    
    public void setPromo(PromoItem promoItem) {
        discountInfo = promoItem;
        lbDiscount.setText(Integer.toString(discountInfo.getRate()) + "%");
        lbDiscount.setVisible(true);
        lbDiscountTag.setVisible(true);
    }
    
    @FXML
    private void toDetail(MouseEvent event) throws IOException {
        String file_name = null;
        
        if(item instanceof Book) 
            file_name = "Book";
        else if(item instanceof CD)
            file_name = "CD";
        else if(item instanceof DVD)
            file_name = "DVD";
        else if(item instanceof LP)
            file_name= "LP";
        else if(item instanceof Ebook)
            file_name = "Ebook";
        else if(item instanceof Movie)
            file_name = "Movie";
        else if(item instanceof Album)
            file_name = "Album";
        
        if(file_name != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/products/"+file_name+"_detail.fxml"));
            Add_Update_Picker.setItem(item);
            Parent node = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Show " + file_name);
            stage.setScene(new Scene(node));
            stage.show();
        }
    }
}
