/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.promos;

import connectors.PromoDbUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.PromoItem;
import models.Promo;
import utils.promoUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class CreatePromoController implements Initializable {
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfRate;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btCancel;
    @FXML
    private Button btCreate;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private DatePicker dateStart;
    @FXML
    private TableColumn<PromoItem, String> titleCol;
    @FXML
    private TableColumn<PromoItem, Float> priceCol;
    @FXML
    private Button btAddItem;
    @FXML
    private Button btRemoveItem;
    @FXML
    private TableView<PromoItem> itemList;
    
    private ArrayList<PromoItem> items = new ArrayList<PromoItem>();
    private Promo tempPromo = null;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setCellFactory(TextFieldTableCell.<PromoItem> forTableColumn());
        
        /*titleCol.setOnEditCommit((CellEditEvent<Promo,String> e) -> {
            TablePosition<Promo,String> pos = e.getTablePosition();
            
            String newTrackName = e.getNewValue();
            
            int row = pos.getRow();
            Promo track = e.getTableView().getItems().get(row);
            
            track.setName(newTrackName);
        });*/ 
        // those code for edit directly on item name (confuse)
        
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        if(promoUtils.getMode() == 1) {
            btCreate.setText("Update");
            tempPromo = (Promo) promoUtils.getPromo();
            loadInfo();
        }
    }    

    @FXML
    private void toCancel(ActionEvent event) {
        // TO DO : close this stage
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void toCreate(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(tfTitle.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Promo status");
            statusAlert.setContentText("No title entered.");

            statusAlert.showAndWait();
        }
        else if(items.isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Item status");
            statusAlert.setContentText("No items entered.");

            statusAlert.showAndWait();
        }
        else if(tfRate.getText().isEmpty()) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Item status");
            statusAlert.setContentText("No Rate entered");

            statusAlert.showAndWait();
        }
        else if(dateStart.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add Item status");
            statusAlert.setContentText("Invalid start date.");

            statusAlert.showAndWait();
        }
        else if(dateEnd.getValue() == null) {
            Alert statusAlert = new Alert(Alert.AlertType.ERROR);
            statusAlert.setTitle("Error");

            statusAlert.setHeaderText("Add item status");
            statusAlert.setContentText("Invalid end date.");

            statusAlert.showAndWait();
        }
        else {
            Promo newPromo;
            if(promoUtils.getMode() == 0) 
                newPromo = new Promo(tfTitle.getText(), tfDescription.getText(), dateStart.getValue().format(DateTimeFormatter.ISO_DATE), dateEnd.getValue().format(DateTimeFormatter.ISO_DATE));
            else 
                newPromo = new Promo(tempPromo.getId(), tfTitle.getText(), tfDescription.getText(), dateStart.getValue().format(DateTimeFormatter.ISO_DATE), dateEnd.getValue().format(DateTimeFormatter.ISO_DATE));
            boolean status;
            if(promoUtils.getMode() == 0) {
                status = PromoDbUtil.addPromo(newPromo);
                for(PromoItem item : items) {
                    item.setRate(Integer.parseInt(tfRate.getText()));
                    status = PromoDbUtil.addPromoItem(newPromo, item);
                }
                    
            }
                
            /*else
                status = PromoDbUtil.updateItem(newPromo);*/
            Alert statusAlert = new Alert(Alert.AlertType.INFORMATION);
            statusAlert.setTitle("Info");
            statusAlert.setHeaderText("Add Promo status");
            statusAlert.setContentText("Create Promo sucessfully..");
            statusAlert.showAndWait();
            // close this stage
            Stage stage = (Stage) btCancel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void removeItem(ActionEvent event) {
        PromoItem delItem = itemList.getSelectionModel().getSelectedItem();
        if(delItem != null) {
            items.remove(delItem);
            itemList.getItems().remove(delItem);
        }
    }

    @FXML
    private void addItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/promos/PromoItem.fxml"));
        Parent root = loader.load();
        PromoItemController itemController = loader.getController();
        
        Stage stage = new Stage();
        stage.setTitle("Add PromoItem");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        PromoItem newItem = itemController.getItem();
        if(newItem != null) {
            items.add(newItem);
            // add to table
            itemList.getItems().add(newItem);
        }
    }
    
    public void loadInfo() throws NullPointerException {
        items = PromoDbUtil.getAllPromoItemById(tempPromo.getId());
        
        tfTitle.setText(tempPromo.getName());
        itemList.getItems().addAll(items);
        tfDescription.setText(tempPromo.getDescription());
        if(!items.isEmpty()) //not empty
            tfRate.setText(String.valueOf(items.get(0).getRate()));
        else 
            tfRate.setText("0");
        dateStart.setValue(LocalDate.parse(tempPromo.getStart_time()));
        dateEnd.setValue(LocalDate.parse(tempPromo.getEnd_time()));
    }
}
